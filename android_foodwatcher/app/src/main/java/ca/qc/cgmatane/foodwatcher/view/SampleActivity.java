package ca.qc.cgmatane.foodwatcher.view;

import android.os.Bundle;
import android.widget.TextView;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controller.SampleActivityController;

public class SampleActivity extends MasterActivity {

    SampleActivityController sampleActivityController = new SampleActivityController(this);

    private TextView textView;

    public TextView getTextView() {
        return textView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.view_sample);
        super.configureToolbarMenu(R.menu.view_sample_toolbar);

        textView = (TextView) findViewById(R.id.view_sample_textview);

        toolbar.setOnMenuItemClickListener(sampleActivityController);

        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_display_sample).setChecked(true); //TODO: improve check verification system
        sampleActivityController.onCreate(getApplicationContext());
    }
}
