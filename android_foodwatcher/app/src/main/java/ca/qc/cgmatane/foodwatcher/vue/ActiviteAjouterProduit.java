package ca.qc.cgmatane.foodwatcher.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteAjouterProduit;
import ca.qc.cgmatane.foodwatcher.modele.Produit;

public class ActiviteAjouterProduit extends ConteneurPrincipal implements ActiviteAjouterProduitVue {

    TextInputEditText textFieldIntitule;
    TextInputEditText textFieldQuantite;
    TextInputEditText textFieldCodeBarre;
    CheckBox checkBoxAjouterListeCourse;
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
//        imageViewProduct = (ImageView) findViewById(R.id.view_add_product_img);

        choixUniteQuantite = findViewById(R.id.choix_unite_quantite);
        String[] unites={"Kg","G", "L", "Oz", "unité"};
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,unites);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choixUniteQuantite.setAdapter(dataAdapterR);

        choixCategorieProduit = findViewById(R.id.choix_categorie_produit);
        String[] categories={"liquide","Viande", "Légume", "Fruit", "Féculent"};
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choixCategorieProduit.setAdapter(adapterCategories);

        choixEmplacement = findViewById(R.id.choix_emplacement);
        String[] emplacaments={"Cuisine","Cave", "Frigo"};
        ArrayAdapter<String> adapteurEmplacement = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,emplacaments);
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
        Produit produit = new Produit(1, textFieldCodeBarre.getText().toString(), textFieldIntitule.getText().toString(), 1,1, 1, Double.parseDouble(textFieldQuantite.getText().toString()),1,checkBoxAjouterListeCourse.isSelected()  );
        controleur.actionEnregistrerProduit(produit);
    }

    public void naviguerRetourMaison(){
        this.finish();
    }

    @Override
    public void scanner() {
        //TODO : definire l'action a realiser lors de l'appui du bouton;
        System.out.println("####################################################### Scan");
    }
}