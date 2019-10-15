package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.donnees.BaseDeDonnees;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;
import ca.qc.cgmatane.foodwatcher.vue.ActiviteStock;

public class ControleurActiviteStock implements Controleur {
    public static final String NOM_DOSSIER = "Foodwatcher";
    private ActiviteStock vue;
    public static final int ADD_PRODUCT_ACTIVITY = 1;
    protected ProduitStockeDAO accesseurProduit;

    public ControleurActiviteStock(ActiviteStock vue){
        this.vue = vue;
    }

    public void actionNaviguerVueAjouterProduit(){
        vue.naviguerVueAjouterProduit();
    }



    @Override
    public void onCreate(Context applicationContext) {
        accesseurProduit = ProduitStockeDAO.getInstance();
        List<ProduitStocke> liste;
        liste  = accesseurProduit.recupererListeProduitStockeParIdStock(ControleurConteneurPrincipal.stockCourant.getIdStock());
        vue.setListeProduits(liste);
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
        System.out.println("ON ACTIVITY RESULT");
        switch (activite){
            case ADD_PRODUCT_ACTIVITY:
                vue.setListeProduits(accesseurProduit.recupererListeProduitStockeParIdStock(ControleurConteneurPrincipal.stockCourant.getIdStock()));
                vue.afficherProduits();
                break;
        }
    }

    public void actionSupprimer(int position){
        accesseurProduit = ProduitStockeDAO.getInstance();
        vue.supprimer(position);
        vue.setListeProduits(accesseurProduit.recupererListeProduitStockeParIdStock(ControleurConteneurPrincipal.stockCourant.getIdStock()));
        vue.afficherProduits();
    }

    @Override
    public void onBackPressed() {
    }

    private void refreshGallery(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_STARTED);
        intent.setData(Uri.fromFile(file));
        vue.sendBroadcast(intent);
    }

    public void exporterProduitsStockeEnXML(){

        List<ProduitStocke> listeProduitsStocke =
                ProduitStockeDAO.getInstance().recupererListeProduitStockeParIdStock(
                        ControleurConteneurPrincipal.stockCourant.getIdStock());

        String baliseStockOuvrante = "<stock>";
        String baliseStockFermante = "</stock>";

        String baliseProduitOuvrante = "<produit-stock>";
        String baliseProduitFermante = "</produit-stock>";

        String baliseIdProduitOuvrante = "<id-produit>";
        String baliseIdProduitFermante = "</id-produit>";

        String baliseGencodeOuvrante = "<gencode>";
        String baliseGencodeFermante = "</gencode>";

        String baliseEtiquetteOuvrante = "<etiquette>";
        String baliseEtiquetteFermante = "</etiquette>";

        String baliseIdUniteQuantiteOuvrante = "<id-unite-quantite>";
        String baliseIdUniteQuantiteFermante = "</id-unite-quantite>";

        String baliseIdCategorieProduitOuvrante = "<id-categorie-produit>";
        String baliseIdCategorieProduitFermante = "</id-categorie-produit>";

        String baliseIdStockOuvrante = "<id-stock>";
        String baliseIdStockFermante = "</id-stock>";

        String baliseQuantiteOuvrante = "<quantite>";
        String baliseQuantiteFermante = "</quantite>";

        String baliseIdEmplacementOuvrante = "<id-emplacement>";
        String baliseIdEmplacementFermante = "</id-emplacement>";

        String balisePresentListeCoursesOuvrante = "<present-liste-courses>";
        String balisePresentListeCoursesFermante = "</present-liste-courses>";


        String contenuXML = baliseStockOuvrante;

        for (ProduitStocke produitStocke : listeProduitsStocke) {

            contenuXML += baliseProduitOuvrante +
                    baliseIdProduitOuvrante + produitStocke.getIdProduit() + baliseIdProduitFermante +
                    baliseGencodeOuvrante + produitStocke.getGencode() + baliseGencodeFermante +
                    baliseEtiquetteOuvrante + produitStocke.getEtiquette() + baliseEtiquetteFermante +
                    baliseIdUniteQuantiteOuvrante + produitStocke.getUniteQuantite().getIdUniteQuantite() + baliseIdUniteQuantiteFermante +
                    baliseIdCategorieProduitOuvrante + produitStocke.getCategorieProduit().getIdCategorieProduit() + baliseIdCategorieProduitFermante +
                    baliseIdStockOuvrante + produitStocke.getStock().getIdStock() + baliseIdStockFermante +
                    baliseQuantiteOuvrante + produitStocke.getQuantite() + baliseQuantiteFermante +
                    baliseIdEmplacementOuvrante + produitStocke.getEmplacement().getIdEmplacement() + baliseIdEmplacementFermante +
                    balisePresentListeCoursesOuvrante + produitStocke.isPresentListeCourse() + balisePresentListeCoursesFermante +
                    baliseProduitFermante;

            System.out.println(contenuXML);

        }

        contenuXML += baliseStockFermante;

        saveData(contenuXML);


    }

    public void saveData(String data) {
        File fichier = null;
        try {

            File file = vue.getExternalFilesDir(Environment.DIRECTORY_DCIM);
            File chemin = new File(file, NOM_DOSSIER);


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = simpleDateFormat.format(new Date());
            String name = NOM_DOSSIER + "_" + date + ".xml";

            fichier = new File(chemin, name);

            boolean succesChemin=false;
            boolean succesFichier=false;
            System.out.println(">>>>>>>>>>>>>>>>>>>> t");

            if (!chemin.exists()){
                succesChemin = chemin.mkdirs();
                System.out.println(">>>>>>>>>>>>>>>>>>>> chemin");
            }

            if (!fichier.exists()){
                succesFichier = fichier.createNewFile();
                System.out.println(">>>>>>>>>>>>>>>>>>>> fichier");
            }

            if (succesFichier){
                System.out.println(">>>>>>>>>>>>>>>>>>>> both");
                FileWriter filewriter = new FileWriter(fichier,false);
                filewriter.write(data);
                filewriter.close();
            }

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        refreshGallery(fichier);
    }


}
