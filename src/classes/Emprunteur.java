/**
 * 
 */
package classes;

/**
 * @author Paul Payraudeau
 *
 */
public class Emprunteur {
	private String nom;
	private String prenom;
	private int nbMediaEmprunte;
	private String adresse;
	private String telephone;
	
	public Emprunteur(String nom, String prenom, int nbMediaEmprunte, String adresse, String telephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nbMediaEmprunte = nbMediaEmprunte;
		this.adresse = adresse;
		this.telephone = telephone;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getNbMediaEmprunte() {
		return nbMediaEmprunte;
	}
	public void setNbMediaEmprunte(int nbMediaEmprunte) {
		this.nbMediaEmprunte = nbMediaEmprunte;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}