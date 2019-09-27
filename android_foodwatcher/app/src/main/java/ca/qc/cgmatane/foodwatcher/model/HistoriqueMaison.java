package ca.qc.cgmatane.foodwatcher.model;

public class HistoriqueMaison {

    public static final String CLE_ID_HISTORIQUE_MAISON = "id_historique_maison";
    public static final String CLE_DATE_SAISIE = "date_saisie";

    protected int id_historique_maison;
    protected String date_saisie;

    public HistoriqueMaison(int id_historique_maison, String date_saisie) {
        this.id_historique_maison = id_historique_maison;
        this.date_saisie = date_saisie;
    }

    public int getId_historique_maison() {
        return id_historique_maison;
    }
    public void setId_historique_maison(int id_historique_maison) {
        this.id_historique_maison = id_historique_maison;
    }
    public String getDate_saisie() {
        return date_saisie;
    }
    public void setDate_saisie(String date_saisie) {
        this.date_saisie = date_saisie;
    }

    public boolean isValide() {
        //TODO : implémenter isValide
        return true;
    }
}
