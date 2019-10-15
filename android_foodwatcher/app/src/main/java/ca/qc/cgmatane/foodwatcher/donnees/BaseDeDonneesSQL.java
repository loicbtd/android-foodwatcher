package ca.qc.cgmatane.foodwatcher.donnees;

public interface BaseDeDonneesSQL {

    String SQL_NOM_BASE_DE_DONNEES = "foodwatcher_defaut";

    // stock
    String SQL_CREER_TABLE_STOCK = "CREATE TABLE stock(\n" +
            "    id_stock integer PRIMARY KEY,\n" +
            "    etiquette text\n" +
            ")";
    String SQL_DETRUIRE_TABLE_STOCK = "DROP TABLE IF EXISTS stock";

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
            "    id_unite_quantite integer,\n" +
            "    id_categorie_produit integer,\n" +
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
            "    id_produit integer,\n" +
            "    id_stock integer,\n" +
            "    quantite real,\n" +
            "    id_emplacement integer,\n" +
            "    present_liste_course integer,\n" +
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

    String[] SQL_INSERER_DONNEES_MOCK = new String[]{
            "INSERT INTO stock(etiquette) VALUES ('Domicile')",
            "INSERT INTO stock(etiquette) VALUES ('Mon restaurant')",
            "INSERT INTO stock(etiquette) VALUES ('Maison vacances')",
            "INSERT INTO emplacement(etiquette) VALUES ('Réfrigérateur')",
            "INSERT INTO emplacement(etiquette) VALUES ('Réserve')",
            "INSERT INTO emplacement(etiquette) VALUES ('Cave')",
            "INSERT INTO categorie_produit(etiquette) VALUES ('Épicerie sucrée')",
            "INSERT INTO categorie_produit(etiquette) VALUES ('Épicerie salée')",
            "INSERT INTO categorie_produit(etiquette) VALUES ('Fruits & Légumes')",
            "INSERT INTO unite_quantite(etiquette) VALUES ('g')",
            "INSERT INTO unite_quantite(etiquette) VALUES ('mL')",
            "INSERT INTO unite_quantite(etiquette) VALUES ('unité(s)')",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('234567898762','Chocolat',1,1)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('444567898762','Sel',2,2)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('554567898762','Poire',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('554567878762','Banane',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('554567868762','Pomme',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('554567848762','Kiwi',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('554567818762','Mangue',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('554567898762','Ananas',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('559567898762','Citron',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('556567898762','Orange',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('557567898762','Fraise',3,3)",
            "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('558567898762','Framboise',3,3)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (1,1,100.0,1,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (2,1,200.0,2,1)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (3,1,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (4,1,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (5,1,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (6,1,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (7,1,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (8,1,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (9,2,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (10,2,100.0,3,0)",
            "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (11,2,100.0,3,0)"
    };
}
