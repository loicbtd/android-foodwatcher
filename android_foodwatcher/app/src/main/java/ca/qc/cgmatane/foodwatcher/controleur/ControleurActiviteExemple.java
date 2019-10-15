package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteExemple;

public class ControleurActiviteExemple implements Controleur, Toolbar.OnMenuItemClickListener {

    protected ActiviteExemple vue;

    public ControleurActiviteExemple(ActiviteExemple vue) {
        this.vue = vue;
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
            case R.id.activite_stock_barre_outil_action_exporter_stock:
                vue.getTextView().setText("Hello");
                break;
            case R.id.activite_exemple_barre_outil_action_ecrire_world:
                vue.getTextView().setText("world !");
                break;
            default:
                return false;
        }
        return true;
    }
}
