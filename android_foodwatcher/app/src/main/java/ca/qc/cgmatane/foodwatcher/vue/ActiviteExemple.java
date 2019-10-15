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


}
