package ca.qc.cgmatane.foodwatcher.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.qc.cgmatane.foodwatcher.R;

public class AddProductActivity extends MasterActivity {
    TextInputEditText textFieldIntitule;
    TextInputEditText textFieldQuantite;
    TextInputEditText textFieldCodeBarre;
    MaterialButton boutonAjouterProduit;
    MaterialButton boutonRetour;

    private Bitmap bitmap;

    //TODO Ajouter unr ImageView et un setOnclickListener pour afficher une CameraCapture

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.view_add_product);
        boutonAjouterProduit = findViewById(R.id.btn_view_add_product_action_add);
        boutonRetour = findViewById(R.id.btn_view_add_product_action_cancel);
        textFieldIntitule = findViewById(R.id.intitule_produit_edit_text);
        textFieldQuantite = findViewById(R.id.edit_text_quantite);
        textFieldCodeBarre = findViewById(R.id.code_barre_edit_text);
        boutonAjouterProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(textFieldIntitule.getText());
                System.out.println(textFieldQuantite.getText());
                System.out.println(textFieldCodeBarre.getText());

                bitmap = CameraCapture.bitmap;

                saveImage(bitmap); //Sauvegarde l'image

                naviguerRetourMaison();
            }
        });
        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naviguerRetourMaison();
            }
        });
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
