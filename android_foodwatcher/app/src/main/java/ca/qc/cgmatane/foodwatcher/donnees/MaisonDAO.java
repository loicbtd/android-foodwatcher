package ca.qc.cgmatane.foodwatcher.donnees;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Maison;

public class MaisonDAO implements MaisonSQL {

    private static MaisonDAO instance = null;
    protected List<Maison> listMaison;

    private BaseDeDonneesDeDonnees baseDeDonnees;

    public static MaisonDAO getInstance() {
        if (null == instance) {
            instance = new MaisonDAO();
        }
        return instance;
    }

    public MaisonDAO() {
        this.baseDeDonnees = BaseDeDonneesDeDonnees.getInstance();
        listMaison = new ArrayList<>();
    }

    public List<Maison> pickupListHome() {
        listMaison = new ArrayList<>();
        mockListHome();
        return listMaison;
    }

    public void mockListHome() {
        Maison maison;
        for (int i = 0; i < 5; i++) {
            int id_maison = i;
            String etiquette = "Maison " + i;
            maison = new Maison(id_maison, etiquette);
            listMaison.add(maison);
        }
    }
}
