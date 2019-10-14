package ca.qc.cgmatane.foodwatcher.modele;

public class ProduitStocke extends Produit {

    public static final String CLE_PRESENT_LISTE_COURSE = "present_liste_course";
    public static final String CLE_QUANTITE = "quantite";
    public static final String CLE_ID_STOCK = "id_stock";
    public static final String CLE_ID_EMPLACEMENT = "id_emplacement";

    protected Stock stock;
    protected Emplacement emplacement;
    protected double quantite;
    protected boolean presentListeCourse;

    protected boolean selectionne;

    public ProduitStocke(int idProduit, String gencode, String etiquette, UniteQuantite uniteQuantite, CategorieProduit categorieProduit, Stock stock, Emplacement emplacement, double quantite, boolean presentListeCourse) {
        super(idProduit, gencode, etiquette, uniteQuantite, categorieProduit);
        this.stock = stock;
        this.emplacement = emplacement;
        this.quantite = quantite;
        this.presentListeCourse = presentListeCourse;
        this.selectionne = false;
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
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public boolean isPresentListeCourse() {
        return presentListeCourse;
    }
    public void setPresentListeCourse(boolean presentListeCourse) {
        this.presentListeCourse = presentListeCourse;
    }
    public boolean isSelectionne() {
        return selectionne;
    }
    public void setSelectionne(boolean selectionne) {
        this.selectionne = selectionne;
    }
}
