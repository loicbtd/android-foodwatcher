package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.CategorieProduit;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.modele.UniteQuantite;

public class ProduitDAO implements ProduitSQL {

    private static ProduitDAO instance = null;
    protected List<Produit> listeProduit;

    private BaseDeDonnees baseDeDonnees;

    public static ProduitDAO getInstance() {
        if (null == instance) {
            instance = new ProduitDAO();
        }
        return instance;
    }

    public ProduitDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeProduit = new ArrayList<>();

        // mock
        ajouterListeProduitMock();
    }

    public List<Produit> recupererListeProduit() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_PRODUIT, null);
        this.listeProduit.clear();

        Produit produit;
        int indexIdProduit = curseur.getColumnIndex(Produit.CLE_ID_PRODUIT);
        int indexGencode = curseur.getColumnIndex(Produit.CLE_GENCODE);
        int indexEtiquette = curseur.getColumnIndex(Produit.CLE_ETIQUETTE);
        int indexIdUniteQuantite = curseur.getColumnIndex(Produit.CLE_ID_UNITE_QUANTITE);
        int indexIdCategorieProduit = curseur.getColumnIndex(Produit.CLE_ID_CATEGORIE_PRODUIT);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int idProduit = curseur.getInt(indexIdProduit);
            String gencode = curseur.getString(indexGencode);
            String etiquette = curseur.getString(indexEtiquette);

            int idUniteQuantite = curseur.getInt(indexIdUniteQuantite);
            UniteQuantite uniteQuantite = UniteQuantiteDAO.getInstance().trouverUniteQuantiteParId(idUniteQuantite);

            int idCategorieProduit = curseur.getInt(indexIdCategorieProduit);
            CategorieProduit categorieProduit = CategorieProduitDAO.getInstance().trouverCategorieProduitParId(idCategorieProduit);

            produit = new Produit(
                    idProduit,
                    gencode,
                    etiquette,
                    uniteQuantite,
                    categorieProduit
            );
            this.listeProduit.add(produit);
        }
        return listeProduit;
    }

    public Produit recupererProduitParGencode(String gencodeRecherche) {
        recupererListeProduit();
        for (Produit produit: listeProduit) {
            if (gencodeRecherche.equals(produit.getGencode())) return produit;
        }
        return null;
    }

    public void ajouterProduit(Produit produit){
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_CREER_PRODUIT);
        sqLiteStatement.bindString(1, produit.getGencode());
        sqLiteStatement.bindString(2, ""+produit.getEtiquette());
        sqLiteStatement.bindString(3, ""+produit.getUniteQuantite().getIdUniteQuantite());
        sqLiteStatement.bindString(4, ""+produit.getCategorieProduit().getIdCategorieProduit());
        sqLiteStatement.execute();
    }

    public void modifierProduit(Produit produit) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_PRODUIT);
        sqLiteStatement.bindString(1, produit.getGencode());
        sqLiteStatement.bindString(2, ""+produit.getEtiquette());
        sqLiteStatement.bindString(3, ""+produit.getUniteQuantite().getIdUniteQuantite());
        sqLiteStatement.bindString(4, ""+produit.getCategorieProduit().getIdCategorieProduit());
        sqLiteStatement.bindString(5, ""+produit.getIdProduit());
        sqLiteStatement.execute();
    }

    public void supprimerProduit(Produit produit) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_SUPPRIMER_PRODUIT);
        sqLiteStatement.bindString(1, ""+produit.getIdProduit());
        sqLiteStatement.execute();
    }

    public void ajouterListeProduitMock() {

        ajouterProduit(new Produit(
            0,
            "9897789765567",
            "avocat",
            new UniteQuantite(0, "unité(s)"),
            new CategorieProduit(0, "Légume")
        ));

        ajouterProduit(new Produit(
            0,
            "1234567891234",
            "salade verte",
            new UniteQuantite(0, "unité(s)"),
            new CategorieProduit(0, "Légume")
        ));

        ajouterProduit(new Produit(
            0,
            "9876543219876",
            "oeuf à l'unité",
            new UniteQuantite(0, "unité(s)"),
            new CategorieProduit(0, "Légume")
        ));
    }
}
