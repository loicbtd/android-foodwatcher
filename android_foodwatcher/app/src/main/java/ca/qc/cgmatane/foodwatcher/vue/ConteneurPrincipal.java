package ca.qc.cgmatane.foodwatcher.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurConteneurPrincipal;
import ca.qc.cgmatane.foodwatcher.donnees.StockDAO;
import ca.qc.cgmatane.foodwatcher.modele.Stock;

public class ConteneurPrincipal extends AppCompatActivity {

    protected ControleurConteneurPrincipal controleurConteneurPrincipal = new ControleurConteneurPrincipal(this);

    protected Toolbar barreOutil;
    protected int ressourceMenuBarreOutil;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected ConstraintLayout constraintLayout;

    protected List<Stock> listeStock;

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }
    public NavigationView getNavigationView() {
        return navigationView;
    }
    public ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }
    public List<Stock> getListeStock() {
        return listeStock;
    }
    public void setListeStock(List<Stock> listeStock) {
        this.listeStock = listeStock;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conteneur_principal);

        barreOutil = (Toolbar) findViewById(R.id.activity_master_toolbar);
        setSupportActionBar(barreOutil);

        // Configuration Drawer Layout
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_master_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, barreOutil, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Configuration NavigationView
        navigationView = (NavigationView) findViewById(R.id.activity_master_nav_view);
        navigationView.setNavigationItemSelectedListener(controleurConteneurPrincipal);

        controleurConteneurPrincipal.onCreate(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        controleurConteneurPrincipal.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controleurConteneurPrincipal.onActivityResult(requestCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(ressourceMenuBarreOutil != 0) {
            getMenuInflater().inflate(ressourceMenuBarreOutil, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    public void configureToolbarMenu(int ressourceMenuBarreOutil) {
        this.ressourceMenuBarreOutil = ressourceMenuBarreOutil;
    }

    /**
     * chargement du constraint layout dans le conteneur principal
     * @param ressource id du constraint layout
     */
    public void configureActivityContent(int ressource) {
        constraintLayout = findViewById(R.id.activity_master_constraint_layout);
        constraintLayout.addView(LayoutInflater.from(this).inflate(ressource, null));
    }

    /**
     * peupler la liste des stocks dans le menu du drawer
     */
    public void peuplerListeStockDansMenuDrawer() {
        listeStock = StockDAO.getInstance().recupererListeStock();
        Menu menu = navigationView.getMenu();
        MenuItem itemMenu = menu.findItem(R.id.conteneur_principal_drawer_rubrique_stock);
        SubMenu sousMenuStock = itemMenu.getSubMenu();
        sousMenuStock.clear();
        for (int i = 0; i < listeStock.size(); i++) {
            sousMenuStock.add(0, listeStock.get(i).getIdStock(), i, listeStock.get(i).getEtiquette());
            sousMenuStock.getItem(i).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_home));
            if (listeStock.get(i).getIdStock() == ControleurConteneurPrincipal.stockCourant.getIdStock()) {
                sousMenuStock.getItem(i).setChecked(true);
            }
        }
    }
}