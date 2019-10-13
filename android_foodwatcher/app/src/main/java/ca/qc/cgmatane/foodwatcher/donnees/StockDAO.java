package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Stock;

public class StockDAO implements StockSQL {

    private static StockDAO instance = null;
    protected List<Stock> listeStock;

    private BaseDeDonnees baseDeDonnees;

    public static StockDAO getInstance() {
        if (null == instance) {
            instance = new StockDAO();
        }
        return instance;
    }

    public StockDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeStock = new ArrayList<>();

        // mock
        ajouterListeMaisonMock();
    }

    public List<Stock> recupererListeMaison() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_MAISON, null);
        this.listeStock.clear();

        Stock stock;
        int indexId_maison = curseur.getColumnIndex(Stock.CLE_ID_MAISON);
        int indexEtiquette = curseur.getColumnIndex(Stock.CLE_ETIQUETTE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int id_maison = curseur.getInt(indexId_maison);
            String etiquette = curseur.getString(indexEtiquette);
            stock = new Stock(id_maison, etiquette);
            this.listeStock.add(stock);
        }
        return listeStock;
    }

    public void ajouterMaison(Stock stock) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_INSERER_MAISON);
        sqLiteStatement.bindString(1, stock.getEtiquette());
        sqLiteStatement.execute();
    }

    public void modifierMaison(Stock stock) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODFIFIER_MAISON);
        sqLiteStatement.bindString(1, stock.getEtiquette());
        sqLiteStatement.bindString(2, ""+ stock.getId_maison());
        sqLiteStatement.execute();
    }

    public void ajouterListeMaisonMock() {
        ajouterMaison(new Stock(0, "Domicile"));
        ajouterMaison(new Stock(1, "ActiviteStock vacances"));
        ajouterMaison(new Stock(2, "Mon restaurant"));
    }
}
