package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitDAO;
import ca.qc.cgmatane.foodwatcher.modele.ListeCourseAdapteur;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteListeCourse;

public class ControleurActiviteListeDeCourse implements Controleur {
    protected ActiviteListeCourse vue;
    protected ProduitDAO accesseurProduit;
    public ControleurActiviteListeDeCourse(ActiviteListeCourse vue){
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        accesseurProduit = ProduitDAO.getInstance();
        vue.setListeProduits(accesseurProduit.recupererListeProduit());
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
}
