package ca.qc.cgmatane.foodwatcher.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonneesDAO extends SQLiteOpenHelper implements BaseDeDonneesSQL {

    private static BaseDeDonneesDAO instance;

    public static BaseDeDonneesDAO getInstance(Context context) {
        instance = new BaseDeDonneesDAO(context);
        return instance;
    }

    public static BaseDeDonneesDAO getInstance() {
        return instance;
    }

    public BaseDeDonneesDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonneesDAO(Context contexte) {
        super(contexte, SQL_NOM_BASE_DE_DONNEES, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DETRUIRE_TABLE_UTILISATEUR);
        db.execSQL(SQL_CREER_TABLE_UTILISATEUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL(SQL_DETRUIRE_TABLE_UTILISATEUR);
        db.execSQL(SQL_CREER_TABLE_UTILISATEUR);
    }
}
