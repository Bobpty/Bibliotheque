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
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.Label;

public class Principale {

	private JFrame frmBibliothque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale window = new Principale();
					window.frmBibliothque.setVisible(true);
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
		frmBibliothque = new JFrame();
		frmBibliothque.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmBibliothque.setForeground(Color.WHITE);
		frmBibliothque.setTitle("Gestion d'une biblioth\u00E8que - Paul & Johan");
		frmBibliothque.setBounds(100, 100, 617, 442);
		frmBibliothque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBibliothque.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelHaut = new JPanel();
		frmBibliothque.getContentPane().add(panelHaut, BorderLayout.NORTH);
		panelHaut.setLayout(new CardLayout(0, 0));
		
		JMenuBar Bibliotheque = new JMenuBar();
		panelHaut.add(Bibliotheque, "name_88206213386731");
		
		JMenu mnBibliotheque = new JMenu("Bibliotheque");
		Bibliotheque.add(mnBibliotheque);
		
		JMenuItem mntmCrerUneBibliothque = new JMenuItem("Cr\u00E9er une biblioth\u00E8que");
		mnBibliotheque.add(mntmCrerUneBibliothque);
		
		JMenuItem mntmChangerUneBibliothque = new JMenuItem("Changer une biblioth\u00E8que");
		mnBibliotheque.add(mntmChangerUneBibliothque);
		
		JMenuItem mntmModifierUneBibliotheque = new JMenuItem("Modifier une bibliotheque");
		mnBibliotheque.add(mntmModifierUneBibliotheque);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnBibliotheque.add(mntmQuitter);
		
		JMenu mnMedia = new JMenu("Media");
		Bibliotheque.add(mnMedia);
		
		JMenuItem mntmStockerUnNouveau = new JMenuItem("Stocker un nouveau medium");
		mnMedia.add(mntmStockerUnNouveau);
		
		JMenuItem mntmLouerUnMedium = new JMenuItem("Louer un medium");
		mnMedia.add(mntmLouerUnMedium);
		
		JMenuItem mntmRestituerUnMedium = new JMenuItem("Restituer un medium");
		mnMedia.add(mntmRestituerUnMedium);
		
		JMenuItem mntmRechercher = new JMenuItem("Rechercher");
		mntmRechercher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnMedia.add(mntmRechercher);
		
		JMenu mnDivers = new JMenu("Divers");
		Bibliotheque.add(mnDivers);
		
		JMenuItem mntmLocationEnRetard = new JMenuItem("Locations en retard");
		mnDivers.add(mntmLocationEnRetard);
		
		JMenuItem mntmInventaire = new JMenuItem("Inventaire");
		mntmInventaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnDivers.add(mntmInventaire);
		
		JPanel panelMilieu = new JPanel();
		frmBibliothque.getContentPane().add(panelMilieu, BorderLayout.CENTER);
		panelMilieu.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelMilieu.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 6, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_7.add(scrollPane_1);
		
		Label label = new Label("New label");
		panel_7.add(label);
		
		Label label_1 = new Label("New label");
		panel_7.add(label_1);
		
		Label label_2 = new Label("New label");
		panel_7.add(label_2);
		
		Label label_3 = new Label("New label");
		panel_7.add(label_3);
		
		Label label_4 = new Label("New label");
		panel_7.add(label_4);
		
		Label label_5 = new Label("New label");
		panel_7.add(label_5);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel_3);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_3.add(scrollPane_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel_4);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		panel_4.add(scrollPane_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel_5);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panel_5.add(scrollPane_5);
		
		JPanel panel_13 = new JPanel();
		panelMilieu.add(panel_13);
		panel_13.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_13.setLayout(new GridLayout(0, 6, 0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_13.add(panel_12);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		panel_12.add(scrollPane_6);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_13.add(panel_11);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		panel_11.add(scrollPane_7);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_13.add(panel_10);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		panel_10.add(scrollPane_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_13.add(panel_9);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		panel_9.add(scrollPane_9);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_13.add(panel_8);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		panel_8.add(scrollPane_10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_13.add(panel_6);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		panel_6.add(scrollPane_11);
	}

}
