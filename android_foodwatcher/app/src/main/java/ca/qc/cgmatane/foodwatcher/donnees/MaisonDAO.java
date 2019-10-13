package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Maison;

public class MaisonDAO implements MaisonSQL {

    private static MaisonDAO instance = null;
    protected List<Maison> listeMaison;

    private BaseDeDonnees baseDeDonnees;

    public static MaisonDAO getInstance() {
        if (null == instance) {
            instance = new MaisonDAO();
        }
        return instance;
    }

    public MaisonDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeMaison = new ArrayList<>();
        ajouterListeMaisonMock();
    }

    public List<Maison> recupererListeMaison() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_MAISON, null);
        this.listeMaison.clear();

        Maison maison;
        int indexId_maison = curseur.getColumnIndex(Maison.CLE_ID_MAISON);
        int indexEtiquette = curseur.getColumnIndex(Maison.CLE_ETIQUETTE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int id_maison = curseur.getInt(indexId_maison);
            String etiquette = curseur.getString(indexEtiquette);
            maison = new Maison(id_maison, etiquette);
            this.listeMaison.add(maison);
        }
        return listeMaison;
    }

    public void ajouterMaison(Maison maison) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_INSERER_MAISON);
        sqLiteStatement.bindString(1, maison.getEtiquette());
        sqLiteStatement.execute();
    }

    public void modifierMaison(Maison maison) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODFIFIER_MAISON);
        sqLiteStatement.bindString(1, maison.getEtiquette());
        sqLiteStatement.bindString(2, ""+maison.getId_maison());
        sqLiteStatement.execute();
    }

    public void ajouterListeMaisonMock() {
        ajouterMaison(new Maison(0, "Domicile"));
        ajouterMaison(new Maison(1, "Maison vacances"));
        ajouterMaison(new Maison(2, "Mon restaurant"));
    }
}
