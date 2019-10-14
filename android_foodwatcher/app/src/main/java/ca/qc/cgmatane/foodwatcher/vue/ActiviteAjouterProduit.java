package ca.qc.cgmatane.foodwatcher.vue;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteAjouterProduit;
import ca.qc.cgmatane.foodwatcher.modele.Produit;

public class ActiviteAjouterProduit extends ConteneurPrincipal implements ActiviteAjouterProduitVue {
    TextInputEditText textFieldIntitule;
    TextInputEditText textFieldQuantite;
    TextInputEditText textFieldCodeBarre;
    TextInputEditText textInputJoursConservation;
    Spinner choixUniteQuantite;
    Spinner choixCategorieProduit;
    Spinner choixEmplacement;
    MaterialButton boutonAjouterProduit;
    MaterialButton boutonRetour;
    ControleurActiviteAjouterProduit controleur;

    private ImageView imageViewProduct;

    private Bitmap bitmap;


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
        textInputJoursConservation = findViewById(R.id.jours_conservation_text_input);
        imageViewProduct = (ImageView) findViewById(R.id.view_add_product_img);

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
        imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNavCameraCapture = new Intent(getApplicationContext(), ActivitePrisePhoto.class);
                startActivity(intentNavCameraCapture);
            }
        });

        boutonAjouterProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(textFieldIntitule.getText());
                System.out.println(textFieldQuantite.getText());
                System.out.println(textFieldCodeBarre.getText());

                bitmap = ActivitePrisePhoto.bitmap;

                saveImage(bitmap); //Sauvegarde l'image
                ActivitePrisePhoto.bitmap = null;

                enregistrerProduit();
            }
        });
        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controleur.retourVerStockAnnuler();
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

        if (ActivitePrisePhoto.bitmap != null){
            bitmap = ActivitePrisePhoto.bitmap;
            imageViewProduct.setImageBitmap(bitmap);
        }

    }

    private void enregistrerProduit(){
        //recuperer la valeur d'un spinner:
//        String uniteQuantite;
//        uniteQuantite = String.valueOf(choixUniteQuantite.getSelectedItem());
        Produit produit = new Produit(1, textFieldCodeBarre.getText().toString(), textFieldIntitule.getText().toString(), 1,1, 1, Double.parseDouble(textFieldQuantite.getText().toString()),1,true  );
        controleur.actionEnregistrerProduit(produit);
    }

    public void naviguerRetourMaison(){
        this.finish();
    }



    private void saveImage(Bitmap bitmap){
        FileOutputStream fileOutputStream = null;
        File file = getDisc();

        if (!file.exists() && !file.mkdirs()){
            Toast.makeText(this,"Error directory, can't make it",Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "Img_"+date+".jpg";
        String file_name = file.getAbsolutePath()+"/"+name;
        File new_file = new File(file_name);

        try {
            fileOutputStream = new FileOutputStream(new_file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,fileOutputStream);
            Toast.makeText(this,"Success to save img",Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshGallery(new_file);

    }

    private File getDisc(){
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(file,"Foodwatcher");
    }

    private void refreshGallery(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }


}