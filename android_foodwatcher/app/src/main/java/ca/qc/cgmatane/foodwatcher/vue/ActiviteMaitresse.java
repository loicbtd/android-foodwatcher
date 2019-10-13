package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteMaitresse;
import ca.qc.cgmatane.foodwatcher.modele.Maison;

public class ActiviteMaitresse extends AppCompatActivity {

    protected ControleurActiviteMaitresse controleurActiviteMaitresse = new ControleurActiviteMaitresse(this);

    protected Toolbar toolbar;
    protected int menuToolbarResource;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected ConstraintLayout constraintLayout;

    protected List<Maison> listMaison;

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }
    public NavigationView getNavigationView() {
        return navigationView;
    }
    public ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }
    public List<Maison> getListMaison() {
        return listMaison;
    }
    public void setListMaison(List<Maison> listMaison) {
        this.listMaison = listMaison;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_maitresse_drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.activity_master_toolbar);
        setSupportActionBar(toolbar);

        // Configure Drawer Layout
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_master_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configure NavigationView
        navigationView = (NavigationView) findViewById(R.id.activity_master_nav_view);
        navigationView.setNavigationItemSelectedListener(controleurActiviteMaitresse);

        controleurActiviteMaitresse.onCreate(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        controleurActiviteMaitresse.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(menuToolbarResource != 0) {
            getMenuInflater().inflate(menuToolbarResource, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    public void configureToolbarMenu(int menuToolbarResource) {
        this.menuToolbarResource = menuToolbarResource;
    }

    /**
     * load constraint layout into the master activity
     * @param resource constraint layout id
     */
    public void configureActivityContent(int resource) {
        constraintLayout = findViewById(R.id.activity_master_constraint_layout);
        constraintLayout.addView(LayoutInflater.from(this).inflate(resource, null));
    }

    /**
     * populate home section in menu drawer
     */
    public void populateHomeInMenuDrawer() {
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.activity_master_drawer_section_home);
        SubMenu subMenuHome = menuItem.getSubMenu();
        for (int i = 0; i < listMaison.size(); i++) {
            subMenuHome.add(0, i, i, listMaison.get(i).getEtiquette());
            subMenuHome.getItem(i).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_home));
        }
    }
}