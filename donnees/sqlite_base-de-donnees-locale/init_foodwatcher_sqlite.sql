DROP TABLE IF EXISTS stock_compose_produit;
DROP TABLE IF EXISTS produit;
DROP TABLE IF EXISTS unite_quantite;
DROP TABLE IF EXISTS categorie_produit;
DROP TABLE IF EXISTS emplacement;
DROP TABLE IF EXISTS stock;

CREATE TABLE stock(
    id_stock integer PRIMARY KEY,
    etiquette text
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
    id_unite_quantite integer,
    id_categorie_produit integer,
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
    id_produit integer,
    id_stock integer,
    quantite real,
    id_emplacement integer,
    present_liste_course integer,
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