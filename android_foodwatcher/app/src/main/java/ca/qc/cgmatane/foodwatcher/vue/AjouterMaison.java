package ca.qc.cgmatane.foodwatcher.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurAjouterMaison;

public class AjouterMaison extends ActiviteMaitresse implements AjouterMaisonVue {
    Button boutonAjouterMaison;
    Button boutonAnnuler;
    ControleurAjouterMaison controleur;
    //TODO: create and add controller as attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.vue_ajouter_maison);
//        super.configureToolbarMenu(R.menu.vue_exemple_toolbar);
//        toolbar.setOnMenuItemClickListener();
        controleur = new ControleurAjouterMaison(this);
        boutonAjouterMaison = findViewById(R.id.bouton_ajouter_maison);
        boutonAjouterMaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controleur.actionRetourActiviteMaitresse();
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

    public void retourActiviteMaitresse(){
        this.finish();
    }

}
