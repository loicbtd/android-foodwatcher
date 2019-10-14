package ca.qc.cgmatane.foodwatcher.donnees;

public interface ProduitStockeSQL {
    String SQL_CREER_STOCK_COMPOSE_PRODUIT = "INSERT INTO stock_compose_produit(id_produit,id_stock,quantite,id_emplacement,present_liste_course) VALUES (?,?,?,?,?)";
    String SQL_LISTER_STOCK_COMPOSE_PRODUIT = "SELECT id_produit, id_stock, quantite, id_emplacement, present_liste_course FROM stock_compose_produit";
    String SQL_MODIFIER_STOCK_COMPOSE_PRODUIT = "UPDATE stock_compose_produit SET quantite=?,id_emplacement=?,present_liste_course=? WHERE id_produit=? AND id_stock=?";
    String SQL_SUPPRIMER_STOCK_COMPOSE_PRODUIT = "DELETE FROM stock_compose_produit WHERE id_produit=? AND id_stock=?";

    String SQL_TROUVER_LISTE_PRODUIT_PAR_ID_STOCK = "SELECT produit.id_produit, produit.gencode, produit.etiquette, produit.id_unite_quantite, produit.id_categorie_produit, stock_compose_produit.id_stock, stock_compose_produit.quantite, stock_compose_produit.id_emplacement, stock_compose_produit.present_liste_course\n" +
            "FROM stock_compose_produit\n" +
            "INNER JOIN produit ON stock_compose_produit.id_produit = produit.id_produit\n" +
            "WHERE stock_compose_produit.id_stock=?";

    String SQL_TROUVER_EMPLACEMENT_PAR_ID_PRODUIT_ET_ID_STOCK = "SELECT emplacement.id_emplacement, emplacement.etiquette\n" +
            "FROM stock_compose_produit\n" +
            "INNER JOIN emplacement ON stock_compose_produit.id_emplacement = emplacement.id_emplacement\n" +
            "WHERE stock_compose_produit.id_produit=? AND stock_compose_produit.id_stock=?";
}
