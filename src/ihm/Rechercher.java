package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import dao.DaoMedium;
import entity.Bibliotheque;
import entity.Medium;

public class Rechercher {

	private JFrame frmRechercherUnMedium;
	private ArrayList<Medium> listMedium;
	private Bibliotheque bibliotheque;
	JComboBox<String> listTitre;
	
	private DaoMedium daoMedium = new DaoMedium();

	/**
	 * Create the application.
	 */
	public Rechercher(Bibliotheque biblio) {
		initialize();
		frmRechercherUnMedium.setVisible(true);
		this.bibliotheque = biblio;
		frmRechercherUnMedium.addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        new Principale(bibliotheque);
		    }
		});
		
		listMedium = (ArrayList<Medium>) daoMedium.findAll();
		
		if(listMedium.isEmpty())
		{
			frmRechercherUnMedium.dispose();
			new Principale(bibliotheque);
			JOptionPane.showMessageDialog(null, "Il n'existe pas de medium", "Erreur !", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRechercherUnMedium = new JFrame();
		frmRechercherUnMedium.setTitle("Rechercher un medium");
		frmRechercherUnMedium.setBounds(100, 100, 450, 300);
		frmRechercherUnMedium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmRechercherUnMedium.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitreMedia = new JLabel("Titre");
		panel.add(lblTitreMedia);
		
		listMedium = (ArrayList<Medium>) daoMedium.findAll();
		listTitre.removeAll();
		Vector<String> vector = new Vector<>();
		for(int i = 0; i < listMedium.size(); i++ )
		{
			vector.add(listMedium.get(i).getTitre());
		}
		
		listTitre = new JComboBox<>(vector);
		lblTitreMedia.setLabelFor(listTitre);
		panel.add(listTitre);
		
		JPanel panel_1 = new JPanel();
		frmRechercherUnMedium.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Rechercher");
		panel_1.add(btnNewButton);
	}

}
