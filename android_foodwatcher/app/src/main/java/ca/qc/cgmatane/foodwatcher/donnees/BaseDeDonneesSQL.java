package ca.qc.cgmatane.foodwatcher.donnees;

public interface BaseDeDonneesSQL {

    String SQL_NOM_BASE_DE_DONNEES = "foodwatcher_defaut";

    String SQL_CREER_TABLE_UTILISATEUR = "CREATE TABLE utilisateur(" +
            "id_utilisateur INTEGER PRIMARY KEY, " +
            "email text, " +
            "mot_de_passe text, " +
            "nom text," +
            "prenom text" +
            "telephone text" +
            "date_naissance date" +
            ")";
    String SQL_DETRUIRE_TABLE_UTILISATEUR = "DROP TABLE IF EXISTS utilisateur";


}
