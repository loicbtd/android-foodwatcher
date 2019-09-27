package ca.qc.cgmatane.foodwatcher.data;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.model.CategorieProduit;

public class CategorieProduitDAO implements CategorieProduitSQL {

    private static CategorieProduitDAO instance;
    protected List<CategorieProduit> listeCategorieProduit;

    private BaseDeDonnees accesseurBaseDeDonnees;

    public static CategorieProduitDAO getInstance() {
        if (null == instance) {
            instance = new CategorieProduitDAO();
        }
        return instance;
    }

    public CategorieProduitDAO() {
        this.accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        listeCategorieProduit = new ArrayList<>();
    }

}
