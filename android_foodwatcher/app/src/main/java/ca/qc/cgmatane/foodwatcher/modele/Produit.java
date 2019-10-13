package ca.qc.cgmatane.foodwatcher.modele;

public class Produit {

    public static final String CLE_ID_PRODUIT = "id_produit";
    public static final String CLE_GENCODE = "gencode";
    public static final String CLE_ETIQUETTE = "etiquette";
    public static final String CLE_NOMBRE_JOUR_CONSERVATION = "nombre_jour_conservation";
    public static final String CLE_CHEMIN_IMAGE = "chemin_image";
    public static final String CLE_ID_UNITE_QUANTITE = "id_unite_quantite";
    public static final String CLE_ID_CATEGORIE_PRODUIT = "id_categorie_produit";

    protected int id_produit;
    protected String gencode;
    protected String etiquette;
    protected String nombre_jour_conservation;
    protected String chemin_image;
    protected int id_unite_quantite;
    protected int id_categorie_produit;

    public Produit(int id_produit, String gencode, String etiquette, String nombre_jour_conservation, String chemin_image, int id_unite_quantite, int id_categorie_produit) {
        this.id_produit = id_produit;
        this.gencode = gencode;
        this.etiquette = etiquette;
        this.nombre_jour_conservation = nombre_jour_conservation;
        this.chemin_image = chemin_image;
        this.id_unite_quantite = id_unite_quantite;
        this.id_categorie_produit = id_categorie_produit;
    }

    public int getId_produit() {
        return id_produit;
    }
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
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
    public String getNombre_jour_conservation() {
        return nombre_jour_conservation;
    }
    public void setNombre_jour_conservation(String nombre_jour_conservation) {
        this.nombre_jour_conservation = nombre_jour_conservation;
    }
    public String getChemin_image() {
        return chemin_image;
    }
    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }
    public int getId_unite_quantite() {
        return id_unite_quantite;
    }
    public void setId_unite_quantite(int id_unite_quantite) {
        this.id_unite_quantite = id_unite_quantite;
    }
    public int getId_categorie_produit() {
        return id_categorie_produit;
    }
    public void setId_categorie_produit(int id_categorie_produit) {
        this.id_categorie_produit = id_categorie_produit;
    }

    public boolean isValide() {
        //TODO : implémenter isValide
        return true;
    }
}
