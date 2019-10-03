package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.qc.cgmatane.foodwatcher.R;

public class AjouterMaison extends ActiviteMaitresse {
    Button boutonAjouterMaison;
    Button boutonAnnuler;
    //TODO: create and add controller as attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.vue_ajouter_maison);
//        super.configureToolbarMenu(R.menu.vue_exemple_toolbar);
//        toolbar.setOnMenuItemClickListener();
        boutonAjouterMaison = findViewById(R.id.bouton_ajouter_maison);
        boutonAjouterMaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(this, ActiviteMaitresse.class));
            }
        });
        boutonAnnuler = findViewById(R.id.bouton_annuler_maison);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(this, ActiviteMaitresse.class));
            }
        });
        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_add_home).setChecked(true); //TODO: improve check verification system
        // TODO: call the controller onCreate method
    }
}
