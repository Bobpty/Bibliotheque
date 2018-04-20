package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Dimension;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.javafx.font.Disposer;

import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.Label;

public class Principale implements ActionListener{
	
	private ArrayList<JPanel> listArmoire = new ArrayList<JPanel>();
	private JFrame frmBibliotheque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale window = new Principale();
					window.frmBibliotheque.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principale() {
		initialize();
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
		
		JPanel rangeArmoire = null;
		for(int i = 0; i <20; i++)
		{
			if(i%6 == 0) {
				rangeArmoire = new JPanel(new GridLayout(0, 6, 0, 0));
				rangeArmoire.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panelMilieu.add(rangeArmoire);
			}
			JPanel armoire = new JPanel(new GridLayout(3, 1));
			armoire.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			rangeArmoire.add(armoire);
			listArmoire.add(armoire);
		}
		
//		JPanel panel_14 = new JPanel();
//		panel_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panelMilieu.add(panel_14);
//		panel_14.setLayout(new GridLayout(0, 6, 0, 0));
//		
//		JPanel panel_7 = new JPanel();
//		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_14.add(panel_7);
//		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		
//		Label label = new Label("New label");
//		panel_7.add(label);
//		
//		Label label_1 = new Label("New label");
//		panel_7.add(label_1);
//		
//		Label label_2 = new Label("New label");
//		panel_7.add(label_2);
//		
//		Label label_3 = new Label("New label");
//		panel_7.add(label_3);
//		
//		Label label_4 = new Label("New label");
//		panel_7.add(label_4);
//		
//		Label label_5 = new Label("New label");
//		panel_7.add(label_5);
//		
//		JPanel panel = new JPanel();
//		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_14.add(panel);
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_14.add(panel_2);
//		
//		JPanel panel_3 = new JPanel();
//		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_14.add(panel_3);
//		
//		JPanel panel_4 = new JPanel();
//		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_14.add(panel_4);
//		
//		JPanel panel_5 = new JPanel();
//		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_14.add(panel_5);
//		
//		JPanel panel_13 = new JPanel();
//		panelMilieu.add(panel_13);
//		panel_13.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_13.setLayout(new GridLayout(0, 6, 0, 0));
//		
//		JPanel panel_12 = new JPanel();
//		panel_13.add(panel_12);
//		
//		JPanel panel_11 = new JPanel();
//		panel_11.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_13.add(panel_11);
//		
//		JPanel panel_10 = new JPanel();
//		panel_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_13.add(panel_10);
//		
//		JPanel panel_9 = new JPanel();
//		panel_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_13.add(panel_9);
//		
//		JPanel panel_8 = new JPanel();
//		panel_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_13.add(panel_8);
//		
//		JPanel panel_6 = new JPanel();
//		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		panel_13.add(panel_6);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "stockerNouveauMedium":
			frmBibliotheque.dispose();
			new InfoMedia();
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
			new Rechercher();
			break;
		case "inventaire":
			frmBibliotheque.dispose();
//			new Inventaire();
			break;
		default:
			
			break;
		}
		
	}
}
