package ca.qc.cgmatane.foodwatcher.controller;

import android.content.Context;
import android.content.Intent;

import ca.qc.cgmatane.foodwatcher.view.AddProductActivity;
import ca.qc.cgmatane.foodwatcher.view.StockActivity;

public class StockController implements Controller {
    private StockActivity view;
    private static final int ADD_PRODUCT_ACTIVITY = 1;

    public StockController(StockActivity vue){
        this.view = vue;
    }

    public void navigateToViewAddProduct(){
        view.addProductList();
        view.startActivityForResult(new Intent(this.view.getBaseContext(), AddProductActivity.class), ADD_PRODUCT_ACTIVITY);
    }

    @Override
    public void onCreate(Context applicationContext) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int activite) {
        switch (activite){
            case ADD_PRODUCT_ACTIVITY:
                System.out.println("ajouter un élément à la liste");
                view.addProductList();
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}