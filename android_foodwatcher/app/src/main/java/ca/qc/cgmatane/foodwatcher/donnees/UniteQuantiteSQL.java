package ca.qc.cgmatane.foodwatcher.donnees;

public interface UniteQuantiteSQL {
    String SQL_CREER_UNITE_QUANTITE = "INSERT INTO unite_quantite(etiquette) VALUES (?)";
    String SQL_LISTER_UNITE_QUANTITE = "SELECT id_unite_quantite, etiquette FROM unite_quantite";
    String SQL_MODIFIER_UNITE_QUANTITE = "UPDATE unite_quantite SET etiquette=? WHERE id_unite_quantite=?";
    String SQL_SUPPRIMER_UNITE_QUANTITE = "DELETE FROM unite_quantite WHERE id_unite_quantite=?";
}
