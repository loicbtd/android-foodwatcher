package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Emplacement;

public class EmplacementDAO implements EmplacementSQL {

    private static EmplacementDAO instance = null;
    protected List<Emplacement> listeEmplacement;

    private BaseDeDonnees baseDeDonnees;

    public static EmplacementDAO getInstance() {
        if (null == instance) {
            instance = new EmplacementDAO();
        }
        return instance;
    }

    public EmplacementDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeEmplacement = new ArrayList<>();

        // mock
        ajouterListeEmplacementMock();
    }

    public List<Emplacement> recupererListeEmplacement() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_EMPLACEMENT, null);
        this.listeEmplacement.clear();

        Emplacement emplacement;
        int indexIdEmplacement = curseur.getColumnIndex(Emplacement.CLE_ID_EMPLACEMENT);
        int indexEtiquette = curseur.getColumnIndex(Emplacement.CLE_ETIQUETTE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int idEmplacement = curseur.getInt(indexIdEmplacement);
            String etiquette = curseur.getString(indexEtiquette);
            emplacement = new Emplacement(idEmplacement, etiquette);
            this.listeEmplacement.add(emplacement);
        }
        return listeEmplacement;
    }

    public Emplacement trouverEmplacementParId(int id) { //TODO: améliorer en utilisant une requête préparée ou une meilleure recherche
        recupererListeEmplacement();
        for (Emplacement emplacement : listeEmplacement) {
            if (emplacement.getIdEmplacement() == id) return emplacement;
        }
        return null;
    }

    public void ajouterEmplacement(Emplacement emplacement) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_CREER_EMPLACEMENT);
        sqLiteStatement.bindString(1, emplacement.getEtiquette());
        sqLiteStatement.execute();
    }

    public void modifierEmplacement(Emplacement emplacement) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_EMPLACEMENT);
        sqLiteStatement.bindString(1, emplacement.getEtiquette());
        sqLiteStatement.bindString(2, ""+ emplacement.getIdEmplacement());
        sqLiteStatement.execute();
    }

    public void supprimerEmplacement(Emplacement emplacement) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_SUPPRIMER_EMPLACEMENT);
        sqLiteStatement.bindString(1, ""+ emplacement.getIdEmplacement());
        sqLiteStatement.execute();
    }

    private void ajouterListeEmplacementMock() {
        ajouterEmplacement(new Emplacement(0, "Réserve"));
        ajouterEmplacement(new Emplacement(1, "Réfrigérateur"));
        ajouterEmplacement(new Emplacement(2, "Cuisine"));
    }
}
