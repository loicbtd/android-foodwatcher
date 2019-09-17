DROP TABLE IF EXISTS historique_maison;
DROP TABLE IF EXISTS maison_compose_produit;
DROP TABLE IF EXISTS produit;
DROP TABLE IF EXISTS unite_quantite;
DROP TABLE IF EXISTS categorie;
DROP TABLE IF EXISTS piece;
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
    date_naissance datetime
);

CREATE TABLE maison(
    id_maison serial PRIMARY KEY,
    etiquette text
);

CREATE TABLE maison_compose_utilisateur(
    id_maison_compose_utilisateur serial PRIMARY KEY,
    id_maison integer NOT NULL,
    id_utilisateur integer NOT NULL,
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

CREATE TABLE piece(
    id_piece serial PRIMARY KEY,
    etiquette text
);

CREATE TABLE categorie(
    id_categorie serial PRIMARY KEY,
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
    id_categorie integer NOT NULL,
    CONSTRAINT unite_quantite_produit_fk
        FOREIGN KEY (id_unite_quantite)
        REFERENCES unite_quantite(id_unite_quantite)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT categorie_produit_fk
        FOREIGN KEY (id_categorie)
        REFERENCES categorie(id_categorie)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE maison_compose_produit(
    id_maison_compose_produit serial PRIMARY KEY,
    id_produit integer NOT NULL,
    id_maison integer NOT NULL,
    id_piece integer NOT NULL,
    present_liste_course boolean NOT NULL,
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
    CONSTRAINT piece_maison_compose_produit_fk
        FOREIGN KEY (id_piece)
        REFERENCES piece(id_piece)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE historique_maison(
    id_historique_maison serial PRIMARY KEY,
    date_saisie datetime NOT NULL,
    id_maison_compose_produit integer NOT NULL,
    CONSTRAINT maison_compose_produit_historique_maison_fk
        FOREIGN KEY (id_maison_compose_produit)
        REFERENCES maison_compose_produit(id_maison_compose_produit)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);