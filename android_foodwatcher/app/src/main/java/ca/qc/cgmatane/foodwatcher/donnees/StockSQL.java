package ca.qc.cgmatane.foodwatcher.donnees;

public interface StockSQL {
    String SQL_INSERER_MAISON = "INSERT INTO maison(etiquette) VALUES (?)";
    String SQL_LISTER_MAISON = "SELECT id_maison, etiquette FROM maison";
    String SQL_MODFIFIER_MAISON = "UPDATE maison SET etiquette = ? WHERE id_maison = ?";
}
