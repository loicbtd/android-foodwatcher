\COPY utilisateur(email,mot_de_passe,nom,prenom,telephone,date_naissance) FROM './mockdata/utilisateur.csv' DELIMITER ',' CSV;

\COPY maison(etiquette) FROM './mockdata/maison.csv' DELIMITER ',' CSV;

\COPY maison_compose_utilisateur(id_maison,id_utilisateur) FROM './mockdata/maison_compose_utilisateur.csv' DELIMITER ',' CSV;

\COPY emplacement(etiquette) FROM './mockdata/emplacement.csv' DELIMITER ',' CSV;

\COPY categorie_produit(etiquette) FROM './mockdata/categorie_produit.csv' DELIMITER ',' CSV;

\COPY unite_quantite(etiquette) FROM './mockdata/unite_quantite.csv' DELIMITER ',' CSV;

\COPY produit(gencode,etiquette,nombre_jour_conservation,chemin_image,id_unite_quantite,id_categorie_produit) FROM './mockdata/produit.csv' DELIMITER ',' CSV;

\COPY maison_compose_produit(id_produit,id_maison,id_emplacement,present_liste_course) FROM './mockdata/maison_compose_produit.csv' DELIMITER ',' CSV;

\COPY historique_maison(quantite,date_saisie,id_produit,id_maison) FROM './mockdata/historique_maison.csv' DELIMITER ',' CSV;



