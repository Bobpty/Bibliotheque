/**
 * 
 */
package classes;

import java.util.Date;

/**
 * @author Paul Payraudeau
 *
 */
public class Media{
	private String titreMedia;
	private String realAutInt;
	private Date dateParution;
	private double prixHT;
	private int dureeLocation;
	
	
	public Media(String titreMedia, String realAutInt, Date dateParution, double prixHT, int dureeLocation) 
	{
		super();
		this.titreMedia = titreMedia;
		this.realAutInt = realAutInt;
		this.dateParution = dateParution;
		this.prixHT = prixHT;
		this.dureeLocation = dureeLocation;
	}
	
	public String getTitreMedia() {
		return titreMedia;
	}
	public void setTitreMedia(String titreMedia) {
		this.titreMedia = titreMedia;
	}
	public String getRealAutInt() {
		return realAutInt;
	}
	public void setRealAutInt(String realAutInt) {
		this.realAutInt = realAutInt;
	}
	public Date getDateParution() {
		return dateParution;
	}
	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}
	public double getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}
	public int getDureeLocation() {
		return dureeLocation;
	}
	public void setDureeLocation(int dureeLocation) {
		this.dureeLocation = dureeLocation;
	}
}
