package ca.qc.cgmatane.foodwatcher.view;

import android.os.Bundle;

import ca.qc.cgmatane.foodwatcher.R;

public class FindStoreActivity extends MasterActivity {

    //TODO: create and add controller as attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.view_find_store);
//        super.configureToolbarMenu(R.menu.view_sample_toolbar);
//        toolbar.setOnMenuItemClickListener();

        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_find_store).setChecked(true); //TODO: improve check verification system
        // TODO: call the controller onCreate method
    }
}
