package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * <p>Cette classe permet de d'obtenir les informations sur la bibliothèque et en insérer une nouvelle.
 *
 */
public class InfoBiblio {

	private JFrame frmInformationsSurLaBibliotheque;
	private JTextField txtNom;
	private JTextField txtAdresse;
	private JTextField txtNombreDarmoires;
	private JTextField txtNombreDeRangees;

	/**
	 * Create the application.
	 */
	public InfoBiblio() {
		initialize();
		frmInformationsSurLaBibliotheque.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInformationsSurLaBibliotheque = new JFrame();
		frmInformationsSurLaBibliotheque.setTitle("Informations sur la biblioth\u00E8que");
		frmInformationsSurLaBibliotheque.setBounds(100, 100, 637, 498);
		frmInformationsSurLaBibliotheque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmInformationsSurLaBibliotheque.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNom = new JLabel("Nom:");
		panel.add(lblNom);
		
		txtNom = new JTextField();
		lblNom.setLabelFor(txtNom);
		panel.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		panel.add(lblAdresse);
		
		txtAdresse = new JTextField();
		lblAdresse.setLabelFor(txtAdresse);
		panel.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblNombreDarmoires = new JLabel("Nombre d'armoires:");
		panel.add(lblNombreDarmoires);
		
		txtNombreDarmoires = new JTextField();
		lblNombreDarmoires.setLabelFor(txtNombreDarmoires);
		panel.add(txtNombreDarmoires);
		txtNombreDarmoires.setColumns(10);
		
		JLabel lblNombreDeRangees = new JLabel("Nombre de rang\u00E9es:");
		panel.add(lblNombreDeRangees);
		
		txtNombreDeRangees = new JTextField();
		lblNombreDeRangees.setLabelFor(txtNombreDeRangees);
		panel.add(txtNombreDeRangees);
		txtNombreDeRangees.setColumns(10);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.add(btnEnregistrer);
	}

}
