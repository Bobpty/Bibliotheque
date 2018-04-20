package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;

public class Rechercher {

	private JFrame frmRechercherUnMedium;

	/**
	 * Create the application.
	 */
	public Rechercher() {
		initialize();
		frmRechercherUnMedium.setVisible(true);
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
		
		JList listMedia = new JList();
		listMedia.setModel(new AbstractListModel() {
			String[] values = new String[] {"Choisir un medium"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lblTitreMedia.setLabelFor(listMedia);
		listMedia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(listMedia);
		
		JPanel panel_1 = new JPanel();
		frmRechercherUnMedium.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Rechercher");
		panel_1.add(btnNewButton);
	}

}
