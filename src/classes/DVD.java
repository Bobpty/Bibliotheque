/**
 * 
 */
package classes;

import java.util.Date;

/**
 * @author Paul Payraudeau
 *
 */
public class DVD extends Media{
	private int duree;

	
	public DVD(String titreMedia, String realAutInt, Date dateParution, double prixHT, int dureeLocation, int uneDuree)
	{
		super(titreMedia, realAutInt, dateParution, prixHT, dureeLocation);
		this.duree = uneDuree;
	}

	@Override
	public String getTitreMedia() {
		// TODO Auto-generated method stub
		return super.getTitreMedia();
	}

	@Override
	public void setTitreMedia(String titreMedia) {
		// TODO Auto-generated method stub
		super.setTitreMedia(titreMedia);
	}

	@Override
	public String getRealAutInt() {
		// TODO Auto-generated method stub
		return super.getRealAutInt();
	}

	@Override
	public void setRealAutInt(String realAutInt) {
		// TODO Auto-generated method stub
		super.setRealAutInt(realAutInt);
	}

	@Override
	public Date getDateParution() {
		// TODO Auto-generated method stub
		return super.getDateParution();
	}

	@Override
	public void setDateParution(Date dateParution) {
		// TODO Auto-generated method stub
		super.setDateParution(dateParution);
	}

	@Override
	public double getPrixHT() {
		// TODO Auto-generated method stub
		return super.getPrixHT();
	}

	@Override
	public void setPrixHT(double prixHT) {
		// TODO Auto-generated method stub
		super.setPrixHT(prixHT);
	}

	@Override
	public int getDureeLocation() {
		// TODO Auto-generated method stub
		return super.getDureeLocation();
	}

	@Override
	public void setDureeLocation(int dureeLocation) {
		// TODO Auto-generated method stub
		super.setDureeLocation(dureeLocation);
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
}
