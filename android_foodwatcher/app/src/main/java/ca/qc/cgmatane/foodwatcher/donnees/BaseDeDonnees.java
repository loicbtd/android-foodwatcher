package ca.qc.cgmatane.foodwatcher.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper implements BaseDeDonneesSQL {

    private static BaseDeDonnees instance;

    public static BaseDeDonnees getInstance(Context context) {
        instance = new BaseDeDonnees(context);
        return instance;
    }

    public static BaseDeDonnees getInstance() {
        return instance;
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, SQL_NOM_BASE_DE_DONNEES, null, 1);
        contexte.deleteDatabase(SQL_NOM_BASE_DE_DONNEES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        genererBaseDeDonneesVierge(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        genererBaseDeDonneesVierge(db);
    }

    private void genererBaseDeDonneesVierge(SQLiteDatabase db) {
        db.execSQL(SQL_DETRUIRE_TABLE_HISTORIQUE_MAISON);
        db.execSQL(SQL_DETRUIRE_TABLE_MAISON_COMPOSE_PRODUIT);
        db.execSQL(SQL_DETRUIRE_TABLE_PRODUIT);
        db.execSQL(SQL_DETRUIRE_TABLE_UNITE_QUANTITE);
        db.execSQL(SQL_DETRUIRE_TABLE_CATEGORIE_PRODUIT);
        db.execSQL(SQL_DETRUIRE_TABLE_EMPLACEMENT);
        db.execSQL(SQL_DETRUIRE_TABLE_MAISON_COMPOSE_UTILISATEUR);
        db.execSQL(SQL_DETRUIRE_TABLE_MAISON);
        db.execSQL(SQL_DETRUIRE_TABLE_UTILISATEUR);

        db.execSQL(SQL_CREER_TABLE_UTILISATEUR);
        db.execSQL(SQL_CREER_TABLE_MAISON);
        db.execSQL(SQL_CREER_TABLE_MAISON_COMPOSE_UTILISATEUR);
        db.execSQL(SQL_CREER_TABLE_EMPLACEMENT);
        db.execSQL(SQL_CREER_TABLE_CATEGORIE_PRODUIT);
        db.execSQL(SQL_CREER_TABLE_UNITE_QUANTITE);
        db.execSQL(SQL_CREER_TABLE_PRODUIT);
        db.execSQL(SQL_CREER_TABLE_MAISON_COMPOSE_PRODUIT);
        db.execSQL(SQL_CREER_TABLE_HISTORIQUE_MAISON);
    }
}
