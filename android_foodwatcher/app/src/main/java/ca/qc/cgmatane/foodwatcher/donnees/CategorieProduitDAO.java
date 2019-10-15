package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.CategorieProduit;

public class CategorieProduitDAO implements CategorieProduitSQL {

    private static CategorieProduitDAO instance = null;
    protected List<CategorieProduit> listeCategorieProduit;

    private BaseDeDonnees baseDeDonnees;

    public static CategorieProduitDAO getInstance() {
        if (null == instance) {
            instance = new CategorieProduitDAO();
        }
        return instance;
    }

    public CategorieProduitDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeCategorieProduit = new ArrayList<>();
    }

    public List<CategorieProduit> recupererListeCategorieProduit() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_CATEGORIE_PRODUIT, null);
        this.listeCategorieProduit.clear();

        CategorieProduit categorieProduit;
        int indexIdCategorieProduit = curseur.getColumnIndex(CategorieProduit.CLE_ID_CATEGORIE_PRODUIT);
        int indexEtiquette = curseur.getColumnIndex(CategorieProduit.CLE_ETIQUETTE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int idCategorieProduit = curseur.getInt(indexIdCategorieProduit);
            String etiquette = curseur.getString(indexEtiquette);
            categorieProduit = new CategorieProduit(idCategorieProduit, etiquette);
            this.listeCategorieProduit.add(categorieProduit);
        }
        return listeCategorieProduit;
    }

    public CategorieProduit trouverCategorieProduitParId(int id) { //TODO: améliorer en utilisant une requête préparée ou une meilleure recherche
        recupererListeCategorieProduit();
        for (CategorieProduit categorieProduit : listeCategorieProduit) {
            if (categorieProduit.getIdCategorieProduit() == id) return categorieProduit;
        }
        return null;
    }

    public void ajouterCategorieProduit(CategorieProduit categorieProduit) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_CREER_CATEGORIE_PRODUIT);
        sqLiteStatement.bindString(1, categorieProduit.getEtiquette());
        sqLiteStatement.execute();
    }

    public void modifierCategorieProduit(CategorieProduit categorieProduit) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_CATEGORIE_PRODUIT);
        sqLiteStatement.bindString(1, categorieProduit.getEtiquette());
        sqLiteStatement.bindString(2, ""+ categorieProduit.getIdCategorieProduit());
        sqLiteStatement.execute();
    }

    public void supprimerCategorieProduit(CategorieProduit categorieProduit) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_SUPPRIMER_CATEGORIE_PRODUIT);
        sqLiteStatement.bindString(1, ""+ categorieProduit.getIdCategorieProduit());
        sqLiteStatement.execute();
    }

    private void ajouterListeCategorieProduitMock() {
        ajouterCategorieProduit(new CategorieProduit(0, "Épicerie sucrée"));
        ajouterCategorieProduit(new CategorieProduit(1, "Épicerie salée"));
        ajouterCategorieProduit(new CategorieProduit(2, "Fruits & Légumes"));
    }
}
