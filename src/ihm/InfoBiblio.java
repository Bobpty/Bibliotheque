package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Bibliotheque;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

/**
 * 
 * <p>Cette classe permet de d'obtenir les informations sur la biblioth�que et en ins�rer une nouvelle.
 *
 */
public class InfoBiblio {

	private JFrame frmInformationsSurLaBibliotheque;
	private JTextField txtNom;
	private JTextField txtAdresse;
	private JTextField txtNombreDarmoires;
	private JTextField txtNombreDeRangees;
	Bibliotheque bibliotheque;


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
		addWindowListener
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
					JOptionPane.showMessageDialog(null, "Il faut saisir toutes les donn�es", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);

				}
				else {
					String nom = ((JTextField) txtNom).getText();
					String adresse = ((JTextField) txtAdresse).getText();

					if(nom.contains("'"))
						nom = nom.replaceAll("'", "''");
					if(adresse.contains("'"))
						adresse.replaceAll("'", "''");
					
					int nbArmoires;
					int nbRangees;
					try {
						nbArmoires = Integer.parseInt(((JTextField) txtNombreDarmoires).getText());
						nbRangees = Integer.parseInt(((JTextField) txtNombreDeRangees).getText());
					}
					catch (Exception exception) {
						JOptionPane.showMessageDialog(null, "Seuls les chiffres sont autoris�s", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
						break;
					}
					
					if(nbRangees >= nbArmoires)
						JOptionPane.showMessageDialog(null, "Impossible d'avoir plus de rang�es que d'armoires", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
					else if(nbArmoires >= (6*nbRangees+1))
						JOptionPane.showMessageDialog(null, "Impossible d'avoir plus de 6 armoires par c�t� de rang�e", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);

					
					
					
					if(bibliotheque == null)
					{
						bibliotheque = new Bibliotheque(nom, adresse, nbArmoires, nbRangees);
						int rep = DB.insererBibliotheque(bibliotheque);
						
						if(rep != -1)
						{
							JOptionPane.showMessageDialog(null, "La biblioth�que a bien �t� enregistr�e", "Biblioth�que enregistr�e !", JOptionPane.INFORMATION_MESSAGE);
							bibliotheque.setId(rep);
							new Principale(bibliotheque);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "La biblioth�que n'a pas �t� enregistr�e, veuillez r�essayer", "Erreur d'enregistrement", JOptionPane.ERROR_MESSAGE);
							new Principale(bibliotheque);
							dispose();
						}

					}
					else
					{
						bibliotheque.setNom(nom);
						bibliotheque.setAdresse(adresse);
						bibliotheque.setNbArmoires(nbArmoires);
						bibliotheque.setNbRangees(nbRangees);
						
						boolean rep = DB.modifierBibliotheque(bibliotheque);
						if(rep)
							JOptionPane.showMessageDialog(null, "La biblioth�que a bien �t� modifi�e", "Biblioth�que modifi�e !", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "La biblioth�que n'a pas �t� modifi�e, veuillez r�essayer", "Erreur de modification", JOptionPane.ERROR_MESSAGE);
						new Principale(bibliotheque);
						dispose();
					}
				}
			}
		});
		panel.add(btnEnregistrer);
	}

}
