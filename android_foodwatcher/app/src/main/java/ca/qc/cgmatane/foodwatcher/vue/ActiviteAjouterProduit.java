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

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteAjouterProduit;

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                String gencode = null;
                String nomProduit = null;

                if (data != null) {
                    //TODO Récupérer les autres données
                    gencode = data.getStringExtra("code");
                    nomProduit = data.getStringExtra("etiquette");


                    textFieldCodeBarre.setText(gencode);
                    textFieldIntitule.setText(nomProduit);
                    //TODO tester si elles sont nulles : si oui alors ecrire le gencode seulement et sinon tous les champs
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

    private void enregistrerProduit(){
        //recuperer la valeur d'un spinner:
//        String uniteQuantite;
//        uniteQuantite = String.valueOf(choixUniteQuantite.getSelectedItem());
//        Produit produit = new Produit(1, textFieldCodeBarre.getText().toString(), textFieldIntitule.getText().toString(), 1, 1, 1, Double.parseDouble(textFieldQuantite.getText().toString()),1,checkBoxAjouterListeCourse.isSelected());
//        controleur.actionEnregistrerProduit(produit);
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