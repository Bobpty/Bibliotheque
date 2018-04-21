package dao;

import entity.Armoire;
import entity.Bibliotheque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoArmoire extends Dao<Armoire>
{
	/**
	 * @param le numéro de l'armoire recherchée
	 * @return une armoire selon son numéro en paramètre
	 */
    @Override
    public Armoire find(int id)
    {
        Armoire armoire = null;

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Armoire WHERE NumArmoire = ?");
            sql.setInt(1, id);
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            if(resultat.first())
            {
                armoire = new Armoire(resultat.getInt("NumArmoire"),
                                        resultat.getString("NomArmoire"),
                                        new DaoBibliotheque().find(resultat.getInt("NumBibliotheque")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return armoire;
    }
    
    /**
     * méthode qui recherchent les armoires dans une bibliothèque
     * @param bibliotheque qui contient les armoires
     * @return une ArrayList des armoires dans la bibliothèque
     */
    public ArrayList<Armoire> findArmoiresParBibliotheque(Bibliotheque bibliotheque)
    {
    	ArrayList<Armoire> listeNumArmoire = new ArrayList<>();
    	
    	try
    	{
    		PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Armoire WHERE NumBibliotheque = ?");
    		sql.setInt(1, bibliotheque.getNumBibliotheque());
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Armoire armoire = new Armoire(resultat.getInt("NumArmoire"),
                                              resultat.getString("NomArmoire"),
                                              new DaoBibliotheque().find(resultat.getInt("NumBibliotheque")));

                listeNumArmoire.add(armoire);
            }
    	}
    	catch (SQLException e)
    	{
    		e.printStackTrace();
            return null;
    	}
    	
    	return listeNumArmoire;
    }

    /**
     * @return une ArrayList de toutes les armoires enregistrées
     */
    @Override
    public ArrayList<Armoire> findAll()
    {
        ArrayList<Armoire> listeArmoires = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Armoire");
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Armoire armoire = new Armoire(resultat.getInt("NumArmoire"),
                                                    resultat.getString("NomArmoire"),
                                                    new DaoBibliotheque().find(resultat.getInt("NumBibliotheque")));

                listeArmoires.add(armoire);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeArmoires;
    }
    
    /**
     * @param la bibliothèque qui contient les armoires
     * @return le nombre d'armoire pour une bibliothèque
     */
    public int countNombreArmoires(Bibliotheque bibliotheque)
    {
    	int nombreArmoires = 0;
    	
        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT COUNT(*) AS nombre FROM Armoire WHERE NumBibliotheque = ?");
            sql.setInt(1, bibliotheque.getNumBibliotheque());
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            if(resultat.first())
            {
                nombreArmoires = resultat.getInt("nombre");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return nombreArmoires;
    }

    /**
     * création d'une armoire
     */
    @Override
    public Armoire create(Armoire armoire)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO Armoire (NomArmoire, NumBibliotheque) " +
                                                                    "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setString(i++, armoire.getNomArmoire());
            sql.setString(i++, armoire.getBibliotheque().getNomBibliotheque());
            sql.executeUpdate();

            ResultSet keys = sql.getGeneratedKeys();
            keys.next();
            //armoire.NumArmoire = keys.getInt(1);
            armoire = find(armoire.getNumArmoire());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return armoire;
    }

    /**
     * mise à jour d'une armoire
     */
    @Override
    public boolean update(Armoire armoire)
    {
        try
        {
            PreparedStatement sql = this.connexion.prepareStatement("UPDATE Armoire " +
                                                                        "SET NomArmoire = ? " +
                                                                        "WHERE NumArmoire = ?");

            int i = 1;
            sql.setString(i++, armoire.getNomArmoire());
            sql.setInt(i++, armoire.getNumArmoire());

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
     * suppression d'une armoire
     */
    @Override
    public boolean delete(Armoire armoire)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("DELETE FROM armoire WHERE NumArmoire = ?");
            sql.setInt(1, armoire.getNumArmoire());
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
