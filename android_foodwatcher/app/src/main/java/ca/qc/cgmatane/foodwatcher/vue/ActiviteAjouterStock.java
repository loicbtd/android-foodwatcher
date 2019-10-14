package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteAjouterStock;
import ca.qc.cgmatane.foodwatcher.modele.Stock;

public class ActiviteAjouterStock extends ConteneurPrincipal implements ActiviteAjouterStockVue {
    Button boutonAjouterMaison;
    Button boutonAnnuler;
    TextInputEditText textFieldEtiquetteStock;
    ControleurActiviteAjouterStock controleur;
    //TODO: create and add controller as attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.activite_ajouter_maison);
//        super.configureToolbarMenu(R.menu.vue_exemple_toolbar);
//        toolbar.setOnMenuItemClickListener();
        controleur = new ControleurActiviteAjouterStock(this);
        boutonAjouterMaison = findViewById(R.id.bouton_ajouter_maison);
        textFieldEtiquetteStock = findViewById(R.id.intitule_maison_edit_text);
        boutonAjouterMaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                enregistrerStock();
            }
        });
        boutonAnnuler = findViewById(R.id.bouton_annuler_maison);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controleur.actionRetourActiviteMaitresse();
            }
        });
        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_add_home).setChecked(true); //TODO: improve check verification system
        // TODO: call the controller onCreate method
    }

    public void enregistrerStock(){
        Stock stock = new Stock(0,textFieldEtiquetteStock.getText().toString() );
        controleur.actionEnregistrerStock(stock);
    }

    public void retourActiviteMaitresse(){
        this.finish();
    }

}
