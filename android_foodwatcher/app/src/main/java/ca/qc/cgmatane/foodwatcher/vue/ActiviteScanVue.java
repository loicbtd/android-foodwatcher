package ca.qc.cgmatane.foodwatcher.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import info.androidhive.barcode.BarcodeReader;

public class ActiviteScanVue extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    public BarcodeReader barcodeReader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_scan);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);

    }

    @Override
    public void onScanned(Barcode barcode) {

        barcodeReader.playBeep();

        //TODO faire la requete sql, si non présent juste remplir le champ gencode et sinon remplir tous les champs

        //TODO Ajouter en extra ce qui doit etre affiche
        Intent intentionRetour = new Intent();
        intentionRetour.putExtra("code", barcode.displayValue);
        setResult(Activity.RESULT_OK,intentionRetour);

        finish();

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {

    }


}
