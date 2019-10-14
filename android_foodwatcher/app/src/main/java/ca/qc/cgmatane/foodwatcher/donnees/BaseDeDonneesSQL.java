package ca.qc.cgmatane.foodwatcher.donnees;

public interface BaseDeDonneesSQL {

    String SQL_NOM_BASE_DE_DONNEES = "foodwatcher_defaut";

    // utilisateur
    String SQL_CREER_TABLE_UTILISATEUR = "CREATE TABLE utilisateur(" +
            "id_utilisateur integer PRIMARY KEY, " +
            "email text, " +
            "mot_de_passe text, " +
            "nom text," +
            "prenom text," +
            "telephone text," +
            "date_naissance date" +
            ")";
    String SQL_DETRUIRE_TABLE_UTILISATEUR = "DROP TABLE IF EXISTS utilisateur";

    // maison
    String SQL_CREER_TABLE_MAISON = "CREATE TABLE maison(" +
            "id_maison integer PRIMARY KEY, " +
            "etiquette text" +
            ")";
    String SQL_DETRUIRE_TABLE_MAISON = "DROP TABLE IF EXISTS maison";

    // maison_compose_utilisateur
    String SQL_CREER_TABLE_MAISON_COMPOSE_UTILISATEUR = "CREATE TABLE maison_compose_utilisateur(" +
            "id_maison integer, " +
            "id_utilisateur integer," +
            "PRIMARY KEY(id_maison, id_utilisateur)" +
            "FOREIGN KEY(id_maison) REFERENCES maison(id_maison)" +
            "FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur)" +
            ")";
    String SQL_DETRUIRE_TABLE_MAISON_COMPOSE_UTILISATEUR = "DROP TABLE IF EXISTS maison_compose_utilisateur";

    // emplacement
    String SQL_CREER_TABLE_EMPLACEMENT = "CREATE TABLE emplacement(" +
            "id_emplacement integer PRIMARY KEY, " +
            "etiquette text" +
            ")";
    String SQL_DETRUIRE_TABLE_EMPLACEMENT = "DROP TABLE IF EXISTS emplacement";

    // categorie_produit
    String SQL_CREER_TABLE_CATEGORIE_PRODUIT = "CREATE TABLE categorie_produit(" +
            "id_categorie_produit integer PRIMARY KEY, " +
            "etiquette text" +
            ")";
    String SQL_DETRUIRE_TABLE_CATEGORIE_PRODUIT = "DROP TABLE IF EXISTS categorie_produit";

    // categorie_produit
    String SQL_CREER_TABLE_UNITE_QUANTITE = "CREATE TABLE unite_quantite(" +
            "id_unite_quantite integer PRIMARY KEY, " +
            "etiquette text" +
            ")";
    String SQL_DETRUIRE_TABLE_UNITE_QUANTITE = "DROP TABLE IF EXISTS unite_quantite";

    // produit
    String SQL_CREER_TABLE_PRODUIT = "CREATE TABLE produit(" +
            "id_produit integer PRIMARY KEY, " +
            "gencode text" +
            "etiquette text" +
            "nombre_jour_conservation integer" +
            "chemin_image text" +
            "id_unite_quantite integer" +
            "id_categorie_produit integer" +
            ")";
    String SQL_DETRUIRE_TABLE_PRODUIT = "DROP TABLE IF EXISTS produit";

    // maison_compose_produit
    String SQL_CREER_TABLE_MAISON_COMPOSE_PRODUIT = "CREATE TABLE maison_compose_produit(" +
            "id_produit integer, " +
            "id_maison integer, " +
            "id_emplacement integer, " +
            "present_liste_course integer, " +
            "PRIMARY KEY(id_produit, id_maison)" +
            ")";
    String SQL_DETRUIRE_TABLE_MAISON_COMPOSE_PRODUIT = "DROP TABLE IF EXISTS maison_compose_produit";

    // historique_maison
    String SQL_CREER_TABLE_HISTORIQUE_MAISON = "CREATE TABLE historique_maison(" +
            "id_historique_maison integer PRIMARY KEY, " +
            "quantite real" +
            "date_saisie date" +
            "id_produit integer" +
            "id_maison integer" +
//            "FOREIGN KEY(id_produit, id_maison) REFERENCES produit(id_produit, id_maison)" +
            ")";
    String SQL_DETRUIRE_TABLE_HISTORIQUE_MAISON = "DROP TABLE IF EXISTS historique_maison";
}
