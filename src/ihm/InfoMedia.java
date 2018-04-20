package ihm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.Dao;
import util.DBUtil;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InfoMedia {

	private JFrame frmInformationSurLeMedium;
	private JTextField textFieldTitre;
	private JTextField txtRealAutComp;
	private JTextField txtDuree;
	private JTextField txtDateParution;
	private JTextField txtPrixHt;
	private JTextField txtDureeDeLocation;
	private JTable tableLocation;

	/**
	 * Create the application.
	 */
	public InfoMedia() {
		initialize();
		frmInformationSurLeMedium.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInformationSurLeMedium = new JFrame();
		frmInformationSurLeMedium.setTitle("Information sur le medium");
		frmInformationSurLeMedium.setBounds(100, 100, 1055, 744);
		frmInformationSurLeMedium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInformationSurLeMedium.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPaneHaut = new JScrollPane();
		frmInformationSurLeMedium.getContentPane().add(scrollPaneHaut);
		
		JPanel panel = new JPanel();
		scrollPaneHaut.setViewportView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelTitre = new JPanel();
		panel.add(panelTitre);
		panelTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitre = new JLabel("Titre:");
		panelTitre.add(lblTitre);
		lblTitre.setLabelFor(textFieldTitre);
		
		textFieldTitre = new JTextField();
		panelTitre.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		JPanel panelRealAutComp = new JPanel();
		panel.add(panelRealAutComp);
		panelRealAutComp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblRealAutComp = new JLabel("R\u00E9alisateur:");
		panelRealAutComp.add(lblRealAutComp);
		lblRealAutComp.setLabelFor(txtRealAutComp);
		
		txtRealAutComp = new JTextField();
		panelRealAutComp.add(txtRealAutComp);
		txtRealAutComp.setColumns(10);
		
		JPanel panelDuree = new JPanel();
		panel.add(panelDuree);
		panelDuree.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDuree = new JLabel("Dur\u00E9e:");
		panelDuree.add(lblDuree);
		lblDuree.setLabelFor(txtDuree);
		
		txtDuree = new JTextField();
		panelDuree.add(txtDuree);
		txtDuree.setColumns(10);
		
		JPanel panelDateParution = new JPanel();
		panel.add(panelDateParution);
		
		JLabel lblDateParution = new JLabel("Date de parution:");
		panelDateParution.add(lblDateParution);
		lblDateParution.setLabelFor(txtDateParution);
		
		txtDateParution = new JTextField();
		panelDateParution.add(txtDateParution);
		txtDateParution.setColumns(10);
		
		JPanel panelPrixHT = new JPanel();
		panel.add(panelPrixHT);
		
		JLabel lblPrixHt = new JLabel("Prix HT:");
		panelPrixHT.add(lblPrixHt);
		lblPrixHt.setLabelFor(txtPrixHt);
		
		txtPrixHt = new JTextField();
		panelPrixHT.add(txtPrixHt);
		txtPrixHt.setColumns(10);
		
		JPanel panelDureeLocation = new JPanel();
		panel.add(panelDureeLocation);
		
		JLabel lblDureeDeLocation = new JLabel("Dur\u00E9e de location:");
		panelDureeLocation.add(lblDureeDeLocation);
		
		txtDureeDeLocation = new JTextField();
		lblDureeDeLocation.setLabelFor(txtDureeDeLocation);
		panelDureeLocation.add(txtDureeDeLocation);
		txtDureeDeLocation.setColumns(10);
		
		JLabel lblJours = new JLabel("Jours");
		lblJours.setLabelFor(txtDureeDeLocation);
		panelDureeLocation.add(lblJours);
		
		JPanel panelStockerDansArmoire = new JPanel();
		panel.add(panelStockerDansArmoire);
		
		JLabel lblStockerDansArmoire = new JLabel("Stocker dans armoire:");
		panelStockerDansArmoire.add(lblStockerDansArmoire);
		
		
		JComboBox<String> list = new JComboBox<>();
		panelStockerDansArmoire.add(list);
		
		JScrollPane scrollPaneTableau = new JScrollPane();
		frmInformationSurLeMedium.getContentPane().add(scrollPaneTableau);
		
		JPanel panelTableau = new JPanel();
		scrollPaneTableau.setViewportView(panelTableau);
		panelTableau.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblLocations = new JLabel("Locations:");
		panelTableau.add(lblLocations);
		
		
		String  donnees[][] = new String[45][5];
		String ligne[] = {"John", "15 avenue de ville Paris", "01/01/2001", "01/02/2001", "blahblah"};
		for(int i = 0 ; i<45; i++)
		{
			donnees[i] = ligne;
		}
		
		String entete[] = {"Nom", "Adresse", "Date Location", "Date restitution", "Commentaire"};
		
		JScrollPane scrollPane = new JScrollPane();
		panelTableau.add(scrollPane);
		
		tableLocation = new JTable(donnees, entete);
		scrollPane.setViewportView(tableLocation);
//		tableLocation.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"John", "15 avenue de ville Paris", "01/01/2001", "01/02/2001", "blahblah"},
//				{"John", "15 avenue de ville Paris", "01/01/2001", "01/02/2001", "blahblah"},
//			},
//			new String[] {
//				"Nom", "Adresse", "Date Location", "Date restitution", "Commentaire"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				String.class, String.class, String.class, String.class, String.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//			boolean[] columnEditables = new boolean[] {
//				false, false, false, false, false
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
//		tableLocation.getColumnModel().getColumn(2).setPreferredWidth(94);
//		tableLocation.getColumnModel().getColumn(3).setPreferredWidth(103);
//		tableLocation.getColumnModel().getColumn(4).setPreferredWidth(101);
//		tableLocation.setToolTipText("");
//		tableLocation.setColumnSelectionAllowed(true);
//		tableLocation.setCellSelectionEnabled(true);
		tableLocation.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		panelTableau.add(btnEnregistrer);
	}

}
