package ihm;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.DaoBibliotheque;
import entity.Bibliotheque;
/**
 * 
 * <p>Cette classe permet de charger une bibliothèque
 *
 */
public class ChargerBiblio {

	private JFrame frmChargerUneBibliotheque;
	ArrayList<Bibliotheque> listBiblio = new ArrayList<Bibliotheque>();
	Bibliotheque biblio;
	DaoBibliotheque daoBibliotheque = new DaoBibliotheque();

	/**
	 * Create the application.
	 */
	public ChargerBiblio() {
		initialize();
		frmChargerUneBibliotheque.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChargerUneBibliotheque = new JFrame();
		frmChargerUneBibliotheque.setTitle("Charger une biblioth\u00E8que");
		frmChargerUneBibliotheque.setBounds(100, 100, 450, 300);
		frmChargerUneBibliotheque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmChargerUneBibliotheque.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNom);
		
		listBiblio = daoBibliotheque.findAll();

		Vector<String> vector = new Vector<>();
		
		for(int i = 0; i < listBiblio.size(); i++)
		{
			vector.add(listBiblio.get(i).getNomBibliotheque());
		}
		
		JComboBox<String> listBibliotheque = new JComboBox<>(vector);
		lblNom.setLabelFor(listBibliotheque);
		panel.add(listBibliotheque);
		
		JButton btnCharger = new JButton("Charger");
		btnCharger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Bibliotheque bibliotheque = listBiblio.get(listBibliotheque.getSelectedIndex());
				frmChargerUneBibliotheque.dispose();
				new Principale(bibliotheque);
			}
		});
		panel.add(btnCharger);
	}

}
