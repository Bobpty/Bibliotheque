package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.DaoMedium;
import dao.DaoPersonne;
import entity.Louer;
import entity.Medium;
import entity.Personne;

public class Location {

	private JFrame frmLocation;
	private JTextField txtNomDuLoueur;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtDateDeLocation;
	private JTextField txtDateDeRestitution;
	private Medium leMedium = null;
	private Personne leLoueur = null;
	ArrayList<Medium> listMedia = new ArrayList<Medium>();
	DaoMedium daoMedium = new DaoMedium();
	DaoPersonne daoPersonne = new DaoPersonne();
	JComboBox<String> listTitre = new JComboBox<>();
	/**
	 * Create the application.
	 */
	public Location() {
		initialize();
		frmLocation.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLocation = new JFrame();
		frmLocation.setTitle("Location");
		frmLocation.setBounds(100, 100, 695, 495);
		frmLocation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmLocation.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitre = new JLabel("Titre:");
		lblTitre.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblTitre);
		
		listMedia = daoMedium.findAllMediaEmpruntes();
		listTitre.removeAll();
		Vector<String> vector = new Vector<>();
		for(int i = 0; i < listMedia.size(); i++ )
		{
			vector.add(listMedia.get(i).getTitre());
		}
		
		listTitre = new JComboBox<>(vector);
		lblTitre.setLabelFor(listTitre);
		panel.add(listTitre);
		
		JLabel lblNomDuLoueur = new JLabel("Nom du loueur");
		lblNomDuLoueur.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNomDuLoueur);
		
		txtNomDuLoueur = new JTextField();
		lblNomDuLoueur.setLabelFor(txtNomDuLoueur);
		panel.add(txtNomDuLoueur);
		txtNomDuLoueur.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblAdresse);
		
		txtAdresse = new JTextField();
		lblAdresse.setLabelFor(txtAdresse);
		panel.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		lblEmail.setLabelFor(txtEmail);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblDateDeLocation = new JLabel("Date de location:");
		lblDateDeLocation.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblDateDeLocation);
		
		txtDateDeLocation = new JTextField();
		lblDateDeLocation.setLabelFor(txtDateDeLocation);
		panel.add(txtDateDeLocation);
		txtDateDeLocation.setColumns(10);
		
		JLabel lblDateDeRestitution = new JLabel("Date de restitution:");
		lblDateDeRestitution.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblDateDeRestitution);
		
		txtDateDeRestitution = new JTextField();
		lblDateDeRestitution.setLabelFor(txtDateDeRestitution);
		panel.add(txtDateDeRestitution);
		txtDateDeRestitution.setColumns(10);
		
		JLabel lblCommentaire = new JLabel("Commentaire:");
		lblCommentaire.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCommentaire);
		
		JTextArea txtrCommentaire = new JTextArea();
		txtrCommentaire.setRows(10);
		txtrCommentaire.setColumns(20);
		lblCommentaire.setLabelFor(txtrCommentaire);
		panel.add(txtrCommentaire);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setHorizontalAlignment(SwingConstants.LEFT);
		btnEnregistrer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtDateDeLocation.getText().isEmpty() || txtEmail.getText().isEmpty() || txtNomDuLoueur.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Il faut saisir toutes les données", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String medium = lblTitre.getText();
					String commentaire = txtrCommentaire.getText();
					String adresse = txtAdresse.getText();
					String dateLocation = txtDateDeLocation.getText();
					String dateRestitution = txtDateDeRestitution.getText();
					String email = txtEmail.getText();
					String loueur = txtNomDuLoueur.getText();
					
					//leMedium = DaoMedium.findByNom(medium);
					//leLoueur = DaoPersonne.findByNom(loueur);
					
					if (dateRestitution == "" && commentaire == "")
					{
						daoMedium.emprunter(leLoueur, leMedium, dateLocation);
					}
					else
					{
						daoMedium.restituer(leLoueur, leMedium, dateRestitution, commentaire);
					}
				}
			}
		});
		panel.add(btnEnregistrer);
	}

}
