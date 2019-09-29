package ca.qc.cgmatane.foodwatcher.controller;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.data.DataBase;
import ca.qc.cgmatane.foodwatcher.data.HomeDAO;
import ca.qc.cgmatane.foodwatcher.view.AddHomeActivity;
import ca.qc.cgmatane.foodwatcher.view.FindStoreActivity;
import ca.qc.cgmatane.foodwatcher.view.MasterActivity;
import ca.qc.cgmatane.foodwatcher.view.SampleActivity;

public class MasterActivityController implements Controller, NavigationView.OnNavigationItemSelectedListener {

    static final public int ACTIVITY_SAMPLE = -1;
    static final public int ACTIVITY_HOME = 1;
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
        view.getDrawerLayout().closeDrawer(GravityCompat.START);

        Intent intent;
        switch (item.getItemId()) {
            case R.id.activity_master_drawer_action_display_sample:
                intent = new Intent(view.getApplicationContext(), SampleActivity.class);
                view.startActivityForResult(intent, ACTIVITY_SAMPLE);
                break;
            case R.id.activity_master_drawer_action_add_home:
                intent = new Intent(view.getApplicationContext(), AddHomeActivity.class);
                view.startActivityForResult(intent, ACTIVITY_ADD_HOME);
                break;
            case R.id.activity_master_drawer_action_find_store:
                intent = new Intent(view.getApplicationContext(), FindStoreActivity.class);
                view.startActivityForResult(intent, ACTIVITY_FIND_STORE);
                break;
            default:
                return false;
        }
        Menu menu = view.getNavigationView().getMenu();
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setChecked(false);
        }
        return true;
    }
}
