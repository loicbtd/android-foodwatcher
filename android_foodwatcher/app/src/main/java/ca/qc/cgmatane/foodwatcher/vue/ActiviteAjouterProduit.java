package ca.qc.cgmatane.foodwatcher.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteAjouterProduit;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurConteneurPrincipal;
import ca.qc.cgmatane.foodwatcher.donnees.CategorieProduitDAO;
import ca.qc.cgmatane.foodwatcher.donnees.EmplacementDAO;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitDAO;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.donnees.UniteQuantiteDAO;
import ca.qc.cgmatane.foodwatcher.modele.CategorieProduit;
import ca.qc.cgmatane.foodwatcher.modele.Emplacement;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;
import ca.qc.cgmatane.foodwatcher.modele.UniteQuantite;

public class ActiviteAjouterProduit extends ConteneurPrincipal implements ActiviteAjouterProduitVue {

    public static final int RESULTAT_ACTIVITE_SCAN = 1;

    TextInputEditText textFieldIntitule;
    TextInputEditText textFieldQuantite;
    TextInputEditText textFieldCodeBarre;
    MaterialCheckBox checkBoxAjouterListeCourse;
    Spinner choixUniteQuantite;
    Spinner choixCategorieProduit;
    Spinner choixEmplacement;
    MaterialButton boutonAjouterProduit;
    MaterialButton boutonRetour;
    MaterialButton boutonScanner;
    ControleurActiviteAjouterProduit controleur;
    ProduitStockeDAO accesseurProduitStockeDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.activite_ajouter_produit);
        accesseurProduitStockeDAO = ProduitStockeDAO.getInstance();
        controleur = new ControleurActiviteAjouterProduit(this);
        boutonAjouterProduit = findViewById(R.id.btn_view_add_product_action_add);
        boutonRetour = findViewById(R.id.btn_view_add_product_action_cancel);
        textFieldIntitule = findViewById(R.id.intitule_produit_edit_text);
        textFieldQuantite = findViewById(R.id.edit_text_quantite);
        textFieldCodeBarre = findViewById(R.id.code_barre_edit_text);
        boutonScanner = findViewById(R.id.buton_vue_ajouter_produit_action_scanner);
        checkBoxAjouterListeCourse = findViewById(R.id.checkbox_ajouter_liste_course_vue_ajouter_produit);


        choixUniteQuantite = findViewById(R.id.choix_unite_quantite);
        List<String> unites = new ArrayList<>();
        List<UniteQuantite> listeUniteQuantite = UniteQuantiteDAO.getInstance().recupererListeUniteQuantite();
        for (UniteQuantite uniteQuantite : listeUniteQuantite) {
            unites.add(uniteQuantite.getEtiquette());
        }
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,unites);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choixUniteQuantite.setAdapter(dataAdapterR);


        choixCategorieProduit = findViewById(R.id.choix_categorie_produit);
        List<String> categories = new ArrayList<>();
        List<CategorieProduit> listeCategorie = CategorieProduitDAO.getInstance().recupererListeCategorieProduit();
        for (CategorieProduit categorieProduit : listeCategorie) {
            categories.add(categorieProduit.getEtiquette());
        }
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choixCategorieProduit.setAdapter(adapterCategories);

        choixEmplacement = findViewById(R.id.choix_emplacement);
        List<String> emplacements = new ArrayList<>();
        List<Emplacement> listeEmplacements = EmplacementDAO.getInstance().recupererListeEmplacement();
        for (Emplacement emplacement : listeEmplacements) {
            emplacements.add(emplacement.getEtiquette());
        }
        ArrayAdapter<String> adapteurEmplacement = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,emplacements);
        adapteurEmplacement.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choixEmplacement.setAdapter(adapteurEmplacement);

        controleur.onCreate(getApplicationContext());

        boutonAjouterProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(textFieldIntitule.getText());
                System.out.println(textFieldQuantite.getText());
                System.out.println(textFieldCodeBarre.getText());


                controleur.actionEnregistrerProduit();
            }
        });
        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controleur.retourVerStockAnnuler();
            }
        });
        boutonScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controleur.scanner();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                String gencode = null;
                String nomProduit = null;

                if (data != null) {
                    gencode = data.getStringExtra("code");
                    nomProduit = data.getStringExtra("etiquette");


                    textFieldCodeBarre.setText(gencode);
                    textFieldIntitule.setText(nomProduit);
                }

                Toast.makeText(getApplicationContext(),nomProduit,Toast.LENGTH_LONG).show();

            }

        }
    }

    @Override
    public void naviguerVersStockAnnuler(){
        Intent intent = new Intent(getApplicationContext(), ActiviteStock.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {

        super.onResume();

    }

    public void enregistrerProduit(){
        UniteQuantite unite = new UniteQuantite(1,"test");
        CategorieProduit categorie = new CategorieProduit(1, "test");
        Emplacement emplacement = new Emplacement(1, "test");
        List<UniteQuantite> listeUniteQuantite = UniteQuantiteDAO.getInstance().recupererListeUniteQuantite();
        for (int i = 0; i < listeUniteQuantite.size(); i++) {
            if (choixUniteQuantite.getSelectedItem().toString().equals((String) listeUniteQuantite.get(i).getEtiquette())){
                unite = listeUniteQuantite.get(i);
                System.out.println(unite.getEtiquette());
            }
        }
        List<CategorieProduit> listeCategories = CategorieProduitDAO.getInstance().recupererListeCategorieProduit();
        for (int i = 0; i < listeCategories.size(); i++) {
            if (choixCategorieProduit.getSelectedItem().toString().equals((String) listeCategories.get(i).getEtiquette())){
                categorie = listeCategories.get(i);
                System.out.println(categorie.getEtiquette());
            }
        }
        List<Emplacement> listeEmplacements = EmplacementDAO.getInstance().recupererListeEmplacement();
        for (int i = 0; i < listeEmplacements.size(); i++) {
            if (choixEmplacement.getSelectedItem().toString().equals((String) listeEmplacements.get(i).getEtiquette())){
                emplacement = listeEmplacements.get(i);
                System.out.println(emplacement.getEtiquette());
            }
        }

        Produit produit = new Produit(0, textFieldCodeBarre.getText().toString(), textFieldIntitule.getText().toString(), unite, categorie);
        ProduitDAO.getInstance().ajouterProduit(produit);
        int id = ProduitDAO.getInstance().recupererListeProduit().size();
        produit.setIdProduit(id);
        ProduitStocke produitStocke = new ProduitStocke(produit, ControleurConteneurPrincipal.stockCourant, emplacement,Double.parseDouble(textFieldQuantite.getText().toString()), checkBoxAjouterListeCourse.isChecked());
        accesseurProduitStockeDAO = ProduitStockeDAO.getInstance();
        accesseurProduitStockeDAO.ajouterProduitAuStock(produitStocke);
    }

    public void naviguerRetourMaison(){
        this.finish();
    }

    @Override
    public void scanner() {
//        Toast.makeText(getApplicationContext(),"HERE",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(ActiviteAjouterProduit.this, ActiviteScan.class);
        startActivityForResult(intent, RESULTAT_ACTIVITE_SCAN);
    }

}