package ca.qc.cgmatane.foodwatcher.donnees;

public interface ProduitSQL {
    String SQL_INSERER_PRODUIT = "INSERT INTO maison(etiquette) VALUES (?)";
    String SQL_LISTER_PRODUIT = "SELECT id_maison, etiquette FROM maison";
    String SQL_MODFIFIER_PRODUIT = "UPDATE maison SET etiquette = ? WHERE id_maison = ?";
}
