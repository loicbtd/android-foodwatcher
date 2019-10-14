package ca.qc.cgmatane.foodwatcher.donnees;

public interface ProduitSQL {
    String SQL_CREER_PRODUIT = "INSERT INTO produit(gencode,etiquette,id_unite_quantite,id_categorie_produit) VALUES (?,?,?,?)";
    String SQL_LISTER_PRODUIT = "SELECT id_produit, gencode, etiquette, id_unite_quantite, id_categorie_produit FROM produit";
    String SQL_MODIFIER_PRODUIT = "UPDATE produit SET gencode=?,etiquette=?,id_unite_quantite=?,id_categorie_produit=? WHERE id_produit=?";
    String SQL_SUPPRIMER_PRODUIT = "DELETE FROM produit WHERE id_produit=?";

    String SQL_TROUVER_UNITE_QUANTITE_PAR_ID_PRODUIT = "SELECT unite_quantite.id_unite_quantite, unite_quantite.etiquette\n" +
            "FROM produit\n" +
            "INNER JOIN unite_quantite ON produit.id_unite_quantite = unite_quantite.id_unite_quantite\n" +
            "WHERE produit.id_produit=?";

    String SQL_TROUVER_CATEGORIE_PRODUIT_PAR_ID_PRODUIT = "SELECT categorie_produit.id_categorie_produit, categorie_produit.etiquette\n" +
            "FROM produit\n" +
            "INNER JOIN categorie_produit ON produit.id_categorie_produit = categorie_produit.id_categorie_produit\n" +
            "WHERE produit.id_categorie_produit=?";
}
