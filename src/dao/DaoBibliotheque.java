package dao;

import entity.Bibliotheque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoBibliotheque extends Dao<Bibliotheque>
{
    @Override
    public Bibliotheque find(int id)
    {
        Bibliotheque bibliotheque = null;

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Bibliotheque WHERE NumBibliotheque = ?");
            sql.setInt(1, id);
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            if(resultat.first())
            {
                bibliotheque = new Bibliotheque(resultat.getInt("NumBibliotheque"),
                                                resultat.getString("NomBibliotheque"),
                                                resultat.getString("Adresse"),
                                                resultat.getInt("NombreRangees"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return bibliotheque;
    }

    @Override
    public ArrayList<Bibliotheque> findAll()
    {
        ArrayList<Bibliotheque> listeBibliotheques = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Bibliotheque");
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Bibliotheque bibliotheque = new Bibliotheque(resultat.getInt("NumBibliotheque"),
                                                            resultat.getString("NomBibliotheque"),
                                                            resultat.getString("Adresse"),
                                                            resultat.getInt("NombreRangees"));

                listeBibliotheques.add(bibliotheque);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeBibliotheques;
    }

    @Override
    public Bibliotheque create(Bibliotheque bibliotheque)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO Bibliotheque (NomBibliotheque, Adresse, NombreRangees) " +
                                                                    "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setString(i++, bibliotheque.getNomBibliotheque());
            sql.setString(i++, bibliotheque.getAdresse());
            sql.setInt(i++, bibliotheque.getNombreRangees());
            sql.executeUpdate();

            ResultSet keys = sql.getGeneratedKeys();
            keys.next();
            //bibliotheque.NumBibliotheque = keys.getInt(1);
            bibliotheque = find(bibliotheque.getNumBibliotheque());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return bibliotheque;
    }

    @Override
    public boolean update(Bibliotheque bibliotheque)
    {
        try
        {
            PreparedStatement sql = this.connexion.prepareStatement("UPDATE Bibliotheque " +
                                                                        "SET NomBibliotheque = ?, Adresse = ?, NombreRangees = ? " +
                                                                        "WHERE NumBibliotheque = ?");

            int i = 1;
            sql.setString(i++, bibliotheque.getNomBibliotheque());
            sql.setString(i++, bibliotheque.getAdresse());
            sql.setInt(i++, bibliotheque.getNombreRangees());
            sql.setInt(i++, bibliotheque.getNumBibliotheque());

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
    public boolean delete(Bibliotheque bibliotheque)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("DELETE FROM armoire WHERE NumBibliotheque = ?");
            sql.setInt(1, bibliotheque.getNumBibliotheque());
            sql.executeUpdate();

            PreparedStatement statement = connexion.prepareStatement("DELETE FROM bibliotheque WHERE NumBibliotheque = ?");
            statement.setInt(1, bibliotheque.getNumBibliotheque());
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
