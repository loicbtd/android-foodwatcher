DROP TABLE IF EXISTS historique_stock;
DROP TABLE IF EXISTS stock_compose_produit;
DROP TABLE IF EXISTS produit;
DROP TABLE IF EXISTS unite_quantite;
DROP TABLE IF EXISTS categorie_produit;
DROP TABLE IF EXISTS emplacement;
DROP TABLE IF EXISTS stock_compose_utilisateur;
DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS utilisateur;


CREATE TABLE utilisateur(
    id_utilisateur integer PRIMARY KEY,
    email text,
    mot_de_passe text,
    nom text,
    prenom text,
    telephone text,
    date_naissance date
);

CREATE TABLE stock(
    id_stock integer PRIMARY KEY,
    etiquette text
);

CREATE TABLE stock_compose_utilisateur(
    id_stock integer NOT NULL,
    id_utilisateur integer NOT NULL,
    PRIMARY KEY(id_stock,id_utilisateur),
    CONSTRAINT stock_stock_compose_utilisateur_fk
        FOREIGN KEY (id_stock)
        REFERENCES stock(id_stock)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT utilisateur_stock_compose_utilisateur_fk
        FOREIGN KEY (id_utilisateur)
        REFERENCES utilisateur(id_utilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE emplacement(
    id_emplacement integer PRIMARY KEY,
    etiquette text
);

CREATE TABLE categorie_produit(
    id_categorie_produit integer PRIMARY KEY,
    etiquette text
);

CREATE TABLE unite_quantite(
    id_unite_quantite integer PRIMARY KEY,
    etiquette text
);

CREATE TABLE produit(
    id_produit integer PRIMARY KEY,
    gencode integer,
    etiquette text,
    nombre_jour_conservation integer,
    chemin_image text,
    id_unite_quantite integer NOT NULL,
    id_categorie_produit integer NOT NULL,
    CONSTRAINT unite_quantite_produit_fk
        FOREIGN KEY (id_unite_quantite)
        REFERENCES unite_quantite(id_unite_quantite)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT categorie_produit_produit_fk
        FOREIGN KEY (id_categorie_produit)
        REFERENCES categorie_produit(id_categorie_produit)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE stock_compose_produit(
    id_produit integer NOT NULL,
    id_stock integer NOT NULL,
    id_emplacement integer,
    present_liste_course boolean NOT NULL,
    PRIMARY KEY(id_produit,id_stock),
    CONSTRAINT produit_stock_compose_produit_fk
        FOREIGN KEY (id_produit)
        REFERENCES produit(id_produit)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT stock_stock_compose_produit_fk
        FOREIGN KEY (id_stock)
        REFERENCES stock(id_stock)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT emplacement_stock_compose_produit_fk
        FOREIGN KEY (id_emplacement)
        REFERENCES emplacement(id_emplacement)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE historique_stock(
    id_historique_stock integer PRIMARY KEY,
    quantite float NOT NULL,
    date_saisie date NOT NULL,
    id_produit integer NOT NULL,
    id_stock integer NOT NULL,
    CONSTRAINT stock_compose_produit_historique_stock_fk
        FOREIGN KEY (id_produit,id_stock)
        REFERENCES stock_compose_produit(id_produit,id_stock)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);