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
        ajouterListeStockMock();
    }

    public List<Stock> recupererListeMaison() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_STOCK, null);
        this.listeStock.clear();

        Stock stock;
        int indexId_stock = curseur.getColumnIndex(Stock.CLE_ID_STOCK);
        int indexEtiquette = curseur.getColumnIndex(Stock.CLE_ETIQUETTE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int id_stock = curseur.getInt(indexId_stock);
            String etiquette = curseur.getString(indexEtiquette);
            stock = new Stock(id_stock, etiquette);
            this.listeStock.add(stock);
        }
        return listeStock;
    }

    public void ajouterStock(Stock stock) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_INSERER_STOCK);
        sqLiteStatement.bindString(1, stock.getEtiquette());
        sqLiteStatement.execute();
    }

    public void modifierStock(Stock stock) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODFIFIER_STOCK);
        sqLiteStatement.bindString(1, stock.getEtiquette());
        sqLiteStatement.bindString(2, ""+ stock.getId_stock());
        sqLiteStatement.execute();
    }

    public void ajouterListeStockMock() {
        ajouterStock(new Stock(0, "Missile"));
        ajouterStock(new Stock(1, "Maison vacances"));
        ajouterStock(new Stock(2, "Mon restaurant"));
    }
}
