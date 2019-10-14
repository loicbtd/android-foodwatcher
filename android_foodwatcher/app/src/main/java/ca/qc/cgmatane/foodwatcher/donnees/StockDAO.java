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

    public List<Stock> recupererListeStock() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_STOCK, null);
        this.listeStock.clear();

        Stock stock;
        int indexIdStock = curseur.getColumnIndex(Stock.CLE_ID_STOCK);
        int indexEtiquette = curseur.getColumnIndex(Stock.CLE_ETIQUETTE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int idStock = curseur.getInt(indexIdStock);
            String etiquette = curseur.getString(indexEtiquette);
            stock = new Stock(idStock, etiquette);
            this.listeStock.add(stock);
        }
        return listeStock;
    }

    public Stock trouverStockParId(int id) { //TODO: améliorer en utilisant une requête préparée ou une meilleure recherche
        recupererListeStock();
        for (Stock stock : listeStock) {
            if (stock.getIdStock() == id) return stock;
        }
        return null;
    }

    public void ajouterStock(Stock stock) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_CREER_STOCK);
        sqLiteStatement.bindString(1, stock.getEtiquette());
        sqLiteStatement.execute();
    }

    public void modifierStock(Stock stock) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODIFIER_STOCK);
        sqLiteStatement.bindString(1, stock.getEtiquette());
        sqLiteStatement.bindString(2, ""+ stock.getIdStock());
        sqLiteStatement.execute();
    }

    public void supprimerStock(Stock stock) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_SUPPRIMER_STOCK);
        sqLiteStatement.bindString(1, ""+ stock.getIdStock());
        sqLiteStatement.execute();
    }

    private void ajouterListeStockMock() {
        ajouterStock(new Stock(0, "Domicile"));
        ajouterStock(new Stock(1, "Maison vacances"));
        ajouterStock(new Stock(2, "Mon restaurant"));
    }
}
