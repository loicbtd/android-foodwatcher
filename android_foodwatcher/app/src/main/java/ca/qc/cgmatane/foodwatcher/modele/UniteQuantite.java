package ca.qc.cgmatane.foodwatcher.modele;

public class UniteQuantite {

    public static final String CLE_ID_UNITE_QUANTITE = "id_unite_quantite";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int idUniteQuantite;
    protected String etiquette;

    public UniteQuantite(int idUniteQuantite, String etiquette) {
        this.idUniteQuantite = idUniteQuantite;
        this.etiquette = etiquette;
    }

    public int getIdUniteQuantite() {
        return idUniteQuantite;
    }
    public void setIdUniteQuantite(int idUniteQuantite) {
        this.idUniteQuantite = idUniteQuantite;
    }
    public String getEtiquette() {
        return etiquette;
    }
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }
}
