package ca.qc.cgmatane.foodwatcher.controller;

import android.content.Context;

public interface Controller {
    void onCreate(Context applicationContext);
    void onPause();
    void onResume();
    void onDestroy();
    void onActivityResult(int activite);
    void onBackPressed();
}