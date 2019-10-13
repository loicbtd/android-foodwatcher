package ca.qc.cgmatane.foodwatcher.donnees;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Produit;

public class ProduitDAO {

    private static ProduitDAO instance = null;
    protected List<Produit> listeProduit;

    private BaseDeDonneesDAO baseDeDonneesDAO;

    public static ProduitDAO getInstance() {
        if (null == instance) {
            instance = new ProduitDAO();
        }
        return instance;
    }

    public ProduitDAO() {
        this.baseDeDonneesDAO = BaseDeDonneesDAO.getInstance();
        listeProduit = new ArrayList<>();
    }

    public List<Produit> recupererListeProduit() {
        listeProduit = new ArrayList<>();
        mockListeProduit();
        return listeProduit;
    }

    public void mockListeProduit() {
        listeProduit.add(new Produit(
                0,
                "9897789765567",
                "avocat",
                7,
                "avocat_vrac.png",
                1,
                1
        ));
        listeProduit.add(new Produit(
                1,
                "1234567891234",
                "salade verte en sachet",
                3,
                "salade_verte_sachet.png",
                0,
                0
        ));
        listeProduit.add(new Produit(
                2,
                "9876543219876",
                "oeuf à l'unité",
                60,
                "oeuf_unite.png",
                0,
                0
        ));
    }
}
