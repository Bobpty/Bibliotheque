package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChargerBiblio {

	private JFrame frmChargerUneBibliotheque;

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
		
		JList listBibliotheque = new JList();
		listBibliotheque.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lblNom.setLabelFor(listBibliotheque);
		panel.add(listBibliotheque);
		
		JButton btnCharger = new JButton("Charger");
		btnCharger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.add(btnCharger);
	}

}
