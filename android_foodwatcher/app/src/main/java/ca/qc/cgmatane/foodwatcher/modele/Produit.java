package ca.qc.cgmatane.foodwatcher.modele;

public class Produit {

    public static final String CLE_ID_PRODUIT = "id_produit";
    public static final String CLE_GENCODE = "gencode";
    public static final String CLE_ETIQUETTE = "etiquette";
    public static final String CLE_QUANTITE = "quantite";
    public static final String CLE_PRESENT_LISTE_COURSE = "present_liste_course";
    public static final String CLE_ID_UNITE_QUANTITE = "id_unite_quantite";
    public static final String CLE_ID_CATEGORIE_PRODUIT = "id_categorie_produit";
    public static final String CLE_ID_STOCK = "idStock";
    public static final String CLE_ID_EMPLACEMENT = "id_emplacement";

    protected int idProduit;
    protected String gencode;
    protected String etiquette;
    protected double quantite;
    protected boolean presentListeCourse;
    protected UniteQuantite uniteQuantite;
    protected CategorieProduit categorieProduit;
    protected Stock stock;
    protected Emplacement emplacement;

    protected boolean selectionne;

    public Produit(int idProduit, String gencode, String etiquette, double quantite, boolean presentListeCourse, UniteQuantite uniteQuantite, CategorieProduit categorieProduit, Stock stock, Emplacement emplacement) {
        this.idProduit = idProduit;
        this.gencode = gencode;
        this.etiquette = etiquette;
        this.quantite = quantite;
        this.presentListeCourse = presentListeCourse;
        this.uniteQuantite = uniteQuantite;
        this.categorieProduit = categorieProduit;
        this.stock = stock;
        this.emplacement = emplacement;
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
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public UniteQuantite getUniteQuantite() {
        return uniteQuantite;
    }
    public void setUniteQuantite(UniteQuantite uniteQuantite) {
        this.uniteQuantite = uniteQuantite;
    }
    public boolean isPresentListeCourse() {
        return presentListeCourse;
    }
    public void setPresentListeCourse(boolean presentListeCourse) {
        this.presentListeCourse = presentListeCourse;
    }
    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }
    public void setCategorieProduit(CategorieProduit categorieProduit) {
        this.categorieProduit = categorieProduit;
    }
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    public Emplacement getEmplacement() {
        return emplacement;
    }
    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }
    public boolean isSelectionne() {
        return selectionne;
    }
    public void setSelectionne(boolean selectionne) {
        this.selectionne = selectionne;
    }
}
