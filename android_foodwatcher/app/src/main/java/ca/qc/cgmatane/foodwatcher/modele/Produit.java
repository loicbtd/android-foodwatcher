package ca.qc.cgmatane.foodwatcher.modele;

public class Produit {

    public static final String CLE_ID_PRODUIT = "id_produit";
    public static final String CLE_GENCODE = "gencode";
    public static final String CLE_ETIQUETTE = "etiquette";
    public static final String CLE_ID_UNITE_QUANTITE = "id_unite_quantite";
    public static final String CLE_ID_CATEGORIE_PRODUIT = "id_categorie_produit";

    protected int idProduit;
    protected String gencode;
    protected String etiquette;
    protected UniteQuantite uniteQuantite;
    protected CategorieProduit categorieProduit;

    public Produit(int idProduit, String gencode, String etiquette, UniteQuantite uniteQuantite, CategorieProduit categorieProduit) {
        this.idProduit = idProduit;
        this.gencode = gencode;
        this.etiquette = etiquette;
        this.uniteQuantite = uniteQuantite;
        this.categorieProduit = categorieProduit;
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
    public UniteQuantite getUniteQuantite() {
        return uniteQuantite;
    }
    public void setUniteQuantite(UniteQuantite uniteQuantite) {
        this.uniteQuantite = uniteQuantite;
    }
    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }
    public void setCategorieProduit(CategorieProduit categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public boolean equals(String gencodeACompare) {
        int gencodeCourant;
        int gencodeProduitACompare;
//        try {
            gencodeCourant = Integer.parseInt(getGencode());
            gencodeProduitACompare = Integer.parseInt(gencodeACompare);
        /*} catch (Exception e) {
            return false;
        }*/
        return gencodeCourant == gencodeProduitACompare;
    }
}
