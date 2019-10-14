package ca.qc.cgmatane.foodwatcher.donnees;

public interface StockSQL {
    String SQL_INSERER_STOCK = "INSERT INTO stock(etiquette) VALUES (?)";
    String SQL_LISTER_STOCK = "SELECT id_stock, etiquette FROM stock";
    String SQL_MODFIFIER_STOCK = "UPDATE stock SET etiquette = ? WHERE id_stock = ?";
}
