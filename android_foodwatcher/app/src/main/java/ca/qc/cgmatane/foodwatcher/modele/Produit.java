package ca.qc.cgmatane.foodwatcher.modele;

public class Produit {

    public static final String CLE_ID_PRODUIT = "id_produit";
    public static final String CLE_GENCODE = "gencode";
    public static final String CLE_ETIQUETTE = "etiquette";
    public static final String CLE_ID_UNITE_QUANTITE = "id_unite_quantite";
    public static final String CLE_ID_CATEGORIE_PRODUIT = "id_categorie_produit";
    public static final String CLE_ID_STOCK = "id_stock";
    public static final String CLE_QUANTITE = "quantite";
    public static final String CLE_ID_EMPLACEMENT = "id_emplacement";
    public static final String CLE_PRESENT_LISTE_COURSE = "present_liste_course";

    protected int idProduit;
    protected String gencode;
    protected String etiquette;
    protected int idUniteQuantite;
    protected int idCategorieProduit;
    protected int idStock;
    protected double quantite;
    protected int idEmplacement;
    protected boolean presentListeCourse;

    protected boolean selectionne;

    public Produit(int idProduit, String gencode, String etiquette, int idUniteQuantite, int idCategorieProduit, int idStock, double quantite, int idEmplacement, boolean presentListeCourse) {
        this.idProduit = idProduit;
        this.gencode = gencode;
        this.etiquette = etiquette;
        this.idUniteQuantite = idUniteQuantite;
        this.idCategorieProduit = idCategorieProduit;
        this.idStock = idStock;
        this.quantite = quantite;
        this.idEmplacement = idEmplacement;
        this.presentListeCourse = presentListeCourse;
        this.selectionne = false;
    }

    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public String getGencode() {
        return gencode;
    }
    public void setGencode(String gencode) {
        this.gencode = gencode;
    }
    public String getEtiquette() {
        return etiquette;
    }
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }
    public int getIdUniteQuantite() {
        return idUniteQuantite;
    }
    public void setIdUniteQuantite(int idUniteQuantite) {
        this.idUniteQuantite = idUniteQuantite;
    }
    public int getIdCategorieProduit() {
        return idCategorieProduit;
    }
    public void setIdCategorieProduit(int idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }
    public int getIdStock() {
        return idStock;
    }
    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public int getIdEmplacement() {
        return idEmplacement;
    }
    public void setIdEmplacement(int idEmplacement) {
        this.idEmplacement = idEmplacement;
    }
    public boolean isPresentListeCourse() {
        return presentListeCourse;
    }
    public void setPresentListeCourse(boolean presentListeCourse) {
        this.presentListeCourse = presentListeCourse;
    }
    public boolean isSelectionne(){
        return selectionne;
    }
    public void setSelectionne(boolean estSelectionne){
        this.selectionne = estSelectionne;
    }

    public boolean isValide() {
        //TODO : implémenter isValide
        return true;
    }
}
