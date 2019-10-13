package ca.qc.cgmatane.foodwatcher.modele;

public class Stock {

    public static final String CLE_ID_MAISON = "id_maison";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int id_maison;
    protected String etiquette;

    public Stock(int id_maison, String etiquette) {
        this.id_maison = id_maison;
        this.etiquette = etiquette;
    }

    public int getId_maison() {
        return id_maison;
    }
    public void setId_maison(int id_maison) {
        this.id_maison = id_maison;
    }
    public String getEtiquette() {
        return etiquette;
    }
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    public boolean isValide() {
        //TODO : implémenter isValide
        return true;
    }
}
