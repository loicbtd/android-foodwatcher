package ca.qc.cgmatane.foodwatcher.model;

public class Emplacement {

    public static final String CLE_ID_EMPLACEMENT = "id_emplacement";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int id_emplacement;
    protected int etiquette;

    public Emplacement(int id_emplacement, int etiquette) {
        this.id_emplacement = id_emplacement;
        this.etiquette = etiquette;
    }

    public int getId_emplacement() {
        return id_emplacement;
    }
    public void setId_emplacement(int id_emplacement) {
        this.id_emplacement = id_emplacement;
    }
    public int getEtiquette() {
        return etiquette;
    }
    public void setEtiquette(int etiquette) {
        this.etiquette = etiquette;
    }

    public boolean isValide() {
        //TODO : implémenter isValide
        return true;
    }
}
