package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitDAO;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteListeCourse;

public class ControleurActiviteListeDeCourse implements Controleur {
    protected ActiviteListeCourse vue;
    protected ProduitStockeDAO accesseurProduit;
    public ControleurActiviteListeDeCourse(ActiviteListeCourse vue){
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        accesseurProduit = ProduitStockeDAO.getInstance();
        //TODO: trouver un moyen de recuperer id du stock (avec extra dans intent)
        vue.setListeProduits(accesseurProduit.recupererListeProduitStockeParIdStock(1));
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int activite) {

    }

    @Override
    public void onBackPressed() {

    }

    public void actionSupprimerSelection() {
        vue.supprimerSelection();
    }
}
