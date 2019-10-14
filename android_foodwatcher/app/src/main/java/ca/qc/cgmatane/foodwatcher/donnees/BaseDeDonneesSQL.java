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
            "    id_stock integer PRIMARY KEY,\n" +
            "    etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_STOCK = "DROP TABLE IF EXISTS stock";

    
    // stock_compose_utilisateur
    String SQL_CREER_TABLE_STOCK_COMPOSE_UTILISATEUR = "CREATE TABLE stock_compose_utilisateur(\n" +
            "    id_stock integer NOT NULL,\n" +
            "    id_utilisateur integer NOT NULL,\n" +
            "    PRIMARY KEY(id_stock,id_utilisateur),\n" +
            "    CONSTRAINT stock_stock_compose_utilisateur_fk\n" +
            "        FOREIGN KEY (id_stock)\n" +
            "        REFERENCES stock(id_stock)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CONSTRAINT utilisateur_stock_compose_utilisateur_fk\n" +
            "        FOREIGN KEY (id_utilisateur)\n" +
            "        REFERENCES utilisateur(id_utilisateur)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE\n" +
            ")";
    String SQL_DETRUIRE_TABLE_STOCK_COMPOSE_UTILISATEUR = "DROP TABLE IF EXISTS stock_compose_utilisateur";

    // emplacement
    String SQL_CREER_TABLE_EMPLACEMENT = "CREATE TABLE emplacement(\n" +
            "    id_emplacement integer PRIMARY KEY,\n" +
            "    etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_EMPLACEMENT = "DROP TABLE IF EXISTS emplacement";

    // categorie_produit
    String SQL_CREER_TABLE_CATEGORIE_PRODUIT = "CREATE TABLE categorie_produit(\n" +
            "    id_categorie_produit integer PRIMARY KEY,\n" +
            "    etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_CATEGORIE_PRODUIT = "DROP TABLE IF EXISTS categorie_produit";

    // unite_quantite
    String SQL_CREER_TABLE_UNITE_QUANTITE = "CREATE TABLE unite_quantite(\n" +
            "    id_unite_quantite integer PRIMARY KEY,\n" +
            "    etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_UNITE_QUANTITE = "DROP TABLE IF EXISTS unite_quantite";

    // produit
    String SQL_CREER_TABLE_PRODUIT = "CREATE TABLE produit(\n" +
            "    id_produit integer PRIMARY KEY,\n" +
            "    gencode integer,\n" +
            "    etiquette text,\n" +
            "    nombre_jour_conservation integer,\n" +
            "    chemin_image text,\n" +
            "    id_unite_quantite integer NOT NULL,\n" +
            "    id_categorie_produit integer NOT NULL,\n" +
            "    CONSTRAINT unite_quantite_produit_fk\n" +
            "        FOREIGN KEY (id_unite_quantite)\n" +
            "        REFERENCES unite_quantite(id_unite_quantite)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CONSTRAINT categorie_produit_produit_fk\n" +
            "        FOREIGN KEY (id_categorie_produit)\n" +
            "        REFERENCES categorie_produit(id_categorie_produit)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE\n" +
            ")";
    String SQL_DETRUIRE_TABLE_PRODUIT = "DROP TABLE IF EXISTS produit";

    // stock_compose_produit
    String SQL_CREER_TABLE_STOCK_COMPOSE_PRODUIT = "CREATE TABLE stock_compose_produit(\n" +
            "    id_produit integer NOT NULL,\n" +
            "    id_stock integer NOT NULL,\n" +
            "    id_emplacement integer,\n" +
            "    present_liste_course boolean NOT NULL,\n" +
            "    PRIMARY KEY(id_produit,id_stock),\n" +
            "    CONSTRAINT produit_stock_compose_produit_fk\n" +
            "        FOREIGN KEY (id_produit)\n" +
            "        REFERENCES produit(id_produit)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CONSTRAINT stock_stock_compose_produit_fk\n" +
            "        FOREIGN KEY (id_stock)\n" +
            "        REFERENCES stock(id_stock)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE,\n" +
            "    CONSTRAINT emplacement_stock_compose_produit_fk\n" +
            "        FOREIGN KEY (id_emplacement)\n" +
            "        REFERENCES emplacement(id_emplacement)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE\n" +
            ")";
    String SQL_DETRUIRE_TABLE_STOCK_COMPOSE_PRODUIT = "DROP TABLE IF EXISTS stock_compose_produit";

    // historique_stock
    String SQL_CREER_TABLE_HISTORIQUE_STOCK = "CREATE TABLE historique_stock(\n" +
            "    id_historique_stock integer PRIMARY KEY,\n" +
            "    quantite float NOT NULL,\n" +
            "    date_saisie date NOT NULL,\n" +
            "    id_produit integer NOT NULL,\n" +
            "    id_stock integer NOT NULL,\n" +
            "    CONSTRAINT stock_compose_produit_historique_stock_fk\n" +
            "        FOREIGN KEY (id_produit,id_stock)\n" +
            "        REFERENCES stock_compose_produit(id_produit,id_stock)\n" +
            "        ON DELETE CASCADE\n" +
            "        ON UPDATE CASCADE\n" +
            ")";
    String SQL_DETRUIRE_TABLE_HISTORIQUE_STOCK = "DROP TABLE IF EXISTS historique_stock";
}
