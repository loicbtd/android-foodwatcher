package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteExemple;

public class ControleurActiviteExemple implements Controleur, Toolbar.OnMenuItemClickListener {

    protected ActiviteExemple view;

    public ControleurActiviteExemple(ActiviteExemple view) {
        this.view = view;
    }

    @Override
    public void onCreate(Context applicationContext) {

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

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_sample_toolbar_action_write_hello:
                view.getTextView().setText("Hello");
                break;
            case R.id.view_sample_toolbar_action_write_world:
                view.getTextView().setText("world !");
                break;
            default:
                return false;
        }
        return true;
    }
}
