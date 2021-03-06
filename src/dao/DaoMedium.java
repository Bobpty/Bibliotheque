package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Bibliotheque;
import entity.Louer;
import entity.Medium;
import entity.Personne;


public class DaoMedium extends Dao<Medium>
{
	DaoPersonne daoPersonne;
	
	/**
	 * @param le num�ro du medium recherch�
	 * @return un medium selon son num�ro en param�tre
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
                                    resultat.getString("DateParution"),
                                    resultat.getString("DateStockage"),
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
     * recherche le medium correspondant au nom
     * @param le nom du medium cible
     * @return Le medium recherch�
     */
    public Medium findByNom(String nomMedium)
    {
    	Medium medium = null;

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * FROM Medium WHERE Titre = ?");
            sql.setString(1, nomMedium);
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            if (resultat.first())
            {
                medium = new Medium(resultat.getInt("NumMedium"),
                        resultat.getString("Titre"),
                        resultat.getString("InterRealAuteur"),
                        resultat.getInt("Contenant"),
                        resultat.getString("DateParution"),
                        resultat.getString("DateStockage"),
                        resultat.getFloat("Prix"),
                        resultat.getInt("DureeLocation"),
                        resultat.getString("Type"),
                        new DaoArmoire().find(resultat.getInt("NumArmoire")));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return medium;
    }

    /**
     * @return une ArrayList de tous les media enregistr�s
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
                                            resultat.getString("DateParution"),
                                            resultat.getString("DateStockage"),
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
     * @return ArrayList de tous les m�dia emprunt�s
     */
    public ArrayList<Medium> findAllMediaEmpruntes()
    {
        ArrayList<Medium> listeMediaLoues = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT * "
											            		+ "FROM louer L, medium M "
											            		+ "WHERE L.NumMedium = M.NumMedium "
											            		+ "AND (DateRestitution = NULL "
											            		+ "OR DateRestitution = '0000-00-00')");
            
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Medium location = new Medium(resultat.getInt("NumMedium"),
					                        resultat.getString("Titre"),
					                        resultat.getString("InterRealAuteur"),
					                        resultat.getInt("Contenant"),
					                        resultat.getString("DateParution"),
					                        resultat.getString("DateStockage"),
					                        resultat.getFloat("Prix"),
					                        resultat.getInt("DureeLocation"),
					                        resultat.getString("Type"),
					                        new DaoArmoire().find(resultat.getInt("NumArmoire")));

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
     * @return ArrayList de tous les m�dia emprunt�s
     */
    public ArrayList<Louer> findAllLocationEmpruntes()
    {
        ArrayList<Louer> listeMediaLoues = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT DateLocation, DateRestitution, Commentaire, IDpersonne, L.NumMedium "
											            		+ "FROM louer L "
											            		+ "WHERE DateRestitution = NULL "
											            		+ "OR DateRestitution = '0000-00-00'");
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Louer location = new Louer(resultat.getString("DateLocation"),
                                            resultat.getString("DateRestitution"),
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
     * recherche tous les media qui n'ont pas �t� emprunt�s
     * @return ArrayList de Media
     */
    public ArrayList<Medium> findAllMediaDisponibles()
    {
    	ArrayList<Medium> listeMediaDisponibles = new ArrayList<>();

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
					                        resultat.getString("DateParution"),
					                        resultat.getString("DateStockage"),
					                        resultat.getFloat("Prix"),
					                        resultat.getInt("DureeLocation"),
					                        resultat.getString("Type"),
					                        new DaoArmoire().find(resultat.getInt("NumArmoire")));

            	listeMediaDisponibles.add(medium);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return listeMediaDisponibles;
    }
    
    /**
     * recherche tous les media emprunt�s qui ne sont pas restitu�s � temps
     * calcule si la date du jour est sup�rieure � la date de location + sa dur�e
     * @return ArrayList de Media
     */
    /*
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
					                        resultat.getString("DateParution"),
					                        resultat.getString("DateStockage"),
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
    */
    
    /**
     * recherche tous les media emprunt�s qui ne sont pas restitu�s � temps
     * calcule si la date du jour est sup�rieure � la date de location + sa dur�e
     * @return ArrayList de Media
     */
    public ArrayList<Louer> findAllLocationsEnRetard()
    {
    	ArrayList<Louer> listeMediaLoues = new ArrayList<>();
    	
        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT DateLocation DateRestitution, Commentaire, IDpersonne, NumMedium "
            													+ "FROM louer L, medium M"
            													+ "WHERE L.NumMedium = M.NumMedium"
											            		+ "AND NOW() > DATE_ADD(L.DateLocation, INTERVAL M.DureeLocation DAY)");
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
            	Louer location = new Louer(resultat.getString("DateLocation"),
					                        resultat.getString("DateRestitution"),
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
     * recherche tous les media par type
     * @param type de medium (CD, DVD ou Livre)
     * @return ArrayListe de tous les m�dia du type
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
                        resultat.getString("DateParution"),
                        resultat.getString("DateStockage"),
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
     * recherche tous les media par biblioth�que
     * @param la biblioth�que de r�f�rence
     * @return ArrayListe de tous les m�dia de la biblioth�que
     */
    public List<Medium> findByBibliotheque(Bibliotheque bibliotheque)
    {
        ArrayList<Medium> listeMedia = new ArrayList<>();

        try
        {
            PreparedStatement sql = connexion.prepareStatement("SELECT NumMedium, Titre, InterRealAuteur, Contenant, DateParution, DateStockage, Prix, DureeLocation, Type, M.NumArmoire "
            													+ "FROM medium M, armoire A, bibliotheque B "
            													+ "WHERE M.NumArmoire = A.NumArmoire "
            													+ "AND A.NumBibliotheque = B.NumBibliotheque "
            													+ "AND B.NumBibliotheque = ?");
            sql.setInt(1, bibliotheque.getNumBibliotheque());
            sql.execute();
            ResultSet resultat = sql.getResultSet();

            while (resultat.next())
            {
                Medium medium = new Medium(resultat.getInt("NumMedium"),
                        resultat.getString("Titre"),
                        resultat.getString("InterRealAuteur"),
                        resultat.getInt("Contenant"),
                        resultat.getString("DateParution"),
                        resultat.getString("DateStockage"),
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
     * cr�ation d'un medium
     */
    @Override
    public Medium create(Medium medium)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO Armoire (Titre, InterRealAuteur, Contenant, DateParution, DateStockage, Prix, DureeLocation, Type, NumArmoire) " +
                                                                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int i = 1; //Permet d'itérer plus facilement sur chacun des paramètres
            sql.setString(i++, medium.getTitre());
            sql.setString(i++, medium.getInterRealAuteur());
            sql.setInt(i++, medium.getContenant());
            sql.setString(i++, medium.getDateParution());
            sql.setString(i++, medium.getDateStockage());
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
     * mise � jour d'un medium
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
            sql.setString(i++, medium.getDateParution());
            sql.setString(i++, medium.getDateStockage());
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
     * m�thode cr�ant un lien "emprunter" entre un utilisateur et un medium
     * si l'utilisateur n'existe pas alors on le cr�e
     * @param utilisateur souhaitant emprunter le medium en param�tre
     * @param medium s�lectionn� par l'emprunteur pass� en param�tre
     * @return true si l'emprunt a �t� correctement effectu� sinon �chec
     */
    public boolean emprunter(Personne personne, Medium medium, String date)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("INSERT INTO louer (DateLocation, IDpersonne, NumMedium) VALUES (?, ?, ?)");

            int i = 1; //Permet d'it�rer plus facilement sur chacun des param�tres
            sql.setString(i++, date);
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
     * m�thode cr�ant un lien "restituer" medium emprunt� par un utilisateur
     * @param utilisateur souhaitant restituer le medium en param�tre
     * @param medium s�lectionn� par l'emprunteur pass� en param�tre
     * @return true si la restitution a �t� correctement effectu�e sinon �chec
     */
    public boolean restituer(Personne personne, Medium medium, String date, String commentaire)
    {
        try
        {
            PreparedStatement sql = connexion.prepareStatement("UPDATE louer "
										            		+ "SET DateRestitution = ?, Commentaire = ? "
										            		+ "WHERE IDpersonne = ? "
										            		+ "AND NumMedium = ?");

            int i = 1; //Permet d'it�rer plus facilement sur chacun des param�tres
            sql.setString(i++, date);
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
