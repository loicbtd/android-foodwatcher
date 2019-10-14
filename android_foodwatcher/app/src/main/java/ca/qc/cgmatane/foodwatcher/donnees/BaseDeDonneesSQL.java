package ca.qc.cgmatane.foodwatcher.donnees;

public interface BaseDeDonneesSQL {

    String SQL_NOM_BASE_DE_DONNEES = "foodwatcher_defaut";

    // utilisateur
    String SQL_CREER_TABLE_UTILISATEUR = "CREATE TABLE utilisateur(\n" +
            "id_utilisateur integer PRIMARY KEY,\n" +
            "email text,\n" +
            "mot_de_passe text,\n" +
            "nom text,\n" +
            "prenom text,\n" +
            "telephone text,\n" +
            "date_naissance date\n" +
            ")";
    String SQL_DETRUIRE_TABLE_UTILISATEUR = "DROP TABLE IF EXISTS utilisateur";

    // stock
    String SQL_CREER_TABLE_STOCK = "CREATE TABLE stock(\n" +
            "id_stock integer PRIMARY KEY,\n" +
            "etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_STOCK = "DROP TABLE IF EXISTS stock";

    
    // stock_compose_utilisateur
    String SQL_CREER_TABLE_STOCK_COMPOSE_UTILISATEUR = "CREATE TABLE stock_compose_utilisateur(\n" +
            "id_stock integer,\n" +
            "id_utilisateur integer,\n" +
            "PRIMARY KEY(id_stock, id_utilisateur),\n" +
            "CONSTRAINT stock_stock_compose_utilisateur_fk\n" +
            "   FOREIGN KEY(id_stock)\n" +
            "   REFERENCES stock(id_stock)\n" +
            "   ON DELETE CASCADE\n" +
            "   ON UPDATE CASCADE,\n" +
            "CONSTRAINT utilisateur_stock_compose_utilisateur_fk\n" +
            "   FOREIGN KEY(id_utilisateur)\n" +
            "   REFERENCES utilisateur(id_utilisateur)\n" +
            "   ON DELETE CASCADE\n" +
            "   ON UPDATE CASCADE,\n" +
            ")";
    String SQL_DETRUIRE_TABLE_MAISON_COMPOSE_UTILISATEUR = "DROP TABLE IF EXISTS stock_compose_utilisateur";

    // emplacement
    String SQL_CREER_TABLE_EMPLACEMENT = "CREATE TABLE emplacement(\n" +
            "id_emplacement integer PRIMARY KEY,\n" +
            "etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_EMPLACEMENT = "DROP TABLE IF EXISTS emplacement";

    // categorie_produit
    String SQL_CREER_TABLE_CATEGORIE_PRODUIT = "CREATE TABLE categorie_produit(\n" +
            "id_categorie_produit integer PRIMARY KEY,\n" +
            "etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_CATEGORIE_PRODUIT = "DROP TABLE IF EXISTS categorie_produit";

    // categorie_produit
    String SQL_CREER_TABLE_UNITE_QUANTITE = "CREATE TABLE unite_quantite(\n" +
            "id_unite_quantite integer PRIMARY KEY,\n" +
            "etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_UNITE_QUANTITE = "DROP TABLE IF EXISTS unite_quantite";

    // produit
    String SQL_CREER_TABLE_PRODUIT = "CREATE TABLE produit(\n" +
            "id_produit integer PRIMARY KEY,\n" +
            "gencode text,\n" +
            "etiquette text,\n" +
            "nombre_jour_conservation integer,\n" +
            "chemin_image text,\n" +
            "id_unite_quantite integer,\n" +
            "id_categorie_produit integer,\n" +
            "CONSTRAINT unite_quantite_produit_fk\n" +
            "   FOREIGN KEY(id_unite_quantite" +



            "FOREIGN KEY(id_unite_quantite) REFERENCES unite_quantite(id_unite_quantite), " +
            "FOREIGN KEY(id_categorie_produit) REFERENCES categorie_produit(id_categorie_produit)" +
            ")";
    String SQL_DETRUIRE_TABLE_PRODUIT = "DROP TABLE IF EXISTS produit";

    // stock_compose_produit
    String SQL_CREER_TABLE_STOCK_COMPOSE_PRODUIT = "CREATE TABLE stock_compose_produit(" +
            "id_produit integer, " +
            "id_stock integer, " +
            "id_emplacement integer, " +
            "present_liste_course integer, " +
            "PRIMARY KEY(id_produit, id_stock), " +
            "FOREIGN KEY(id_produit) REFERENCES produit(id_produit), " +
            "FOREIGN KEY(id_stock) REFERENCES stock(id_stock), " +
            ")";
    String SQL_DETRUIRE_TABLE_STOCK_COMPOSE_PRODUIT = "DROP TABLE IF EXISTS stock_compose_produit";

    // historique_stock
    String SQL_CREER_TABLE_HISTORIQUE_STOCK = "CREATE TABLE historique_stock(" +
            "id_historique_stock integer PRIMARY KEY, " +
            "quantite real, " +
            "date_saisie date, " +
            "id_produit integer, " +
            "id_stock integer" +
//            "FOREIGN KEY(id_produit, id_stock) REFERENCES produit(id_produit, id_stock)" +
            ")";
    String SQL_DETRUIRE_TABLE_HISTORIQUE_STOCK = "DROP TABLE IF EXISTS historique_stock";
}
