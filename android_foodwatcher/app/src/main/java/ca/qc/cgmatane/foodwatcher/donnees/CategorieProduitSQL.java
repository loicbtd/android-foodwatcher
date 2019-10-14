package ca.qc.cgmatane.foodwatcher.donnees;

public interface CategorieProduitSQL {
    String SQL_CREER_CATEGORIE_PRODUIT = "INSERT INTO categorie_produit(etiquette) VALUES (?)";
    String SQL_LISTER_CATEGORIE_PRODUIT = "SELECT id_categorie_produit, etiquette FROM categorie_produit";
    String SQL_MODIFIER_CATEGORIE_PRODUIT = "UPDATE categorie_produit SET etiquette=? WHERE id_categorie_produit=?";
    String SQL_SUPPRIMER_CATEGORIE_PRODUIT = "DELETE FROM unite_quantite WHERE id_unite_quantite=?";

    String SQL_TROUVER_CATEGORIE_PRODUIT_PAR_ID = "SELECT id_categorie_produit, etiquette FROM categorie_produit WHERE id_categorie_produit=?";
}
