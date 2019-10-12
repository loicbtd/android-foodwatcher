package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.content.Intent;

import ca.qc.cgmatane.foodwatcher.vue.AjouterProduit;
import ca.qc.cgmatane.foodwatcher.vue.Stock;

public class ControleurAjouterProduit implements Controleur {
    private AjouterProduit vue;

    public ControleurAjouterProduit(AjouterProduit vue){
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {

    }

    public void retourVerStockAnnuler(){
        vue.naviguerVersStockAnnuler();
    }

    public void naviguerVersStockAjouter(){

        vue.naviguerRetourMaison();
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
