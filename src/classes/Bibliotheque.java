/**
 * 
 */
package classes;

import java.util.Date;

public class Bibliotheque implements Emprunter
{

	DVD unDVD;
	CD unCD;
	Livre unLivre;
	
	public void creerUnDVD(String titreMedia, String realAutInt, Date dateParution, double prixHT, int dureeLocation, int uneDuree) {
		unDVD = new DVD(titreMedia, realAutInt, dateParution, prixHT, dureeLocation, uneDuree);
	}
	
	public void creerUnCD(String titreMedia, String realAutInt, Date dateParution, double prixHT, int dureeLocation, int unNbChanson)
	{
		unCD = new CD(titreMedia, realAutInt, dateParution, prixHT, dureeLocation, unNbChanson);
	}
	
	public void creerUnLivre(String titreMedia, String realAutInt, Date dateParution, double prixHT, int dureeLocation, int unNbPages)
	{
		unLivre = new Livre(titreMedia, realAutInt, dateParution, prixHT, dureeLocation, unNbPages);
	}

	@Override
	public boolean estEmprunte()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void emprunter(Emprunteur unEmprunteur, Date dateDEmprunt)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restituer(Emprunter unEmprunteur, Date dateRestitution)
	{
		// TODO Auto-generated method stub
		
	}
}
