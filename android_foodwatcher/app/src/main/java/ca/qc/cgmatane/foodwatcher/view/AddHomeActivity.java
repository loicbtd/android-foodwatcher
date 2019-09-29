package ca.qc.cgmatane.foodwatcher.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

import ca.qc.cgmatane.foodwatcher.R;

public class AddHomeActivity extends MasterActivity {
    Button boutonAjouterMaison;
    Button boutonAnnuler;
    //TODO: create and add controller as attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.view_add_home);
//        super.configureToolbarMenu(R.menu.view_sample_toolbar);
//        toolbar.setOnMenuItemClickListener();
        boutonAjouterMaison = findViewById(R.id.bouton_ajouter_maison);
        boutonAjouterMaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(this, MasterActivity.class));
            }
        });
        boutonAnnuler = findViewById(R.id.bouton_annuler_maison);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(this, MasterActivity.class));
            }
        });
        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_add_home).setChecked(true); //TODO: improve check verification system
        // TODO: call the controller onCreate method
    }
}
