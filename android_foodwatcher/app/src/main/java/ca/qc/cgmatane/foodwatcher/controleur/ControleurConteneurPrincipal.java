package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.StockDAO;
import ca.qc.cgmatane.foodwatcher.modele.Stock;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteAjouterStock;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteExemple;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteListeCourse;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteStock;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteTrouverMagasin;
import ca.qc.cgmatane.foodwatcher.vue.ConteneurPrincipal;

public class ControleurConteneurPrincipal implements Controleur, NavigationView.OnNavigationItemSelectedListener {

    static final public int ACTIVITY_SAMPLE = -1;
    static final public int ACTIVITY_STOCK = 1;
    static final public int ACTIVITY_ADD_HOME = 2;
    static final public int ACTIVITY_FIND_STORE = 3;

    public static Stock stockCourant;

    protected ConteneurPrincipal view;

    protected StockDAO stockDAO;

    public ControleurConteneurPrincipal(ConteneurPrincipal view) {
        this.view = view;
    }


    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        stockDAO = StockDAO.getInstance();
        view.setListStock(stockDAO.recupererListeStock());
        view.populateHomeInMenuDrawer();
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
        if (view.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            view.getDrawerLayout().closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        int itemId;

        // get item id
        itemId = item.getItemId();
        Toast.makeText(view, "item "+itemId, Toast.LENGTH_SHORT).show();

        // close the drawer
        view.getDrawerLayout().closeDrawer(GravityCompat.START);

        // if itemId corresponds to a stock
        if (0 <= itemId && itemId < view.getListStock().size()) {
            if (itemId != stockCourant.getIdStock()) {
                int id = itemId;
                itemId = stockCourant.getIdStock();
                intent = new Intent(view.getApplicationContext(), ActiviteStock.class);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+view.getListStock().get(id).getIdStock());
                intent.putExtra("idStock", view.getListStock().get(id).getIdStock());
                view.startActivityForResult(intent, ACTIVITY_STOCK);
            }
        } // else if it corresponds to another activity
        else {
            switch (itemId) {
                    // start ActiviteExemple
                case R.id.activity_master_drawer_action_display_sample:
                    intent = new Intent(view.getApplicationContext(), ActiviteExemple.class);
                    view.startActivityForResult(intent, ACTIVITY_SAMPLE);
                    //TODO décommenter pour tester l'export du xml
//                    ProduitStockeDAO produitStockeDAO = ProduitStockeDAO.getInstance();
//                    produitStockeDAO.exporterProduitsStockeEnXML();
                    break;
                    // start ActiviteAjouterStock
                case R.id.activity_master_drawer_action_add_home:
                    intent = new Intent(view.getApplicationContext(), ActiviteAjouterStock.class);
                    view.startActivityForResult(intent, ACTIVITY_ADD_HOME);
                    break;
                    // start ActiviteTrouverMagasin
                case R.id.activity_master_drawer_action_find_store:
                    intent = new Intent(view.getApplicationContext(), ActiviteTrouverMagasin.class);
                    view.startActivityForResult(intent, ACTIVITY_FIND_STORE);
                    break;
                case R.id.activity_master_drawer_action_display_shopping_list:
                    intent = new Intent(view.getApplicationContext(), ActiviteListeCourse.class);
                    view.startActivity(intent);
                    // return false if item id does not correspond to any activity
                default:
                    return false;
            }
        }

        // close navigation drawer and unselect items
        Menu menu = view.getNavigationView().getMenu();
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setChecked(false);
        }
        return true;
    }
}
