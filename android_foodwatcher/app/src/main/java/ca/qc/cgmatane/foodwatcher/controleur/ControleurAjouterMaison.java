package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ca.qc.cgmatane.foodwatcher.vue.ActiviteAjouterMaison;


public class ControleurAjouterMaison implements Controleur {

    private ActiviteAjouterMaison vue;

    public ControleurAjouterMaison(ActiviteAjouterMaison vue) {
       this.vue = vue;
    }

    public void actionRetourActiviteMaitresse(){
        vue.retourActiviteMaitresse();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
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
}
