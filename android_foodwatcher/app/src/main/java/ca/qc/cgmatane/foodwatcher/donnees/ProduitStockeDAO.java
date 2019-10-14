package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.CategorieProduit;
import ca.qc.cgmatane.foodwatcher.modele.Emplacement;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;
import ca.qc.cgmatane.foodwatcher.modele.Stock;
import ca.qc.cgmatane.foodwatcher.modele.UniteQuantite;

public class ProduitStockeDAO implements ProduitStockeSQL {

    private static ProduitStockeDAO instance = null;
    protected List<ProduitStocke> listeProduitStocke;

    private BaseDeDonnees baseDeDonnees;

    public static ProduitStockeDAO getInstance() {
        if (null == instance) {
            instance = new ProduitStockeDAO();
        }
        return instance;
    }

    public ProduitStockeDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeProduitStocke = new ArrayList<>();

        // mock
        ajouterProduitAuStockMock();
    }

    public List<ProduitStocke> recupererListeProduitStockeParIdStock(int idStockRecherche) {
        Cursor curseur = baseDeDonnees.getWritableDatabase()
                .rawQuery(SQL_TROUVER_LISTE_PRODUIT_PAR_ID_STOCK, new String[] {""+idStockRecherche});
        this.listeProduitStocke.clear();

        ProduitStocke produitStocke;
        int indexIdProduit = curseur.getColumnIndex(ProduitStocke.CLE_ID_PRODUIT);
        int indexGencode = curseur.getColumnIndex(ProduitStocke.CLE_GENCODE);
        int indexEtiquette = curseur.getColumnIndex(ProduitStocke.CLE_ETIQUETTE);
        int indexQuantite = curseur.getColumnIndex(ProduitStocke.CLE_QUANTITE);
        int indexPresentListeCourse = curseur.getColumnIndex(ProduitStocke.CLE_PRESENT_LISTE_COURSE);
        int indexIdUniteQuantite = curseur.getColumnIndex(ProduitStocke.CLE_ID_UNITE_QUANTITE);
        int indexIdCategorieProduit = curseur.getColumnIndex(ProduitStocke.CLE_ID_CATEGORIE_PRODUIT);
        int indexIdStock = curseur.getColumnIndex(ProduitStocke.CLE_ID_STOCK);
        int indexIdEmplacement = curseur.getColumnIndex(ProduitStocke.CLE_ID_EMPLACEMENT);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int idProduit = curseur.getInt(indexIdProduit);
            String gencode = curseur.getString(indexGencode);
            String etiquette = curseur.getString(indexEtiquette);
            double quantite = curseur.getDouble(indexQuantite);
            boolean presentListeCourse = curseur.getInt(indexPresentListeCourse) == 0;

            int idUniteQuantite = curseur.getInt(indexIdUniteQuantite);
            UniteQuantite uniteQuantite = UniteQuantiteDAO.getInstance().trouverUniteQuantiteParId(idUniteQuantite);

            int idCategorieProduit = curseur.getInt(indexIdCategorieProduit);
            CategorieProduit categorieProduit = CategorieProduitDAO.getInstance().trouverCategorieProduitParId(idCategorieProduit);

            int idStock = curseur.getInt(indexIdStock);
            Stock stock = StockDAO.getInstance().trouverStockParId(idStock);

            int idEmplacement = curseur.getInt(indexIdEmplacement);
            Emplacement emplacement = EmplacementDAO.getInstance().trouverEmplacementParId(idEmplacement);

            produitStocke = new ProduitStocke(
                    idProduit,
                    gencode,
                    etiquette,
                    uniteQuantite,
                    categorieProduit,
                    stock,
                    emplacement,
                    quantite,
                    presentListeCourse
            );
            this.listeProduitStocke.add(produitStocke);
        }
        return listeProduitStocke;
    }

    public void ajouterProduitAuStock(ProduitStocke produitStocke){
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_CREER_STOCK_COMPOSE_PRODUIT);
        sqLiteStatement.bindString(1, ""+produitStocke.getIdProduit());
        sqLiteStatement.bindString(2, ""+produitStocke.getStock().getIdStock());
        sqLiteStatement.bindString(3, ""+produitStocke.getQuantite());
        sqLiteStatement.bindString(4, ""+produitStocke.getEmplacement().getIdEmplacement());
        sqLiteStatement.bindString(5, ""+(produitStocke.isPresentListeCourse() ? 0 : 1));
        sqLiteStatement.execute();
    }

    public void modifierProduitStocke(ProduitStocke produitStocke) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_STOCK_COMPOSE_PRODUIT);
        sqLiteStatement.bindString(1, ""+produitStocke.getQuantite());
        sqLiteStatement.bindString(2, ""+produitStocke.getEmplacement().getIdEmplacement());
        sqLiteStatement.bindString(3, ""+(produitStocke.isPresentListeCourse() ? 0 : 1));
        sqLiteStatement.bindString(4, ""+produitStocke.getIdProduit());
        sqLiteStatement.bindString(5, ""+produitStocke.getStock().getIdStock());
        sqLiteStatement.execute();
    }

    public void supprimerProduitDuStock(ProduitStocke produitStocke) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_SUPPRIMER_STOCK_COMPOSE_PRODUIT);
        sqLiteStatement.bindString(1, ""+produitStocke.getIdProduit());
        sqLiteStatement.bindString(1, ""+produitStocke.getStock().getIdStock());
        sqLiteStatement.execute();
    }

    public void ajouterProduitAuStockMock() {

        List<Produit> listeProduit = ProduitDAO.getInstance().recupererListeProduit();
        List<Stock> listeStock = StockDAO.getInstance().recupererListeStock();
        Emplacement emplacement = EmplacementDAO.getInstance().trouverEmplacementParId(1);

        ProduitStocke produitStocke;
        for (Stock stock : listeStock) {
            for (Produit produit : listeProduit) {
                produitStocke = new ProduitStocke(
                        produit.getIdProduit(),
                        produit.getGencode(),
                        produit.getEtiquette(),
                        produit.getUniteQuantite(),
                        produit.getCategorieProduit(),
                        stock,
                        emplacement,
                        2.0,
                        true
                );
                ajouterProduitAuStock(produitStocke);
            }
        }
    }
}
