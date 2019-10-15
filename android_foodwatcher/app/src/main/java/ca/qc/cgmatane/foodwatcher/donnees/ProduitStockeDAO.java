package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.text.format.DateFormat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.controleur.ControleurConteneurPrincipal;
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

    public void exporterProduitsStockeEnXML(){

        List<ProduitStocke> listeProduitsStocke =
                recupererListeProduitStockeParIdStock(
                        ControleurConteneurPrincipal.stockCourant.getIdStock());

        String baliseStockOuvrante = "<stocke>";
        String baliseStockFermante = "</stocke>";

        String baliseProduitOuvrante = "<produit-stocke>";
        String baliseProduitFermante = "</produit-stocke>";

        String baliseIdProduitOuvrante = "<id-produit>";
        String baliseIdProduitFermante = "</id-produit>";

        String baliseGencodeOuvrante = "<gencode>";
        String baliseGencodeFermante = "</gencode>";

        String baliseEtiquetteOuvrante = "<etiquette>";
        String baliseEtiquetteFermante = "</etiquette>";

        String baliseIdUniteQuantiteOuvrante = "<id-unite-quantite>";
        String baliseIdUniteQuantiteFermante = "</id-unite-quantite>";

        String baliseIdCategorieProduitOuvrante = "<id-categorie-produit>";
        String baliseIdCategorieProduitFermante = "</id-categorie-produit>";

        String baliseIdStockOuvrante = "<id-stock>";
        String baliseIdStockFermante = "</id-stock>";

        String baliseQuantiteOuvrante = "<quantite>";
        String baliseQuantiteFermante = "</quantite>";

        String baliseIdEmplacementOuvrante = "<id-emplacement>";
        String baliseIdEmplacementFermante = "</id-emplacement>";

        String balisePresentListeCoursesOuvrante = "<present-liste-courses>";
        String balisePresentListeCoursesFermante = "</present-liste-courses>";


        String contenuXML = baliseStockOuvrante;

        for (ProduitStocke produitStocke : listeProduitsStocke) {

            contenuXML += baliseProduitOuvrante +
                    baliseIdProduitOuvrante + produitStocke.getIdProduit() + baliseIdProduitFermante +
                    baliseGencodeOuvrante + produitStocke.getGencode() + baliseGencodeFermante +
                    baliseEtiquetteOuvrante + produitStocke.getEtiquette() + baliseEtiquetteFermante +
                    baliseIdUniteQuantiteOuvrante + produitStocke.getUniteQuantite().getIdUniteQuantite() + baliseIdUniteQuantiteFermante +
                    baliseIdCategorieProduitOuvrante + produitStocke.getCategorieProduit().getIdCategorieProduit() + baliseIdCategorieProduitFermante +
                    baliseIdStockOuvrante + produitStocke.getStock().getIdStock() + baliseIdStockFermante +
                    baliseQuantiteOuvrante + produitStocke.getQuantite() + baliseQuantiteFermante +
                    baliseIdEmplacementOuvrante + produitStocke.getEmplacement().getIdEmplacement() + baliseIdEmplacementFermante +
                    balisePresentListeCoursesOuvrante + produitStocke.isPresentListeCourse() + balisePresentListeCoursesFermante +
                    baliseProduitFermante;

        }

        contenuXML += baliseStockFermante;

        try {
            String h = DateFormat.format("MM-dd-yyyyy-h-mmssaa", System.currentTimeMillis()).toString();
            // this will create a new name everytime and unique
//            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            // if external memory exists and folder with name Notes
            File root = new File(Environment.DIRECTORY_DOWNLOADS);
            if (!root.exists()) {
                root.mkdirs(); // this will create folder.
            }
            File filepath = new File(root, h + ".xml");  // file path to save
            FileWriter writer = new FileWriter(filepath);
            writer.append(contenuXML);
            writer.flush();
            writer.close();
            String m = "File generated with name " + h + ".xml";
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + m);

        } catch (IOException e) {
            e.printStackTrace();

        }



    }

}
