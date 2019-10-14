package ca.qc.cgmatane.foodwatcher.donnees;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Produit;

public class ProduitDAO {

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
    }

    public List<Produit> recupererListeProduit() {
        listeProduit = new ArrayList<>();
        mockListeProduit();
        return listeProduit;
    }

    public void ajouterProduit(Produit produit){
        listeProduit.add(produit);
    }

    public void mockListeProduit() {
        listeProduit.add(new Produit(
                0,
                "9897789765567",
                "avocat",
                0,
                0,
                0,
                2.0,
                0,
                false
        ));
        listeProduit.add(new Produit(
                1,
                "1234567891234",
                "salade verte",
                0,
                0,
                0,
                3.0,
                0,
                true
        ));
        listeProduit.add(new Produit(
                2,
                "9876543219876",
                "oeuf à l'unité",
                0,
                0,
                0,
                1.0,
                0,
                true
        ));
    }
}
