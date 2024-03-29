package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

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
        stockDAO = StockDAO.getInstance();
        vue.setListeStock(stockDAO.recupererListeStock());
        Intent intent;
        int itemId;

        // récupération de l'id de l'item sélectionné
        itemId = item.getItemId();

//        Toast.makeText(vue.getApplicationContext(),
//                "itemId = "+itemId + "\n" +
//                        "stockCourant.getIdStock()"+stockCourant.getIdStock(),
//                Toast.LENGTH_SHORT).show();

        switch (itemId) {
            // lancer ActiviteExemple
            case R.id.conteneur_principal_drawer_action_naviguer_exemple:
                intent = new Intent(vue.getApplicationContext(), ActiviteExemple.class);
                vue.startActivityForResult(intent, ACTIVITE_EXEMPLE);
                quitterMenuDrawerApresSelectionItem();
                return true;

            // lancer ActiviteAjouterStock
            case R.id.conteneur_principal_drawer_action_naviguer_ajouter_stock:
                intent = new Intent(vue.getApplicationContext(), ActiviteAjouterStock.class);
                vue.startActivityForResult(intent, ACTIVITE_AJOUTER_STOCK);
                quitterMenuDrawerApresSelectionItem();
                return true;

            // lancer ActiviteTrouverMagasin
            case R.id.conteneur_principal_drawer_action_naviguer_carte_magasin:
                intent = new Intent(vue.getApplicationContext(), ActiviteTrouverMagasin.class);
                vue.startActivityForResult(intent, ACTIVITY_CARTE_MAGASIN);
                quitterMenuDrawerApresSelectionItem();
                return true;

            // lancer ActiviteListeCourse
            case R.id.conteneur_principal_drawer_action_naviguer_liste_course:
                intent = new Intent(vue.getApplicationContext(), ActiviteListeCourse.class);
                vue.startActivity(intent);
                quitterMenuDrawerApresSelectionItem();
                return true;
        }

        for (Stock stock : vue.getListeStock()) {
            if(stock.getIdStock() == itemId) {
                stockCourant.setIdStock(itemId);
                intent = new Intent(vue.getApplicationContext(), ActiviteStock.class);
                vue.startActivity(intent);
                quitterMenuDrawerApresSelectionItem();
                return true;
            }
        }
        vue.getDrawerLayout().closeDrawer(GravityCompat.START);
        return false;
    }

    public void quitterMenuDrawerApresSelectionItem() {
        vue.getDrawerLayout().closeDrawer(GravityCompat.START);
        Menu menu = vue.getNavigationView().getMenu();
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setChecked(false);
            menu.findItem(stockCourant.getIdStock()).setChecked(true);
        }
        vue.peuplerListeStockDansMenuDrawer();
    }
}
