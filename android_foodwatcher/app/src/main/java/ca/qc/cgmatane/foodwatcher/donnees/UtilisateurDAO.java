package ca.qc.cgmatane.foodwatcher.donnees;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.modele.Utilisateur;

public class UtilisateurDAO implements UtilisateurSQL {

    private static UtilisateurDAO instance = null;
    protected List<Utilisateur> listeUtilisateur;

    private BaseDeDonnees baseDeDonnees;

    public static UtilisateurDAO getInstance() {
        if (null == instance) {
            instance = new UtilisateurDAO();
        }
        return instance;
    }

    public UtilisateurDAO() {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeUtilisateur = new ArrayList<>();
        ajouterListeUtilisateurMock();
    }

    public List<Utilisateur> recupererListeUtilisateur() {
        Cursor curseur = baseDeDonnees.getReadableDatabase()
                .rawQuery(SQL_LISTER_UTILISATEUR, null);
        this.listeUtilisateur.clear();

        Utilisateur utilisateur;
        int indexId_utilisateur = curseur.getColumnIndex(Utilisateur.CLE_ID_UTILISATEUR);
        int indexEmail = curseur.getColumnIndex(Utilisateur.CLE_EMAIL);
        int indexMot_de_passe = curseur.getColumnIndex(Utilisateur.CLE_MOT_DE_PASSE);
        int indexNom = curseur.getColumnIndex(Utilisateur.CLE_NOM);
        int indexPrenom = curseur.getColumnIndex(Utilisateur.CLE_PRENOM);
        int indexTelephone = curseur.getColumnIndex(Utilisateur.CLE_TELEPHONE);
        int indexDate_de_naissance = curseur.getColumnIndex(Utilisateur.CLE_DATE_NAISSANCE);

        for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {
            int id_utilisateur = curseur.getInt(indexId_utilisateur);
            String email = curseur.getString(indexEmail);
            String mot_de_passe = curseur.getString(indexMot_de_passe);
            String nom = curseur.getString(indexNom);
            String prenom = curseur.getString(indexPrenom);
            String telephone = curseur.getString(indexTelephone);
            String date_de_naissance = curseur.getString(indexDate_de_naissance);
            utilisateur = new Utilisateur(id_utilisateur, email, mot_de_passe, nom, prenom, telephone, date_de_naissance);
            this.listeUtilisateur.add(utilisateur);
        }
        return listeUtilisateur;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_INSERER_UTILISATEUR);
        sqLiteStatement.bindString(1, utilisateur.getEmail());
        sqLiteStatement.bindString(2, utilisateur.getMot_de_passe());
        sqLiteStatement.bindString(3, utilisateur.getNom());
        sqLiteStatement.bindString(4, utilisateur.getPrenom());
        sqLiteStatement.bindString(5, utilisateur.getTelephone());
        sqLiteStatement.bindString(6, utilisateur.getDate_naissance());
        sqLiteStatement.execute();
    }

    public void modifierUtilisateur(Utilisateur utilisateur) {
        SQLiteDatabase sqLiteDatabase = baseDeDonnees.getWritableDatabase();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(SQL_MODFIFIER_UTILISATEUR);
        sqLiteStatement.bindString(1, utilisateur.getEmail());
        sqLiteStatement.bindString(2, utilisateur.getMot_de_passe());
        sqLiteStatement.bindString(3, utilisateur.getNom());
        sqLiteStatement.bindString(4, utilisateur.getPrenom());
        sqLiteStatement.bindString(5, utilisateur.getTelephone());
        sqLiteStatement.bindString(6, utilisateur.getDate_naissance());
        sqLiteStatement.bindString(7, ""+utilisateur.getId_utilisateur());
        sqLiteStatement.execute();
    }

    public void ajouterListeUtilisateurMock() {
        ajouterUtilisateur(new Utilisateur(
                0,
                "gilles.berre@mail.com",
                "motdepasse",
                "Berre",
                "Gilles",
                "0192837438",
                "1994-01-23"
        ));

        ajouterUtilisateur(new Utilisateur(
                1,
                "julie.corne@mail.com",
                "motdepasse",
                "Corne",
                "Julie",
                "0923874839",
                "1991-04-25"
        ));

        ajouterUtilisateur(new Utilisateur(
                2,
                "jean.braise@mail.com",
                "motdepasse",
                "Braise",
                "Jean",
                "0478394432",
                "1990-04-14"
        ));
    }
}
