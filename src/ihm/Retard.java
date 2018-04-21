package ihm;

import java.awt.BorderLayout;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.DaoMedium;
import dao.DaoPersonne;
import entity.Louer;
import entity.Medium;
import entity.Personne;

public class Retard
{
	Date laDateRendu;
	Date laDateEmprunt;
	final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
	long ecartTemps;
	String ecartDate;
	
	private Louer location;
	private Medium medium;
	private Personne personne;
	ArrayList<Louer> listeLocation;
	
	
	DaoMedium daoMedium = new DaoMedium();
	DaoPersonne daoPersonne = new DaoPersonne();
	
	private JFrame frameRetard;
	private JTable tableRetard;
	
	/**
	 * Create the application.
	 */
	public Retard()
	{
		initialize();
		frameRetard.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frameRetard = new JFrame();
		frameRetard.setTitle("Gestion des retards");
		frameRetard.setBounds(100, 100, 450, 300);
		frameRetard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frameRetard.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		listeLocation = (ArrayList<Louer>) daoMedium.findAllLocationsEnRetard();
		String  donnees[][] = new String[listeLocation.size()][5];
		String entete[] = {"Titre de l'oeuvre", "Date d'emprunt", "Date de rendu", "Nombre de jours de retard", "Nom de l'emprunteur"};
		String[] ligne = new String[listeLocation.size()];
		
		for(int i = 0; i < listeLocation.size(); i++)
		{
			location = listeLocation.get(i);
			medium = daoMedium.find(location.getMedium().getNumMedia());
			personne = daoPersonne.find(location.getPersonne().getIDpersonne());
			
			String dateRendu = location.getDateRestitution();
			String dateEmprunt = location.getDateLocation();
			SimpleDateFormat sdfDateRendu = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfDateEmprunt = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				laDateRendu = sdfDateRendu.parse(dateRendu);
				laDateEmprunt = sdfDateEmprunt.parse(dateEmprunt);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}

			ecartTemps = laDateRendu.getTime() - laDateEmprunt.getTime();
			ecartDate = new Long(ecartTemps / MILLISECONDS_PER_DAY).toString();
			
			ligne = new String[]{medium.getTitre(), location.getDateLocation(), location.getDateRestitution(), ecartDate, personne.getNom() + " " + personne.getPrenom()};
			donnees[i] = ligne;
		}
		
		tableRetard = new JTable(donnees, entete);
		scrollPane.setViewportView(tableRetard);
	}
}
