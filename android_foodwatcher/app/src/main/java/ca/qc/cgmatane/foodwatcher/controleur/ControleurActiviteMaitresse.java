package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.donnees.DataBase;
import ca.qc.cgmatane.foodwatcher.donnees.HomeDAO;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteMaitresse;
import ca.qc.cgmatane.foodwatcher.vue.AjouterMaison;
import ca.qc.cgmatane.foodwatcher.vue.ListeDeCourse;
import ca.qc.cgmatane.foodwatcher.vue.TrouverMagasin;
import ca.qc.cgmatane.foodwatcher.vue.Exemple;
import ca.qc.cgmatane.foodwatcher.vue.Stock;

public class ControleurActiviteMaitresse implements Controleur, NavigationView.OnNavigationItemSelectedListener {

    static final public int ACTIVITY_SAMPLE = -1;
    static final public int ACTIVITY_STOCK = 1;
    static final public int ACTIVITY_ADD_HOME = 2;
    static final public int ACTIVITY_FIND_STORE = 3;

    protected static int currentHome;

    protected ActiviteMaitresse view;

    protected HomeDAO homeDAO;

    public ControleurActiviteMaitresse(ActiviteMaitresse view) {
        this.view = view;
    }

    @Override
    public void onCreate(Context applicationContext) {
        DataBase.getInstance(applicationContext);
        homeDAO = HomeDAO.getInstance();
        view.setListHome(homeDAO.pickupListHome());
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
        if (0 <= itemId && itemId < view.getListHome().size()) {
            if (itemId != currentHome) {
                itemId = currentHome;
                intent = new Intent(view.getApplicationContext(), Stock.class);
                view.startActivityForResult(intent, ACTIVITY_STOCK);
            }
        } // else if it corresponds to another activity
        else {
            switch (itemId) {
                    // start Exemple
                case R.id.activity_master_drawer_action_display_sample:
                    intent = new Intent(view.getApplicationContext(), Exemple.class);
                    view.startActivityForResult(intent, ACTIVITY_SAMPLE);
                    break;
                    // start AjouterMaison
                case R.id.activity_master_drawer_action_add_home:
                    intent = new Intent(view.getApplicationContext(), AjouterMaison.class);
                    view.startActivityForResult(intent, ACTIVITY_ADD_HOME);
                    break;
                    // start TrouverMagasin
                case R.id.activity_master_drawer_action_find_store:
                    intent = new Intent(view.getApplicationContext(), TrouverMagasin.class);
                    view.startActivityForResult(intent, ACTIVITY_FIND_STORE);
                    break;
                case R.id.activity_master_drawer_action_display_shopping_list:
                    intent = new Intent(view.getApplicationContext(), ListeDeCourse.class);
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
