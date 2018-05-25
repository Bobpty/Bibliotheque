package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DaoArmoire;
import dao.DaoBibliotheque;
import entity.Armoire;
import entity.Bibliotheque;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

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
	ArrayList<Armoire> listArmoire = new ArrayList<>();
	ArrayList<Bibliotheque> listBiblio = new ArrayList<>();
	DaoBibliotheque daoBibliotheque = new DaoBibliotheque();
	DaoArmoire daoArmoire = new DaoArmoire();
	Bibliotheque bibliotheque;
	Armoire armoire;

	/**
	 * Create the application.
	 */
	public InfoBiblio() {
		initialize();
		frmInformationsSurLaBibliotheque.setVisible(true);
		bibliotheque = null;
	}
	
	public InfoBiblio(Bibliotheque laBibliotheque)
	{
		initialize();
		frmInformationsSurLaBibliotheque.setVisible(true);
		bibliotheque = laBibliotheque;
		frmInformationsSurLaBibliotheque.addWindowListener(new WindowAdapter() {
			 public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        new Principale(bibliotheque);
			    }
		});
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
				
				final boolean testSaisiChamp = txtNom.getText().isEmpty() || txtAdresse.getText().isEmpty() || txtNombreDarmoires.getText().isEmpty() || txtNombreDeRangees.getText().isEmpty();
			
				if(testSaisiChamp)
				{
					JOptionPane.showMessageDialog(null, "Il faut saisir toutes les données", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);

				}
				else {
					String nom = ((JTextField) txtNom).getText();
					String adresse = ((JTextField) txtAdresse).getText();

					if(nom.contains("'"))
						nom = nom.replaceAll("'", "''");
					if(adresse.contains("'"))
						adresse.replaceAll("'", "''");
					
					int nbArmoires = 0;
					int nbRangees = 0;
					try {
						nbArmoires = Integer.parseInt(((JTextField) txtNombreDarmoires).getText());
						nbRangees = Integer.parseInt(((JTextField) txtNombreDeRangees).getText());
					}
					catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Seuls les chiffres sont autorisés", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
					}
					
					if(nbRangees >= nbArmoires)
						JOptionPane.showMessageDialog(null, "Impossible d'avoir plus de rangées que d'armoires", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
					else if(nbArmoires >= (6*nbRangees+1))
						JOptionPane.showMessageDialog(null, "Impossible d'avoir plus de 6 armoires par côté de rangée", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
					
					listBiblio = daoBibliotheque.findAll();
					int numBiblio = listBiblio.size() + 1;
					
					listArmoire = daoArmoire.findAll();
					
					int numeroArmoire = listArmoire.size() + 1;
					String lenumArmoire = String.valueOf(numeroArmoire);
					
					
					
					if(bibliotheque == null)
					{
						bibliotheque = new Bibliotheque(numBiblio, nom, adresse, nbRangees);
						armoire = new Armoire(numeroArmoire, "armoire" + lenumArmoire, bibliotheque);
						try {
							daoBibliotheque.create(bibliotheque);
							for(int i = 0; i <= nbArmoires; i++)
							{
								daoArmoire.create(armoire);
							}
							JOptionPane.showMessageDialog(null, "La bibliothèque a bien été enregistrée", "Bibliothèque enregistrée !", JOptionPane.INFORMATION_MESSAGE);
							new Principale(bibliotheque);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "La bibliothèque n'a pas été enregistrée, veuillez réessayer", "Erreur d'enregistrement", JOptionPane.ERROR_MESSAGE);
							new Principale(bibliotheque);
							frmInformationsSurLaBibliotheque.dispose();
						}
					}
					else
					{
						bibliotheque.setNomBibliotheque(nom);
						bibliotheque.setAdresse(adresse);
						bibliotheque.setNombreRangees(nbRangees);
						
						for(int i = 0; i <= nbArmoires; i++)
						{
							daoArmoire.create(armoire);
						}
						
						boolean rep = daoBibliotheque.update(bibliotheque);
						if(rep)
							JOptionPane.showMessageDialog(null, "La bibliothèque a bien été modifiée", "Bibliothèque modifiée !", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "La bibliothèque n'a pas été modifiée, veuillez réessayer", "Erreur de modification", JOptionPane.ERROR_MESSAGE);
						new Principale(bibliotheque);
						frmInformationsSurLaBibliotheque.dispose();
					}
				}
			}
		});
		panel.add(btnEnregistrer);
	}

}
