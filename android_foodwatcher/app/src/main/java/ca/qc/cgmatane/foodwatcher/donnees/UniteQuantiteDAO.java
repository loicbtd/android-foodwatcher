package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.UniteQuantite;

public class UniteQuantiteDAO implements UniteQuantiteSQL{

    private static UniteQuantiteDAO instance = null;
    protected List<UniteQuantite> listeUniteQuantite;

    private BaseDeDonnees baseDeDonnees;

    public static UniteQuantiteDAO getInstance() {
        if (null == instance) {
            instance = new UniteQuantiteDAO();
        }
        return instance;
    }

    public UniteQuantiteDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeUniteQuantite = new ArrayList<>();

        // mock
        ajouterListeUniteQuantiteMock();
    }

    public List<UniteQuantite> recupererListeUniteQuantite() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_UNITE_QUANTITE, null);
        this.listeUniteQuantite.clear();

        UniteQuantite uniteQuantite;
        int indexIdUniteQuantite = curseur.getColumnIndex(UniteQuantite.CLE_ID_UNITE_QUANTITE);
        int indexEtiquette = curseur.getColumnIndex(UniteQuantite.CLE_ETIQUETTE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int idUniteQuantite = curseur.getInt(indexIdUniteQuantite);
            String etiquette = curseur.getString(indexEtiquette);
            uniteQuantite = new UniteQuantite(idUniteQuantite, etiquette);
            this.listeUniteQuantite.add(uniteQuantite);
        }
        return listeUniteQuantite;
    }

    public UniteQuantite trouverUniteQuantiteParId(int id) { //TODO: améliorer en utilisant une requête préparée ou une meilleure recherche
        recupererListeUniteQuantite();
        for (UniteQuantite uniteQuantite : listeUniteQuantite) {
            if (uniteQuantite.getIdUniteQuantite() == id) return uniteQuantite;
        }
        return null;
    }

    public void ajouterUniteQuantite(UniteQuantite uniteQuantite) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_CREER_UNITE_QUANTITE);
        sqLiteStatement.bindString(1, uniteQuantite.getEtiquette());
        sqLiteStatement.execute();
    }

    public void modifierUniteQuantite(UniteQuantite uniteQuantite) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_UNITE_QUANTITE);
        sqLiteStatement.bindString(1, uniteQuantite.getEtiquette());
        sqLiteStatement.bindString(2, ""+ uniteQuantite.getIdUniteQuantite());
        sqLiteStatement.execute();
    }

    public void supprimerUniteQuantite(UniteQuantite uniteQuantite) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_SUPPRIMER_UNITE_QUANTITE);
        sqLiteStatement.bindString(1, ""+ uniteQuantite.getIdUniteQuantite());
        sqLiteStatement.execute();
    }

    private void ajouterListeUniteQuantiteMock() {
        ajouterUniteQuantite(new UniteQuantite(0, "unité(s)"));
        ajouterUniteQuantite(new UniteQuantite(1, "kg"));
        ajouterUniteQuantite(new UniteQuantite(2, "g"));
        ajouterUniteQuantite(new UniteQuantite(3, "L"));
        ajouterUniteQuantite(new UniteQuantite(4, "mL"));
    }
}
