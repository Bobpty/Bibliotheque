package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Location {

	private JFrame frmLoaction;
	private JTextField txtNomDuLoueur;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtDateDeLocation;
	private JTextField txtDateDeRestitution;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location window = new Location();
					window.frmLoaction.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Location() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoaction = new JFrame();
		frmLoaction.setTitle("Location");
		frmLoaction.setBounds(100, 100, 695, 495);
		frmLoaction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmLoaction.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblTitre = new JLabel("Titre:");
		panel.add(lblTitre);
		
		JList listTitre = new JList();
		lblTitre.setLabelFor(listTitre);
		listTitre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(listTitre);
		
		JLabel lblNomDuLoueur = new JLabel("Nom du loueur");
		panel.add(lblNomDuLoueur);
		
		txtNomDuLoueur = new JTextField();
		lblNomDuLoueur.setLabelFor(txtNomDuLoueur);
		panel.add(txtNomDuLoueur);
		txtNomDuLoueur.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		panel.add(lblAdresse);
		
		txtAdresse = new JTextField();
		lblAdresse.setLabelFor(txtAdresse);
		panel.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		lblEmail.setLabelFor(txtEmail);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblDateDeLocation = new JLabel("Date de location:");
		panel.add(lblDateDeLocation);
		
		txtDateDeLocation = new JTextField();
		lblDateDeLocation.setLabelFor(txtDateDeLocation);
		panel.add(txtDateDeLocation);
		txtDateDeLocation.setColumns(10);
		
		JLabel lblDateDeRestitution = new JLabel("Date de restitution:");
		panel.add(lblDateDeRestitution);
		
		txtDateDeRestitution = new JTextField();
		lblDateDeRestitution.setLabelFor(txtDateDeRestitution);
		panel.add(txtDateDeRestitution);
		txtDateDeRestitution.setColumns(10);
		
		JLabel lblCommentaire = new JLabel("Commentaire:");
		panel.add(lblCommentaire);
		
		JTextArea txtrCommentaire = new JTextArea();
		txtrCommentaire.setRows(10);
		txtrCommentaire.setColumns(20);
		lblCommentaire.setLabelFor(txtrCommentaire);
		panel.add(txtrCommentaire);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.add(btnEnregistrer);
	}

}
