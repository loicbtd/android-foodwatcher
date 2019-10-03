package ca.qc.cgmatane.foodwatcher.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper implements DataBaseSQL {

    public static final String DATABASE_NAME = "foodwatcher";

    private static DataBase instance;

    public static DataBase getInstance(Context context) {
        instance = new DataBase(context);
        return instance;
    }

    public static DataBase getInstance() {
        return instance;
    }

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBase(Context contexte) {
        super(contexte, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQL_DETRUIRE_TABLE_DEVOIR);
//        db.execSQL(SQL_CREER_TABLE_DEVOIR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
//        db.execSQL(SQL_DETRUIRE_TABLE_DEVOIR);
//        db.execSQL(SQL_CREER_TABLE_DEVOIR);
    }
}
