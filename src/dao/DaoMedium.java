package dao;

import entity.Louer;
import entity.Medium;
import entity.Personne;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoMedium extends Dao<Medium>
{
	DaoPersonne daoPersonne;
	
	/**
	 * @param le numéro du medium recherché
	 * @return un medium selon son numéro en paramètre
	 */
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

    /**
     * @return une ArrayList de tous les media enregistrés
     */
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

    /**
     * @return ArrayList de tous les média empruntés
     */
    public ArrayList<Louer> findAllMediaEmpruntes()
    {
        ArrayList<Louer> listeMediaLoues = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Louer");
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Louer location = new Louer(resultat.getDate("DateLocation"),
                                            resultat.getDate("DateRestitution"),
                                            resultat.getString("Commentaire"),
                                            new DaoPersonne().find(resultat.getInt("IDpersonne")),
                                            new DaoMedium().find(resultat.getInt("NumMedium")));

                listeMediaLoues.add(location);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeMediaLoues;
    }
    
    /**
     * recherche tous les media qui n'ont pas été empruntés
     * @return ArrayList de Media
     */
    public ArrayList<Medium> findAllMediaDisponibles()
    {
    	ArrayList<Medium> listeMediaLoues = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT M.NumMedium, Titre, InterRealAuteur, Contenant, DateParution, DateStockage, Prix, DureeLocation, Type, NumArmoire "
											            		+ "FROM medium M"
											            		+ "WHERE M.NumMedium NOT IN (SELECT L.NumMedium"
											            									+ "FROM louer L"
											            									+ "WHERE DateRestitution = null"
											            									+ "OR DateRestitution = '0000-00-00')");
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

            	listeMediaLoues.add(medium);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeMediaLoues;
    }
    
    /**
     * recherche tous les media empruntés qui ne sont pas restitués à temps
     * calcule si la date du jour est supérieure à la date de location + sa durée
     * @return ArrayList de Media
     */
    public ArrayList<Medium> findAllMediaEnRetard()
    {
    	ArrayList<Medium> listeMediaLoues = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT M.NumMedium, Titre, InterRealAuteur, Contenant, DateParution, DateStockage, Prix, DureeLocation, Type, NumArmoire FROM medium M, louer L"
											            		+ "WHERE M.NumMedium = L.NumMedium"
											            		+ "AND NOW() > DATE_ADD(L.DateLocation, INTERVAL M.DureeLocation DAY); ");
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

            	listeMediaLoues.add(medium);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeMediaLoues;
    }
    
    /**
     * recherche tous les media par type
     * @param type de medium (CD, DVD ou Livre)
     * @return ArrayListe de tous les média du type
     */
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

    /**
     * création d'un medium
     */
    @Override
    public Medium create(Medium medium)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO Armoire (Titre, InterRealAuteur, Contenant, DateParution, DateStockage, Prix, DureeLocation, Type, NumArmoire) " +
                                                                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 1; //Permet d'itÃ©rer plus facilement sur chacun des paramÃ¨tres
            sql.setString(i++, medium.getTitre());
            sql.setString(i++, medium.getInterRealAuteur());
            sql.setInt(i++, medium.getContenant());
            sql.setDate(i++, medium.getDateParution());
            sql.setDate(i++, medium.getDateStockage());
            sql.setFloat(i++, medium.getPrix());
            sql.setInt(i++, medium.getDureeLocation());
            sql.setString(i++, medium.getType());
            sql.setInt(i++, medium.getArmoire().getNumArmoire());
            sql.executeUpdate();

            ResultSet keys = sql.getGeneratedKeys();
            keys.next();
            //medium.NumMedium = keys.getInt(1);
            medium = find(medium.getNumMedia());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return medium;
    }

    /**
     * mise à jour d'un medium
     */
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
            sql.setString(i++, medium.getTitre());
            sql.setString(i++, medium.getInterRealAuteur());
            sql.setInt(i++, medium.getContenant());
            sql.setDate(i++, medium.getDateParution());
            sql.setDate(i++, medium.getDateStockage());
            sql.setFloat(i++, medium.getPrix());
            sql.setInt(i++, medium.getDureeLocation());
            sql.setString(i++, medium.getType());
            sql.setInt(i++, medium.getNumMedia());

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
     * suppression d'un medium
     */
    @Override
    public boolean delete(Medium medium)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("DELETE FROM media WHERE NumMedium = ?");
            sql.setInt(1, medium.getNumMedia());
            sql.executeUpdate();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * méthode créant un lien "emprunter" entre un utilisateur et un medium
     * si l'utilisateur n'existe pas alors on le crée
     * @param utilisateur souhaitant emprunter le medium en paramètre
     * @param medium sélectionné par l'emprunteur passé en paramètre
     * @return true si l'emprunt a été correctement effectué sinon échec
     */
    public boolean emprunter(Personne personne, Medium medium, Date date)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO louer (DateLocation, IDpersonne, NumMedium) VALUES (?, ?, ?)");

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setDate(i++, date);
            sql.setInt(i++, personne.getIDpersonne());
            sql.setInt(i++, medium.getNumMedia());
            sql.executeUpdate();
            
            if (daoPersonne.find(personne.getIDpersonne()) == null)
        	{
            	daoPersonne.create(personne);
        	}
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    } 
    
    /**
     * méthode créant un lien "restituer" medium emprunté par un utilisateur
     * @param utilisateur souhaitant restituer le medium en paramètre
     * @param medium sélectionné par l'emprunteur passé en paramètre
     * @return true si la restitution a été correctement effectuée sinon échec
     */
    public boolean restituer(Personne personne, Medium medium, Date date, String commentaire)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("UPDATE louer "
										            		+ "SET DateRestitution = ?, Commentaire = ? "
										            		+ "WHERE IDpersonne = ? "
										            		+ "AND NumMedium = ?");

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setDate(i++, date);
            sql.setString(i++, commentaire);
            sql.setInt(i++, personne.getIDpersonne());
            sql.setInt(i++, medium.getNumMedia());
            sql.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    } 
}
