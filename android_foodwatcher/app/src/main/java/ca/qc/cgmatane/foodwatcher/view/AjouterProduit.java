package ca.qc.cgmatane.foodwatcher.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import ca.qc.cgmatane.foodwatcher.R;

public class AjouterProduit extends AppCompatActivity {
    TextInputEditText textFieldIntitule;
    TextInputEditText textFieldQuantite;
    TextInputEditText textFieldCodeBarre;
    MaterialButton boutonAjouterProduit;
    MaterialButton boutonRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_produit);
        boutonAjouterProduit = findViewById(R.id.bouton_ajouter_produit);
        boutonRetour = findViewById(R.id.bouton_annuler_produit);
        textFieldIntitule = findViewById(R.id.intitule_produit_edit_text);
        textFieldQuantite = findViewById(R.id.edit_text_quantite);
        textFieldCodeBarre = findViewById(R.id.code_barre_edit_text);
        boutonAjouterProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(textFieldIntitule.getText());
                System.out.println(textFieldQuantite.getText());
                System.out.println(textFieldCodeBarre.getText());
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
}
