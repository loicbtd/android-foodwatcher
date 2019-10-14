package ca.qc.cgmatane.foodwatcher.modele;

public class Stock {

    public static final String CLE_ID_STOCK = "id_stock";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int id_stock;
    protected String etiquette;

    public Stock(int id_stock, String etiquette) {
        this.id_stock = id_stock;
        this.etiquette = etiquette;
    }

    public int getId_stock() {
        return id_stock;
    }
    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }
    public String getEtiquette() {
        return etiquette;
    }
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }
}
