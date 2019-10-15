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
import ca.qc.cgmatane.foodwatcher.donnees.ProduitDAO;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import info.androidhive.barcode.BarcodeReader;

public class ActiviteScan extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener, ActiviteScanVue {

    public BarcodeReader barcodeReader;

    protected ProduitDAO accesseurProduit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_scan);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);

    }

    @Override
    public void onScanned(Barcode barcode) {

        barcodeReader.playBeep();

        accesseurProduit = ProduitDAO.getInstance();

        Produit produit = accesseurProduit.recupererProduitParGencode(barcode.displayValue);

        Intent intentionRetour = new Intent();
        intentionRetour.putExtra("code", barcode.displayValue);

        if (produit != null){
            intentionRetour.putExtra("etiquette", produit.getEtiquette());
        }else {
            intentionRetour.putExtra("etiquette", "");
        }

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
