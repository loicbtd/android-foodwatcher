package ca.qc.cgmatane.foodwatcher.donnees;

public interface UniteQuantiteSQL {
    String SQL_INSERER_UNITE_QUANTITE = "INSERT INTO unite_quantite(etiquette) VALUES (?)";
    String SQL_LISTER_UNITE_QUANTITE = "SELECT id_unite_quantite, etiquette FROM unite_quantite";
    String SQL_MODFIFIER_UNITE_QUANTITE = "UPDATE unite_quantite SET etiquette = ? WHERE id_unite_quantite = ?";
}
