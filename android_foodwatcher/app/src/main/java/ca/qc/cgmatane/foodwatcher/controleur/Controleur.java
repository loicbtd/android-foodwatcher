package ca.qc.cgmatane.foodwatcher.controleur;

import android.content.Context;

public interface Controleur {
    void onCreate(Context contexte);
    void onPause();
    void onResume();
    void onDestroy();
    void onActivityResult(int activite);
}
