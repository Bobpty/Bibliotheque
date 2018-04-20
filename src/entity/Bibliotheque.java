package entity;

public class Bibliotheque
{
    private int numBibliotheque;
    private String nomBibliotheque;
    private String adresse;
    private int nombreRangees;

    public Bibliotheque(int numBibliotheque, String nomBibliotheque, String adresse, int nombreRangees)
    {
        this.numBibliotheque = numBibliotheque;
        this.nomBibliotheque = nomBibliotheque;
        this.adresse = adresse;
        this.nombreRangees = nombreRangees;
    }
    

    public Bibliotheque() {
		super();
	}


	public int getNumBibliotheque()
    {
        return numBibliotheque;
    }

    public String getNomBibliotheque()
    {
        return nomBibliotheque;
    }

    public void setNomBibliotheque(String nomBibliotheque)
    {
        this.nomBibliotheque = nomBibliotheque;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public int getNombreRangees()
    {
        return nombreRangees;
    }

    public void setNombreRangees(int nombreRangees)
    {
        this.nombreRangees = nombreRangees;
    }
}
