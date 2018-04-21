package ihm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.DaoArmoire;
import dao.DaoMedium;
import entity.Armoire;
import entity.Bibliotheque;
import entity.Louer;

public class InfoMedia {

	private JFrame frmInformationSurLeMedium;
	private JTextField textFieldTitre;
	private JTextField txtRealAutComp;
	private JTextField txtDuree;
	private JTextField txtDateParution;
	private JTextField txtPrixHt;
	private JTextField txtDureeDeLocation;
	private JTable tableLocation;
	
	ArrayList<Armoire> listNumArmoire =  new ArrayList<Armoire>();
	ArrayList<Armoire> listArmoire = new ArrayList<>();
	ArrayList<Louer> listLocation = new ArrayList<>();
	DaoArmoire daoArmoire = new DaoArmoire();
	DaoMedium daoMedium = new DaoMedium();
	Bibliotheque biblio = new Bibliotheque();


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
		
		JPanel panelTypeMedia = new JPanel();
		panel.add(panelTypeMedia);
		
		JLabel lblTypeMedia = new JLabel("Type: ");
		panelTypeMedia.add(lblTypeMedia);
		
		String[] tabTypeMedia = new String[] {"CD","DVD","Livre"};
		
		JComboBox<String> listTypeMedia = new JComboBox<>(tabTypeMedia);
		panelTypeMedia.add(listTypeMedia);
		JPanel panelStockerDansArmoire = new JPanel();
		panel.add(panelStockerDansArmoire);
		
		JLabel lblStockerDansArmoire = new JLabel("Stocker dans armoire:");
		panelStockerDansArmoire.add(lblStockerDansArmoire);
		listArmoire = daoArmoire.findAll();
		listNumArmoire = daoArmoire.findArmoiresParBibliotheque(listArmoire.get(0).getBibliotheque());
		
		Vector<String> vector = new Vector<>();
		
		for(int i = 0; i < listNumArmoire.size(); i++)
		{
			vector.add(String.valueOf(listNumArmoire.get(i).getNumArmoire()));
		}
		
		JComboBox<String> list = new JComboBox<>(vector);
		panelStockerDansArmoire.add(list);
		
		JScrollPane scrollPaneTableau = new JScrollPane();
		frmInformationSurLeMedium.getContentPane().add(scrollPaneTableau);
		
		JPanel panelTableau = new JPanel();
		scrollPaneTableau.setViewportView(panelTableau);
		panelTableau.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblLocations = new JLabel("Locations:");
		panelTableau.add(lblLocations);
		
		listLocation = (ArrayList<Louer>) daoMedium.findAllMediaEmpruntes();
		String  donnees[][] = new String[listLocation.size()][5];
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
		for(Louer location : listLocation)
		{
			String[] infoLocation = {location.getPersonne().getNom(), location.getPersonne().getAdresse(), formatter.format(location.getDateLocation()), formatter.format(location.getDateRestitution()), location.getCommentaire()};
			donnees[i] = infoLocation;
			i++;
		}
		
		String entete[] = {"Nom", "Adresse", "Date Location", "Date restitution", "Commentaire"};
		
		JScrollPane scrollPane = new JScrollPane();
		panelTableau.add(scrollPane);
		
		tableLocation = new JTable(donnees, entete);
		scrollPane.setViewportView(tableLocation);
		
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
