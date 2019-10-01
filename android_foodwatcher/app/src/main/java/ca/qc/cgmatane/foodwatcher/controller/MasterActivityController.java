package ca.qc.cgmatane.foodwatcher.controller;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.data.DataBase;
import ca.qc.cgmatane.foodwatcher.data.HomeDAO;
import ca.qc.cgmatane.foodwatcher.view.AddHomeActivity;
import ca.qc.cgmatane.foodwatcher.view.FindStoreActivity;
import ca.qc.cgmatane.foodwatcher.view.MasterActivity;
import ca.qc.cgmatane.foodwatcher.view.SampleActivity;
import ca.qc.cgmatane.foodwatcher.view.StockActivity;

public class MasterActivityController implements Controller, NavigationView.OnNavigationItemSelectedListener {

    static final public int ACTIVITY_SAMPLE = -1;
    static final public int ACTIVITY_STOCK = 1;
    static final public int ACTIVITY_ADD_HOME = 2;
    static final public int ACTIVITY_FIND_STORE = 3;

    protected MasterActivity view;

    protected HomeDAO homeDAO;

    public MasterActivityController(MasterActivity view) {
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
            intent = new Intent(view.getApplicationContext(), StockActivity.class);
            view.startActivityForResult(intent, ACTIVITY_STOCK);
        } // else if it corresponds to another activity
        else {
            switch (itemId) {
                    // start SampleActivity
                case R.id.activity_master_drawer_action_display_sample:
                    intent = new Intent(view.getApplicationContext(), SampleActivity.class);
                    view.startActivityForResult(intent, ACTIVITY_SAMPLE);
                    break;
                    // start AddHomeActivity
                case R.id.activity_master_drawer_action_add_home:
                    intent = new Intent(view.getApplicationContext(), AddHomeActivity.class);
                    view.startActivityForResult(intent, ACTIVITY_ADD_HOME);
                    break;
                    // start FindStoreActivity
                case R.id.activity_master_drawer_action_find_store:
                    intent = new Intent(view.getApplicationContext(), FindStoreActivity.class);
                    view.startActivityForResult(intent, ACTIVITY_FIND_STORE);
                    break;
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
