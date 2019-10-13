package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonneesDAO;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitDAO;
import ca.qc.cgmatane.foodwatcher.vue.Stock;

public class ControleurStock implements Controleur {
    private Stock vue;
    public static final int ADD_PRODUCT_ACTIVITY = 1;

    public ControleurStock(Stock vue){
        this.vue = vue;
    }

    public void actionNavigateToViewAddProduct(){
        vue.naviguerVueAjouterProduit();
    }

    protected ProduitDAO produitDAO;

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonneesDAO.getInstance(applicationContext);
        produitDAO = ProduitDAO.getInstance();
        vue.setListeProduits(produitDAO.recupererListeProduit());
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
                vue.ajouterProduitListe();
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}
