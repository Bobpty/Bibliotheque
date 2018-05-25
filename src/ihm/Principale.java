package ihm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import dao.DaoArmoire;
import dao.DaoMedium;
import entity.Bibliotheque;
import entity.Medium;

public class Principale implements ActionListener{
	
	private JFrame frmBibliotheque;
	private ArrayList<Medium> listMedium = new ArrayList<Medium>();
	Bibliotheque laBibliotheque;
	DaoArmoire daoArmoire = new DaoArmoire();
	DaoMedium daoMedium = new DaoMedium();
	

	/**
	 * Create the application.
	 */
	public Principale() {
		laBibliotheque = null;
		initialize();
		frmBibliotheque.setVisible(true);
	}
	
	public Principale(Bibliotheque laBiblio)
	{
		laBibliotheque = laBiblio;
		initialize();
		frmBibliotheque.setVisible(true);	
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBibliotheque = new JFrame();
		frmBibliotheque.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmBibliotheque.setForeground(Color.WHITE);
		frmBibliotheque.setTitle("Gestion d'une biblioth\u00E8que - Paul & Johan");
		frmBibliotheque.setBounds(100, 100, 617, 442);
		frmBibliotheque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBibliotheque.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelHaut = new JPanel();
		frmBibliotheque.getContentPane().add(panelHaut, BorderLayout.NORTH);
		panelHaut.setLayout(new CardLayout(0, 0));
		
		JMenuBar Bibliotheque = new JMenuBar();
		panelHaut.add(Bibliotheque, "name_88206213386731");
		
		JMenu mnBibliotheque = new JMenu("Bibliotheque");
		Bibliotheque.add(mnBibliotheque);
		
		JMenuItem mntmCreerUneBibliotheque = new JMenuItem("Cr\u00E9er une biblioth\u00E8que");
		mntmCreerUneBibliotheque.setActionCommand("CreerBiblio");
		mntmCreerUneBibliotheque.addActionListener(this);
		mnBibliotheque.add(mntmCreerUneBibliotheque);
		
		JMenuItem mntmChangerUneBibliotheque = new JMenuItem("Changer une biblioth\u00E8que");
		mntmChangerUneBibliotheque.setActionCommand("changerBiblio");
		mntmChangerUneBibliotheque.addActionListener(this);
		mnBibliotheque.add(mntmChangerUneBibliotheque);
		
		JMenuItem mntmModifierUneBibliotheque = new JMenuItem("Modifier une bibliotheque");
		mntmModifierUneBibliotheque.setActionCommand("modifierBiblio");
		mntmModifierUneBibliotheque.addActionListener(this);
		mnBibliotheque.add(mntmModifierUneBibliotheque);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setActionCommand("quitter");
		mntmQuitter.addActionListener(this);
		mntmQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnBibliotheque.add(mntmQuitter);
		
		JMenu mnMedia = new JMenu("Media");
		Bibliotheque.add(mnMedia);
		
		JMenuItem mntmStockerUnNouveauMedium = new JMenuItem("Stocker un nouveau medium");
		mntmStockerUnNouveauMedium.setActionCommand("stockerNouveauMedium");
		mntmStockerUnNouveauMedium.addActionListener(this);
		mnMedia.add(mntmStockerUnNouveauMedium);
		
		JMenuItem mntmLouerUnMedium = new JMenuItem("Louer un medium");
		mntmLouerUnMedium.setActionCommand("louerUnMedium");
		mntmLouerUnMedium.addActionListener(this);
		mnMedia.add(mntmLouerUnMedium);
		
		JMenuItem mntmRestituerUnMedium = new JMenuItem("Restituer un medium");
		mntmRestituerUnMedium.setActionCommand("restituerUnMedium");
		mntmRestituerUnMedium.addActionListener(this);
		mnMedia.add(mntmRestituerUnMedium);
		
		JMenuItem mntmRechercher = new JMenuItem("Rechercher");
		mntmRechercher.setActionCommand("RechercherMedium");
		mntmRechercher.addActionListener(this);
		mntmRechercher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnMedia.add(mntmRechercher);
		
		JMenu mnDivers = new JMenu("Divers");
		Bibliotheque.add(mnDivers);
		
		JMenuItem mntmLocationEnRetard = new JMenuItem("Locations en retard");
		mntmLocationEnRetard.setActionCommand("locationEnRetard");
		mntmLocationEnRetard.addActionListener(this);
		mnDivers.add(mntmLocationEnRetard);
		
		JMenuItem mntmInventaire = new JMenuItem("Inventaire");
		mntmInventaire.setActionCommand("inventaire");
		mntmInventaire.addActionListener(this);
		mntmInventaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnDivers.add(mntmInventaire);
		
		JPanel panelMilieu = new JPanel();
		frmBibliotheque.getContentPane().add(panelMilieu, BorderLayout.CENTER);
		panelMilieu.setLayout(new GridLayout(0, 1, 0, 0));
		
		if (laBibliotheque != null)
		{
			int nbreArmoire = daoArmoire.countNombreArmoires(laBibliotheque);
		
			JPanel rangeArmoire = null;
			JLabel nomMedia = null;
			listMedium = (ArrayList<Medium>) daoMedium.findByBibliotheque(laBibliotheque);
			int nombreMedia = listMedium.size();
			for(int i = 0; i < nbreArmoire; i++)
			{
				for (int j = 0; j < nombreMedia; j++) {
				

					if(i%6 == 0) {
						rangeArmoire = new JPanel(new GridLayout(0, 6, 0, 0));
						rangeArmoire.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panelMilieu.add(rangeArmoire);
						nomMedia = new JLabel(listMedium.get(j).getTitre());
						rangeArmoire.add(nomMedia);
					}
					JPanel armoire = new JPanel(new GridLayout(3, 1));
					armoire.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					rangeArmoire.add(armoire);
					nomMedia = new JLabel(listMedium.get(j).getTitre());
					armoire.add(nomMedia);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "stockerNouveauMedium":
			frmBibliotheque.dispose();
			new InfoMedia(laBibliotheque);
			break;
		case "louerUnMedium":
			frmBibliotheque.dispose();
			new Location();
			break;
		case "restituerUnMedium":
			frmBibliotheque.dispose();
			new Location();
			break;
		case "CreerBiblio":
			frmBibliotheque.dispose();
			new InfoBiblio();
			break;
		case "changerBiblio":
			frmBibliotheque.dispose();
			new ChargerBiblio();
			break;
		case "modifierBiblio":
			frmBibliotheque.dispose();
			new InfoBiblio();
			break;
		case "quitter":
			frmBibliotheque.dispose();
			break;
		case "locationEnRetard":
			frmBibliotheque.dispose();
			break;
			
		case "RechercherMedium":
			frmBibliotheque.dispose();
			new Rechercher(laBibliotheque);
			break;
		case "inventaire":
			frmBibliotheque.dispose();
			new Inventaire();
			break;
		default:
			
			break;
		}
		
	}
}
