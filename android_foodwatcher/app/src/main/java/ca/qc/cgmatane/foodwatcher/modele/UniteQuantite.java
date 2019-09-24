package ca.qc.cgmatane.foodwatcher.modele;

public class UniteQuantite {

    public static final String CLE_ID_UNITE_QUANTITE = "id_unite_quantite";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int id_unite_quantite;
    protected String etiquette;

    public UniteQuantite(int id_unite_quantite, String etiquette) {
        this.id_unite_quantite = id_unite_quantite;
        this.etiquette = etiquette;
    }

    public int getId_unite_quantite() {
        return id_unite_quantite;
    }
    public void setId_unite_quantite(int id_unite_quantite) {
        this.id_unite_quantite = id_unite_quantite;
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
