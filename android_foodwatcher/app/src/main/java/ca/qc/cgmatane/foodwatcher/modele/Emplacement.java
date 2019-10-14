package ca.qc.cgmatane.foodwatcher.modele;

public class Emplacement {

    public static final String CLE_ID_EMPLACEMENT = "id_emplacement";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int idEmplacement;
    protected String etiquette;

    public Emplacement(int idEmplacement, String etiquette) {
        this.idEmplacement = idEmplacement;
        this.etiquette = etiquette;
    }

    public int getIdEmplacement() {
        return idEmplacement;
    }
    public void setIdEmplacement(int idEmplacement) {
        this.idEmplacement = idEmplacement;
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
