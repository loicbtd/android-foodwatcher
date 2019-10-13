package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;
import android.widget.TextView;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurExemple;

public class ActiviteActiviteExemple extends ActiviteMaitresse implements ActiviteExempleVue {

    ControleurExemple sampleActivityController = new ControleurExemple(this);

    private TextView textView;

    public TextView getTextView() {
        return textView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.vue_exemple);
        super.configureToolbarMenu(R.menu.vue_exemple_toolbar);

        textView = (TextView) findViewById(R.id.view_sample_textview);

        toolbar.setOnMenuItemClickListener(sampleActivityController);

        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_display_sample).setChecked(true); //TODO: improve check verification system
        sampleActivityController.onCreate(getApplicationContext());
    }
}
