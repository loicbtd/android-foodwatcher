package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteListeCourse;

public class ControleurActiviteListeDeCourse implements Controleur {
    protected ActiviteListeCourse vue;
    protected ProduitStockeDAO accesseurProduit;
    public ControleurActiviteListeDeCourse(ActiviteListeCourse vue){
        this.vue = vue;
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        accesseurProduit = ProduitStockeDAO.getInstance();
        //TODO: trouver un moyen de recuperer id du stock (avec extra dans intent)
        List<ProduitStocke> listeProduitsAffiches = new ArrayList<>();
        List<ProduitStocke> listeProduits = accesseurProduit.recupererListeProduitStockeParIdStock(ControleurConteneurPrincipal.stockCourant.getIdStock());
        for (int i = 0; i < listeProduits.size() ; i++) {
            System.out.println(listeProduits.get(i).getEtiquette()+"////////////////////////////////////////////////////"+listeProduits.get(i).isPresentListeCourse());
            if (listeProduits.get(i).isPresentListeCourse()){
                listeProduitsAffiches.add(listeProduits.get(i));
            }
        }
        vue.setListeProduits(listeProduitsAffiches);
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

    public void actionSupprimerSelection() {
        vue.supprimerSelection();
    }
}
