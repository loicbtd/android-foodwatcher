package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.CategorieProduit;
import ca.qc.cgmatane.foodwatcher.modele.Emplacement;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.modele.Stock;
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
        int indexQuantite = curseur.getColumnIndex(Produit.CLE_QUANTITE);
        int indexPresentListeCourse = curseur.getColumnIndex(Produit.CLE_PRESENT_LISTE_COURSE);
        int indexIdUniteQuantite = curseur.getColumnIndex(Produit.CLE_ID_UNITE_QUANTITE);
        int indexIdCategorieProduit = curseur.getColumnIndex(Produit.CLE_ID_CATEGORIE_PRODUIT);
        int indexIdStock = curseur.getColumnIndex(Produit.CLE_ID_STOCK);
        int indexIdEmplacement = curseur.getColumnIndex(Produit.CLE_ID_EMPLACEMENT);

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

            produit = new Produit(
                    idProduit,
                    gencode,
                    etiquette,
                    quantite,
                    presentListeCourse,
                    uniteQuantite,
                    categorieProduit,
                    stock,
                    emplacement
            );
            this.listeProduit.add(produit);
        }
        return listeProduit;
    }



    public void ajouterProduit(Produit produit){
        listeProduit.add(produit);
    }

    public void ajouterListeProduitMock() {

        ajouterProduit(new Produit(
            0,
            "9897789765567",
            "avocat",
            2.0,
            false,
            new UniteQuantite(0, "unité(s)"),
            new CategorieProduit(0, "Légume"),
            new Stock(0, "Domicile"),
            new Emplacement(0, "Réserve")
        ));

        ajouterProduit(new Produit(
            0,
            "1234567891234",
            "salade verte",
            1.0,
            false,
            new UniteQuantite(0, "unité(s)"),
            new CategorieProduit(0, "Légume"),
            new Stock(0, "Domicile"),
            new Emplacement(0, "Réserve")
        ));

        ajouterProduit(new Produit(
            0,
            "9876543219876",
            "oeuf à l'unité",
            1.0,
            false,
            new UniteQuantite(0, "unité(s)"),
            new CategorieProduit(0, "Légume"),
            new Stock(0, "Domicile"),
            new Emplacement(0, "Réserve")
        ));
    }
}
