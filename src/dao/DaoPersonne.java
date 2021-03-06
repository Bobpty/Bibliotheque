package dao;

import entity.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoPersonne extends Dao<Personne>
{
	/**
	 * @param l'identifiant de la personne recherch�e
	 * @return une personne selon son identifiant en param�tre
	 */
    @Override
    public Personne find(int id)
    {
        Personne personne = null;

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Personne WHERE IDpersonne = ?");
            sql.setInt(1, id);
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            if(resultat.first())
            {
                personne = new Personne(resultat.getInt("IDpersonne"),
                                        resultat.getString("Nom"),
                                        resultat.getString("Prenom"),
                                        resultat.getString("Adresse"),
                                        resultat.getString("Mail"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return personne;
    }

    /**
     * Retourne une personne par son nom
     * @param le nom de la personne
     * @return la personne recherch�e
     */
	public Personne findByNom(String nomLoueur)
	{
		Personne personne = null;

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Personne WHERE Nom = ?");
            sql.setString(1, nomLoueur);
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            if(resultat.first())
            {
                personne = new Personne(resultat.getInt("IDpersonne"),
                                        resultat.getString("Nom"),
                                        resultat.getString("Prenom"),
                                        resultat.getString("Adresse"),
                                        resultat.getString("Mail"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return personne;
	}
    
    /**
     * @return une ArrayList de toutes les personnes enregistr�es
     */
    @Override
    public List<Personne> findAll()
    {
        ArrayList<Personne> listePersonnes = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Personne");
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Personne personne = new Personne(resultat.getInt("IDpersonne"),
                                                resultat.getString("Nom"),
                                                resultat.getString("Prenom"),
                                                resultat.getString("Adresse"),
                                                resultat.getString("Mail"));

                listePersonnes.add(personne);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listePersonnes;
    }

    /**
     * cr�ation d'une personne
     */
    @Override
    public Personne create(Personne personne)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO Utilisateur (Nom, Prenom, Adresse, Mail) " +
                                                                          "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setString(i++, personne.getNom());
            sql.setString(i++, personne.getPrenom());
            sql.setString(i++, personne.getAdresse());
            sql.setString(i++, personne.getMail());
            sql.executeUpdate();

            ResultSet keys = sql.getGeneratedKeys();
            keys.next();
            //personne.IDpersonne = keys.getInt(1);
            personne = find(personne.getIDpersonne());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return personne;
    }

    /**
     * modification d'une personne
     */
    @Override
    public boolean update(Personne personne)
    {
        try
        {
            PreparedStatement sql = this.connexion.prepareStatement("UPDATE Personne " +
                                                                        "SET Nom = ?, Prenom = ?, Adresse = ?, Mail = ? " +
                                                                        "WHERE IDpersonne = ?");

            int i = 1;
            sql.setString(i++, personne.getNom());
            sql.setString(i++, personne.getPrenom());
            sql.setString(i++, personne.getAdresse());
            sql.setString(i++, personne.getMail());
            sql.setInt(i++, personne.getIDpersonne());

            sql.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * suppression d'une personne
     */
    @Override
    public boolean delete(Personne personne)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("DELETE FROM louer WHERE IDpersonne = ?");
            sql.setInt(1, personne.getIDpersonne());
            sql.executeUpdate();

            PreparedStatement statement = connexion.prepareStatement("DELETE FROM Personne WHERE id = ?");
            statement.setInt(1, personne.getIDpersonne());
            statement.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
