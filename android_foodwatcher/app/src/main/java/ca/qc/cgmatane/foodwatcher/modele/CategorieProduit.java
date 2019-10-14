package ca.qc.cgmatane.foodwatcher.modele;

public class CategorieProduit {

    public static final String CLE_ID_CATEGORIE_PRODUIT = "id_categorie_produit";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int idCategorieProduit;
    protected String etiquette;

    public CategorieProduit(int idCategorieProduit, String etiquette) {
        this.idCategorieProduit = idCategorieProduit;
        this.etiquette = etiquette;
    }

    public int getIdCategorieProduit() {
        return idCategorieProduit;
    }
    public void setIdCategorieProduit(int idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
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
