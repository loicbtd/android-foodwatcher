package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.donnees.StockDAO;
import ca.qc.cgmatane.foodwatcher.modele.Stock;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteAjouterStock;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteExemple;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteListeCourse;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteStock;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteTrouverMagasin;
import ca.qc.cgmatane.foodwatcher.vue.ConteneurPrincipal;

public class ControleurConteneurPrincipal implements Controleur, NavigationView.OnNavigationItemSelectedListener {

    static final public int ACTIVITE_EXEMPLE = -1;
    static final public int ACTIVITE_STOCK = 1;
    static final public int ACTIVITE_AJOUTER_STOCK = 2;
    static final public int ACTIVITY_CARTE_MAGASIN = 3;

    public static Stock stockCourant;

    protected ConteneurPrincipal vue;

    protected StockDAO stockDAO;

    public ControleurConteneurPrincipal(ConteneurPrincipal vue) {
        this.vue = vue;
    }


    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        stockDAO = StockDAO.getInstance();

        List<Stock> listeStock = stockDAO.recupererListeStock();
        if (listeStock.size() == 0) return;
        if (stockCourant == null) stockCourant = listeStock.get(0);
        vue.setListeStock(listeStock);
        vue.peuplerListeStockDansMenuDrawer();

        Menu menu = vue.getNavigationView().getMenu();
        MenuItem itemMenu = menu.findItem(R.id.conteneur_principal_drawer_rubrique_stock);
        SubMenu sousMenuStock = itemMenu.getSubMenu();
        sousMenuStock.findItem(stockCourant.getIdStock()).setChecked(true);
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
        switch (activite) {
            case ACTIVITE_AJOUTER_STOCK:
                vue.setListeStock(stockDAO.recupererListeStock());
                vue.peuplerListeStockDansMenuDrawer();
        }
    }

    @Override
    public void onBackPressed() {
        if (vue.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            vue.getDrawerLayout().closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        int itemId;

        // récupération de l'id de l'item sélectionné
        itemId = item.getItemId();

        // fermeture du drawer
        vue.getDrawerLayout().closeDrawer(GravityCompat.START);

        // si l'id de l'item sélectionné correspond à un l'id d'un stock
        if (itemId >= 1 && itemId <= vue.getListeStock().size()) {
            stockCourant.setIdStock(itemId);
            intent = new Intent(vue.getApplicationContext(), ActiviteStock.class);
            vue.startActivityForResult(intent, ACTIVITE_STOCK);
        } // sinon, s'il correspond à l'id d'une autre activite
        else {
            switch (itemId) {
                    // lancer ActiviteExemple
                case R.id.conteneur_principal_drawer_action_naviguer_exemple:
                    intent = new Intent(vue.getApplicationContext(), ActiviteExemple.class);
                    vue.startActivityForResult(intent, ACTIVITE_EXEMPLE);
                    //TODO décommenter pour tester l'export du xml
                    ProduitStockeDAO produitStockeDAO = ProduitStockeDAO.getInstance();
                    produitStockeDAO.exporterProduitsStockeEnXML();
                    break;
                    // lancer ActiviteAjouterStock
                case R.id.conteneur_principal_drawer_action_naviguer_ajouter_stock:
                    intent = new Intent(vue.getApplicationContext(), ActiviteAjouterStock.class);
                    vue.startActivityForResult(intent, ACTIVITE_AJOUTER_STOCK);
                    break;
                    // lancer ActiviteTrouverMagasin
                case R.id.conteneur_principal_drawer_action_naviguer_carte_magasin:
                    intent = new Intent(vue.getApplicationContext(), ActiviteTrouverMagasin.class);
                    vue.startActivityForResult(intent, ACTIVITY_CARTE_MAGASIN);
                    break;
                case R.id.conteneur_principal_drawer_action_naviguer_liste_course:
                    intent = new Intent(vue.getApplicationContext(), ActiviteListeCourse.class);
                    vue.startActivity(intent);
                    break;
                    // retourne false si l'item reçu ne correspond à aucune activité
                default:
                    return false;
            }
        }

        // ferme le drawer et déselectionne tous les items
        Menu menu = vue.getNavigationView().getMenu();
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setChecked(false);
            menu.findItem(stockCourant.getIdStock()).setChecked(true);
        }
        return true;
    }
}
