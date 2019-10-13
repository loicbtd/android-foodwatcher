package ca.qc.cgmatane.foodwatcher.donnees;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Maison;

public class MaisonDAO implements MaisonSQL {

    private static MaisonDAO instance = null;
    protected List<Maison> listeMaison;

    private BaseDeDonneesDAO baseDeDonneesDAO;

    public static MaisonDAO getInstance() {
        if (null == instance) {
            instance = new MaisonDAO();
        }
        return instance;
    }

    public MaisonDAO() {
        this.baseDeDonneesDAO = BaseDeDonneesDAO.getInstance();
        listeMaison = new ArrayList<>();
    }

    public List<Maison> recupererListeMaison() {
        listeMaison = new ArrayList<>();
        mockListeMaison();
        return listeMaison;
    }

    public void mockListeMaison() {
        listeMaison.add(new Maison(0, "Domicile"));
        listeMaison.add(new Maison(1, "Maison vacances"));
        listeMaison.add(new Maison(2, "Mon restaurant"));
    }
}
