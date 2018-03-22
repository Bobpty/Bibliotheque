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
    @Override
    public Personne find(int id)
    {
        Personne personne = null;

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Personne WHERE id= ?");
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

    @Override
    public Personne create(Personne personne)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO Utilisateur (Nom, Prenom, Adresse, Mail) " +
                                                                          "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setString(i++, personne.Nom);
            sql.setString(i++, personne.Prenom);
            sql.setString(i++, personne.Adresse);
            sql.setString(i++, personne.Mail);
            sql.executeUpdate();

            ResultSet keys = sql.getGeneratedKeys();
            keys.next();
            personne.IDpersonne = keys.getInt(1);
            personne = find(personne.IDpersonne);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return personne;
    }

    @Override
    public boolean update(Personne personne)
    {
        try
        {
            PreparedStatement sql = this.connexion.prepareStatement("UPDATE Personne " +
                                                                        "SET Nom = ?, Prenom = ?, Adresse = ?, Mail = ? " +
                                                                        "WHERE IDpersonne = ?");

            int i = 1;
            sql.setString(i++, personne.Nom);
            sql.setString(i++, personne.Prenom);
            sql.setString(i++, personne.Adresse);
            sql.setString(i++, personne.Mail);
            sql.setInt(i++, personne.IDpersonne);

            sql.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Personne personne)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("DELETE FROM louer WHERE IDpersonne = ?");
            sql.setInt(1, personne.IDpersonne);
            sql.executeUpdate();

            PreparedStatement statement = connexion.prepareStatement("DELETE FROM Personne WHERE id = ?");
            statement.setInt(1, personne.IDpersonne);
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
