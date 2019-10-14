package ca.qc.cgmatane.foodwatcher.modele;

public class Stock {

    public static final String CLE_ID_STOCK = "id_stock";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int idStock;
    protected String etiquette;

    public Stock(int idStock, String etiquette) {
        this.idStock = idStock;
        this.etiquette = etiquette;
    }

    public int getIdStock() {
        return idStock;
    }
    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }
    public String getEtiquette() {
        return etiquette;
    }
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }
}
