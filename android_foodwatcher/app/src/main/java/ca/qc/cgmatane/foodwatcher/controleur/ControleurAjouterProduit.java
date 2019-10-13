package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.content.Intent;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonneesDAO;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitDAO;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.vue.AjouterProduit;
import ca.qc.cgmatane.foodwatcher.vue.Stock;

public class ControleurAjouterProduit implements Controleur {
    private AjouterProduit vue;
    protected ProduitDAO accesseurProduit;

    public ControleurAjouterProduit(AjouterProduit vue){
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonneesDAO.getInstance(applicationContext);
        accesseurProduit = ProduitDAO.getInstance();
    }

    public void actionEnregistrerProduit(Produit produit){
        accesseurProduit = ProduitDAO.getInstance();
        accesseurProduit.ajouterProduit(produit);
        vue.naviguerRetourMaison();
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
