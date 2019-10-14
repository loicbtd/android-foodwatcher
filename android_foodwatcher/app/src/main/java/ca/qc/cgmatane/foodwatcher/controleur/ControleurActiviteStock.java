package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitDAO;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteStock;

public class ControleurActiviteStock implements Controleur {
    private ActiviteStock vue;
    public static final int ADD_PRODUCT_ACTIVITY = 1;
    protected ProduitDAO accesseurProduit;

    public ControleurActiviteStock(ActiviteStock vue){
        this.vue = vue;
    }

    public void actionNaviguerVueAjouterProduit(){
        vue.naviguerVueAjouterProduit();
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
        System.out.println("ON ACTIVITY RESULT");
        switch (activite){
            case ADD_PRODUCT_ACTIVITY:
                System.out.println("ajouter un élément à la liste");
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}