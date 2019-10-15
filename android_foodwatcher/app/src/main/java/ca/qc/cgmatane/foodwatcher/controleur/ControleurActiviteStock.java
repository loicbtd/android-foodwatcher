package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.widget.Toast;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.donnees.StockDAO;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteStock;

public class ControleurActiviteStock implements Controleur {

    public static final int ACTIVITE_AJOUTER_PRODUIT = 1;

    private ActiviteStock vue;
    protected ProduitStockeDAO produitStockeDAO;

    public ControleurActiviteStock(ActiviteStock vue){
        this.vue = vue;
    }

    public void actionNaviguerVueAjouterProduit(){
        vue.naviguerVueAjouterProduit();
    }



    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        produitStockeDAO = ProduitStockeDAO.getInstance();
        vue.setListeProduits(produitStockeDAO.recupererListeProduitStockeParIdStock(ControleurConteneurPrincipal.stockCourant.getIdStock()));
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
            case ACTIVITE_AJOUTER_PRODUIT:
                vue.setListeProduits(produitStockeDAO.recupererListeProduitStockeParIdStock(ControleurConteneurPrincipal.stockCourant.getIdStock()));
                vue.afficherProduits();
                break;
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void exporterProduitsStockeEnXML() {
        Toast.makeText(vue, "Exportation du Stock", Toast.LENGTH_SHORT).show();
    }

    public void supprimerStockCourant() {
        StockDAO.getInstance().supprimerStock(ControleurConteneurPrincipal.stockCourant);
        vue.peuplerListeStockDansMenuDrawer();
        vue.naviguerVueActiviteStock();
    }
}
