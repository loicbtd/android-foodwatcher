package ca.qc.cgmatane.foodwatcher.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.qc.cgmatane.foodwatcher.R;

public class Maison extends AppCompatActivity implements VueMaison {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_maison);
    }
}
