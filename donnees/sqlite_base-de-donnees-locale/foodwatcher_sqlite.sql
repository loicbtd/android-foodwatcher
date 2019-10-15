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

-- créer
INSERT INTO stock(etiquette) VALUES ('Domicile')
INSERT INTO stock(etiquette) VALUES ('Mon restaurant')
INSERT INTO stock(etiquette) VALUES ('Maison vacances')
INSERT INTO emplacement(etiquette) VALUES ('Réfrigérateur')
INSERT INTO emplacement(etiquette) VALUES ('Réserve')
INSERT INTO emplacement(etiquette) VALUES ('Cave')
INSERT INTO categorie_produit(etiquette) VALUES ('Épicerie sucrée')
INSERT INTO categorie_produit(etiquette) VALUES ('Épicerie salée')
INSERT INTO categorie_produit(etiquette) VALUES ('Fruits & Légumes')
INSERT INTO unite_quantite(etiquette) VALUES ('g')
INSERT INTO unite_quantite(etiquette) VALUES ('mL')
INSERT INTO unite_quantite(etiquette) VALUES ('unité(s)')
INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('234567898762','Chocolat',1,1)
INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('444567898762','Sel',2,2)
INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES ('554567898762','Poire',3,3)
INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (1,1,100.0,1,0)
INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (2,1,200.0,2,1)
INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (3,2,100.0,3,0)

-- lire
-- SELECT id_stock, etiquette FROM stock;

-- SELECT id_emplacement, etiquette FROM emplacement;

-- SELECT id_categorie_produit, etiquette FROM categorie_produit;

-- SELECT id_unite_quantite, etiquette FROM unite_quantite;

-- SELECT id_produit, gencode, etiquette, id_unite_quantite, id_categorie_produit FROM produit;

-- SELECT id_produit, id_stock, quantite, id_emplacement, present_liste_course FROM stock_compose_produit;

-- lire avec conditions

-- SELECT id_stock, etiquette FROM stock WHERE id_stock=1;

-- SELECT id_emplacement, etiquette FROM emplacement WHERE id_emplacement=1;

-- SELECT id_categorie_produit, etiquette FROM categorie_produit WHERE id_categorie_produit=1;

-- SELECT id_unite_quantite, etiquette FROM unite_quantite WHERE id_unite_quantite=1;


-- lire (requêtes avec jointures)
-- trouver liste produit par id_stock
SELECT produit.id_produit, produit.gencode, produit.etiquette, produit.id_unite_quantite, produit.id_categorie_produit, stock_compose_produit.id_stock, stock_compose_produit.quantite, stock_compose_produit.id_emplacement, stock_compose_produit.present_liste_course
FROM stock_compose_produit
INNER JOIN produit ON stock_compose_produit.id_produit = produit.id_produit
WHERE stock_compose_produit.id_stock=1;
-- -- trouver unite_quantite par id_produit
-- SELECT unite_quantite.id_unite_quantite, unite_quantite.etiquette
-- FROM produit
-- INNER JOIN unite_quantite ON produit.id_unite_quantite = unite_quantite.id_unite_quantite
-- WHERE produit.id_produit=1;
-- -- trouver categorie_produit par id_produit
-- SELECT categorie_produit.id_categorie_produit, categorie_produit.etiquette
-- FROM produit
-- INNER JOIN categorie_produit ON produit.id_categorie_produit = categorie_produit.id_categorie_produit
-- WHERE produit.id_categorie_produit=1;
-- -- trouver emplacement par id_produit et id_stock
-- SELECT emplacement.id_emplacement, emplacement.etiquette
-- FROM stock_compose_produit
-- INNER JOIN emplacement ON stock_compose_produit.id_emplacement = emplacement.id_emplacement
-- WHERE stock_compose_produit.id_produit=1 AND stock_compose_produit.id_stock=1;

-- modifier
UPDATE stock SET etiquette='Restaurant' WHERE id_stock=1;

UPDATE emplacement SET etiquette='Réfrigérateur' WHERE id_emplacement=1;

UPDATE categorie_produit SET etiquette='Épicerie' WHERE id_categorie_produit=1;

UPDATE unite_quantite SET etiquette='unité(s)' WHERE id_unite_quantite=1;

UPDATE produit SET gencode='9876543212345',etiquette='Chocolat noir',id_unite_quantite=1,id_categorie_produit=1 WHERE id_produit=1;

UPDATE stock_compose_produit SET quantite=1.0,id_emplacement=1,present_liste_course=1 WHERE id_produit=1 AND id_stock=1;

-- supprimer
DELETE FROM stock_compose_produit WHERE id_produit=1 AND id_stock=1;

DELETE FROM produit WHERE id_produit=1;

DELETE FROM unite_quantite WHERE id_unite_quantite=1;

DELETE FROM categorie_produit WHERE id_categorie_produit=1;

DELETE FROM emplacement WHERE id_emplacement=1;

DELETE FROM stock WHERE id_stock=1;