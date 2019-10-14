package ca.qc.cgmatane.foodwatcher.donnees;

public interface EmplacementSQL {
    String SQL_CREER_EMPLACEMENT = "INSERT INTO emplacement(etiquette) VALUES (?)";
    String SQL_LISTER_EMPLACEMENT = "SELECT id_emplacement, etiquette FROM emplacement";
    String SQL_MODIFIER_EMPLACEMENT = "UPDATE emplacement SET etiquette=? WHERE id_emplacement=?";
    String SQL_SUPPRIMER_EMPLACEMENT = "DELETE FROM emplacement WHERE id_emplacement=?";
}