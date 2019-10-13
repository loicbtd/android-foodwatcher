package ca.qc.cgmatane.foodwatcher.modele;

public class Utilisateur {

    public static final String CLE_ID_UTILISATEUR = "id_utilisateur";
    public static final String CLE_EMAIL = "email";
    public static final String CLE_MOT_DE_PASSE = "mot_de_passe";
    public static final String CLE_NOM = "nom";
    public static final String CLE_PRENOM = "prenom";
    public static final String CLE_TELEPHONE = "telephone";
    public static final String CLE_DATE_NAISSANCE = "date_naissance";

    protected int id_utilisateur;
    protected String email;
    protected String mot_de_passe;
    protected String nom;
    protected String prenom;
    protected String telephone;
    protected String date_naissance;

    public Utilisateur(int id_utilisateur, String email, String mot_de_passe, String nom, String prenom, String telephone, String date_naissance) {
        this.id_utilisateur = id_utilisateur;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.date_naissance = date_naissance;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }
    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMot_de_passe() {
        return mot_de_passe;
    }
    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getDate_naissance() {
        return date_naissance;
    }
    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public boolean isValide() {
        //TODO : implémenter isValide
        return true;
    }
}

