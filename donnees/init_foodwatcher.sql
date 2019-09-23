DROP TABLE IF EXISTS historique_maison;
DROP TABLE IF EXISTS maison_compose_produit;
DROP TABLE IF EXISTS produit;
DROP TABLE IF EXISTS unite_quantite;
DROP TABLE IF EXISTS categorie_produit;
DROP TABLE IF EXISTS emplacement;
DROP TABLE IF EXISTS maison_compose_utilisateur;
DROP TABLE IF EXISTS maison;
DROP TABLE IF EXISTS utilisateur;


CREATE TABLE utilisateur(
    id_utilisateur serial PRIMARY KEY,
    email text,
    mot_de_passe text,
    nom text,
    prenom text,
    telephone text,
    date_naissance date
);

CREATE TABLE maison(
    id_maison serial PRIMARY KEY,
    etiquette text
);

CREATE TABLE maison_compose_utilisateur(
    id_maison integer NOT NULL,
    id_utilisateur integer NOT NULL,
    CONSTRAINT maison_utilisateur_pfk 
        PRIMARY KEY(id_maison,id_utilisateur),
    CONSTRAINT maison_maison_compose_utilisateur_fk
        FOREIGN KEY (id_maison)
        REFERENCES maison(id_maison)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT utilisateur_maison_compose_utilisateur_fk
        FOREIGN KEY (id_utilisateur)
        REFERENCES utilisateur(id_utilisateur)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE emplacement(
    id_emplacement serial PRIMARY KEY,
    etiquette text
);

CREATE TABLE categorie_produit(
    id_categorie_produit serial PRIMARY KEY,
    etiquette text
);

CREATE TABLE unite_quantite(
    id_unite_quantite serial PRIMARY KEY,
    etiquette text
);

CREATE TABLE produit(
    id_produit serial PRIMARY KEY,
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

CREATE TABLE maison_compose_produit(
    id_produit integer NOT NULL,
    id_maison integer NOT NULL,
    id_emplacement integer,
    present_liste_course boolean NOT NULL,
    CONSTRAINT maison_produit_pfk
        PRIMARY KEY(id_produit,id_maison),
    CONSTRAINT produit_maison_compose_produit_fk
        FOREIGN KEY (id_produit)
        REFERENCES produit(id_produit)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT maison_maison_compose_produit_fk
        FOREIGN KEY (id_maison)
        REFERENCES maison(id_maison)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT emplacement_maison_compose_produit_fk
        FOREIGN KEY (id_emplacement)
        REFERENCES emplacement(id_emplacement)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE historique_maison(
    id_historique_maison serial PRIMARY KEY,
    quantite float NOT NULL,
    date_saisie date NOT NULL,
    id_produit integer NOT NULL,
    id_maison integer NOT NULL,
    CONSTRAINT maison_compose_produit_historique_maison_fk
        FOREIGN KEY (id_produit,id_maison)
        REFERENCES maison_compose_produit(id_produit,id_maison)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);