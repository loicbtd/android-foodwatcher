package ca.qc.cgmatane.foodwatcher.modele;

public class CategorieProduit {

    public static final String CLE_ID_CATEGORIE_PRODUIT = "id_categorie_produit";
    public static final String CLE_ETIQUETTE = "etiquette";

    protected int id_categorie_produit;
    protected String etiquette;

    public CategorieProduit(int id_categorie_produit, String etiquette) {
        this.id_categorie_produit = id_categorie_produit;
        this.etiquette = etiquette;
    }

    public int getId_categorie_produit() {
        return id_categorie_produit;
    }
    public void setId_categorie_produit(int id_categorie_produit) {
        this.id_categorie_produit = id_categorie_produit;
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
