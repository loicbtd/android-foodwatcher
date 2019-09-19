\COPY utilisateur(email,mot_de_passe,nom,prenom,telephone,date_naissance) FROM './mockdata/utilisateur.csv' DELIMITER ',' CSV;

\COPY maison(etiquette) FROM './mockdata/maison.csv' DELIMITER ',' CSV;

\COPY maison_compose_utilisateur(id_maison,id_utilisateur) FROM './mockdata/maison_compose_utilisateur.csv' DELIMITER ',' CSV;

\COPY emplacement(etiquette) FROM './mockdata/emplacement.csv' DELIMITER ',' CSV;

\COPY categorie(etiquette) FROM './mockdata/categorie.csv' DELIMITER ',' CSV;

\COPY unite_quantite(etiquette) FROM './mockdata/unite_quantite.csv' DELIMITER ',' CSV;

\COPY produit FROM './mockdata/produit.csv' DELIMITER ';' CSV;

-- \COPY maison_compose_produit FROM './mockdata/maison_compose_produit.csv' DELIMITER ';' CSV;

-- \COPY historique_maison FROM './mockdata/historique_maison.csv' DELIMITER ';' CSV;



