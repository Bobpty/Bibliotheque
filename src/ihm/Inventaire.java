package ihm;

import java.awt.EventQueue;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;

import com.mysql.jdbc.PreparedStatement;

import dao.DaoMedium;
import entity.Medium;
import util.DBUtil;

import javax.swing.JScrollPane;

public class Inventaire {

	private JFrame frameInventaire;
	private JTable tableInventaire;
	ArrayList<Medium> listMedia = new ArrayList<Medium>();
	DaoMedium daoMedium = new DaoMedium();
	private Medium medium;
	
	/**
	 * Create the application.
	 */
	public Inventaire() {
		initialize();
		frameInventaire.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameInventaire = new JFrame();
		frameInventaire.setTitle("Inventaire");
		frameInventaire.setBounds(100, 100, 450, 300);
		frameInventaire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frameInventaire.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		listMedia = (ArrayList<Medium>) daoMedium.findAll();
		String  donnees[][] = new String[listMedia.size()][6];
		String entete[] = {"Catégorie", "Titre de l'oeuvre", "Interprète/Auteur/Realisateur", "Date de parution", "Date de stockage", "Prix"};
		String[] ligne = new String[listMedia.size()];


		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		for(int i = 0; i < listMedia.size(); i++ )
		{
			medium = listMedia.get(i);
			ligne = new String[]{medium.getType(), medium.getTitre(), medium.getInterRealAuteur(), formatter.format(medium.getDateParution()), formatter.format(medium.getDateStockage()), String.valueOf(medium.getPrix())};
			donnees[i] = ligne;
		}
		
		tableInventaire = new JTable(donnees, entete);
		scrollPane.setViewportView(tableInventaire);
	}

}
