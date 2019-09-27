package ca.qc.cgmatane.foodwatcher.controller;

import android.content.Context;

public interface Controller {
    void onCreate(Context contexte);
    void onPause();
    void onResume();
    void onDestroy();
    void onActivityResult(int activite);
}
