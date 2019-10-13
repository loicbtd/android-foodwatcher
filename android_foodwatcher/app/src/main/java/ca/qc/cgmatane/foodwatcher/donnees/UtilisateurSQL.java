package ca.qc.cgmatane.foodwatcher.donnees;

public interface UtilisateurSQL {
    String SQL_INSERER_UTILISATEUR = "INSERT INTO utilisateur(email, mot_de_passe, nom, prenom, telephone, date_naissance) VALUES (?,?,?,?,?,?)";
    String SQL_LISTER_UTILISATEUR = "SELECT id_utilisateur, email, mot_de_passe, nom, prenom, telephone, date_naissance FROM utilisateur";
    String SQL_MODFIFIER_UTILISATEUR = "UPDATE utilisateur SET email = ?, mot_de_passe = ?, nom = ?, prenom = ?, telephone = ?, date_naissance = ? WHERE id_utilisateur = ?";
}
