package dao;

import entity.Bibliotheque;
import entity.Medium;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoMedium extends Dao<Medium>
{
    @Override
    public Medium find(int id)
    {
        Medium medium = null;

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Medium WHERE NumMedium = ?");
            sql.setInt(1, id);
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            if(resultat.first())
            {
                medium = new Medium(resultat.getInt("NumMedium"),
                                    resultat.getString("Titre"),
                                    resultat.getString("InterRealAuteur"),
                                    resultat.getInt("Contenant"),
                                    resultat.getDate("DateParution"),
                                    resultat.getDate("DateStockage"),
                                    resultat.getFloat("Prix"),
                                    resultat.getInt("DureeLocation"),
                                    resultat.getString("Type"),
                                    new DaoArmoire().find(resultat.getInt("NumArmoire")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return medium;
    }
  
    @Override
    public List<Medium> findAll()
    {
        ArrayList<Medium> listeMedia = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Medium");
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Medium medium = new Medium(resultat.getInt("NumMedium"),
                                            resultat.getString("Titre"),
                                            resultat.getString("InterRealAuteur"),
                                            resultat.getInt("Contenant"),
                                            resultat.getDate("DateParution"),
                                            resultat.getDate("DateStockage"),
                                            resultat.getFloat("Prix"),
                                            resultat.getInt("DureeLocation"),
                                            resultat.getString("Type"),
                                            new DaoArmoire().find(resultat.getInt("NumArmoire")));

                listeMedia.add(medium);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeMedia;
    }

    public List<Medium> findByType(String type)
    {
        ArrayList<Medium> listeMedia = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Medium WHERE Type = ?");
            sql.setString(1, type);
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Medium medium = new Medium(resultat.getInt("NumMedium"),
                        resultat.getString("Titre"),
                        resultat.getString("InterRealAuteur"),
                        resultat.getInt("Contenant"),
                        resultat.getDate("DateParution"),
                        resultat.getDate("DateStockage"),
                        resultat.getFloat("Prix"),
                        resultat.getInt("DureeLocation"),
                        resultat.getString("Type"),
                        new DaoArmoire().find(resultat.getInt("NumArmoire")));

                listeMedia.add(medium);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeMedia;
    }

    @Override
    public Medium create(Medium medium)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO Armoire (Titre, InterRealAuteur, Contenant, DateParution, DateStockage, Prix, DureeLocation, Type, NumArmoire) " +
                                                                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setString(i++, medium.Titre);
            sql.setString(i++, medium.InterRealAuteur);
            sql.setString(i++, medium.Contenant);
            sql.setString(i++, medium.DateParution);
            sql.setString(i++, medium.DateStockage);
            sql.setString(i++, medium.Prix);
            sql.setString(i++, medium.DureeLocation);
            sql.setString(i++, medium.Type);
            sql.setString(i++, medium.armoire.NumArmoire);
            sql.executeUpdate();

            ResultSet keys = sql.getGeneratedKeys();
            keys.next();
            medium.NumMedium = keys.getInt(1);
            medium = find(medium.NumMedium);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return medium;
    }

    @Override
    public boolean update(Medium medium)
    {
        try
        {
            PreparedStatement sql = this.connexion.prepareStatement("UPDATE Medium " +
                                                                        "SET Titre = ?,  " +
                                                                            "InterRealAuteur = ?, " +
                                                                            "Contenant = ?, " +
                                                                            "DateParution = ?, " +
                                                                            "DateStockage = ?, " +
                                                                            "Prix = ?, " +
                                                                            "DureeLocation = ?, " +
                                                                            "Type = ? " +
                                                                        "WHERE NumMedium = ?");

            int i = 1;
            sql.setString(i++, medium.Titre);
            sql.setString(i++, medium.InterRealAuteur);
            sql.setInt(i++, medium.Contenant);
            sql.setDate(i++, medium.DateParution);
            sql.setDate(i++, medium.DateStockage);
            sql.setFloat(i++, medium.Prix);
            sql.setInt(i++, medium.DureeLocation);
            sql.setString(i++, medium.Type);
            sql.setInt(i++, medium.NumMedium);

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
    public boolean delete(Medium medium)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("DELETE FROM armoire WHERE NumArmoire = ?");
            sql.setInt(1, armoire.NumArmoire);
            sql.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
