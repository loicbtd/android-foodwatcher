package ca.qc.cgmatane.foodwatcher.donnees;

public interface ProduitSQL {
    String SQL_INSERER_PRODUIT = "INSERT INTO maison(etiquette) VALUES (?)";
    String SQL_LISTER_PRODUIT = "SELECT id_stock, etiquette FROM stock";
    String SQL_MODFIFIER_PRODUIT = "UPDATE stock SET etiquette = ? WHERE id_stock = ?";
}
