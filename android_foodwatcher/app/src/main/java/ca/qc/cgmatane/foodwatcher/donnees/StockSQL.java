package ca.qc.cgmatane.foodwatcher.donnees;

public interface StockSQL {
    String SQL_CREER_STOCK = "INSERT INTO stock(etiquette) VALUES (?)";
    String SQL_LISTER_STOCK = "SELECT idStock, etiquette FROM stock";
    String SQL_MODIFIER_STOCK = "UPDATE stock SET etiquette=? WHERE idStock=?";
    String SQL_SUPPRIMER_STOCK = "DELETE FROM stock_compose_produit WHERE id_produit=? AND idStock=?";

    String SQL_TROUVER_STOCK_PAR_ID = "SELECT idStock, etiquette FROM stock WHERE idStock=?";
}
