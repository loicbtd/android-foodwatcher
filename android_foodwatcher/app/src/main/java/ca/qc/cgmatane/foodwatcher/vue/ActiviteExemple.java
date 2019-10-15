package ca.qc.cgmatane.foodwatcher.vue;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteExemple;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurConteneurPrincipal;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;

public class ActiviteExemple extends ConteneurPrincipal implements ActiviteExempleVue {

    ControleurActiviteExemple ControleurActiviteExemple = new ControleurActiviteExemple(this);

    private TextView textView;

    public TextView getTextView() {
        return textView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.activite_exemple);
        super.configureToolbarMenu(R.menu.activite_exemple_barre_outil);

        textView = (TextView) findViewById(R.id.view_sample_textview);

        barreOutil.setOnMenuItemClickListener(ControleurActiviteExemple);

        navigationView.getMenu().findItem(R.id.conteneur_principal_drawer_action_naviguer_exemple).setChecked(true); //TODO: improve check verification system
        ControleurActiviteExemple.onCreate(getApplicationContext());
    }

    private void refreshGallery(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
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

            File file = getExternalFilesDir(Environment.DIRECTORY_DCIM);
            File chemin = new File(file,"Demo");


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = simpleDateFormat.format(new Date());
            String name = "Foodwatcher_"+date+".xml";

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

            if (!(succesChemin || succesFichier)){
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
