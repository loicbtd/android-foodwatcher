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
import ca.qc.cgmatane.foodwatcher.donnees.CategorieProduitDAO;
import ca.qc.cgmatane.foodwatcher.donnees.EmplacementDAO;
import ca.qc.cgmatane.foodwatcher.donnees.UniteQuantiteDAO;
import ca.qc.cgmatane.foodwatcher.modele.CategorieProduit;
import ca.qc.cgmatane.foodwatcher.modele.Emplacement;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.activite_ajouter_produit);
        controleur = new ControleurActiviteAjouterProduit(this);
        boutonAjouterProduit = findViewById(R.id.btn_view_add_product_action_add);
        boutonRetour = findViewById(R.id.btn_view_add_product_action_cancel);
        textFieldIntitule = findViewById(R.id.intitule_produit_edit_text);
        textFieldQuantite = findViewById(R.id.edit_text_quantite);
        textFieldCodeBarre = findViewById(R.id.code_barre_edit_text);
        boutonScanner = findViewById(R.id.buton_vue_ajouter_produit_action_scanner);
        checkBoxAjouterListeCourse = findViewById(R.id.checkbox_ajouter_liste_course_vue_ajouter_produit);
        boutonScanner = findViewById(R.id.buton_vue_ajouter_produit_action_scanner);


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


                enregistrerProduit();
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

                String barcode = null;
                if (data != null) {
                    //TODO Récupérer les autres données
                    barcode = data.getStringExtra("code");

                    //TODO tester si elles sont nulles : si oui alors ecrire le gencode seulement et sinon tous les champs
                }

                Toast.makeText(getApplicationContext(),barcode,Toast.LENGTH_LONG).show();

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

    private void enregistrerProduit(){
        //recuperer la valeur d'un spinner:
//        String uniteQuantite;
//        uniteQuantite = String.valueOf(choixUniteQuantite.getSelectedItem());
//        ProduitStocke produit = new ProduitStocke(1, textFieldCodeBarre.getText().toString(), textFieldIntitule.getText().toString(), 1, 1, 1, Double.parseDouble(textFieldQuantite.getText().toString()),1,checkBoxAjouterListeCourse.isSelected());
//        Produit produit = new ProduitStocke(1, textFieldIntitule.getText().toString(),);
    }

    public void naviguerRetourMaison(){
        this.finish();
    }

    @Override
    public void scanner() {
        //TODO : definire l'action a realiser lors de l'appui du bouton;

        Toast.makeText(getApplicationContext(),"HERE",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(ActiviteAjouterProduit.this, ActiviteScan.class);

        startActivityForResult(intent, RESULTAT_ACTIVITE_SCAN);
    }

}