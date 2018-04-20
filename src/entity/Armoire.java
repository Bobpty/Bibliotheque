package entity;

public class Armoire
{
    private int numArmoire;
    private String nomArmoire;
    private Bibliotheque bibliotheque;

	public Armoire(int numArmoire, String nomArmoire, Bibliotheque bibliotheque)
    {
        this.numArmoire = numArmoire;
        this.nomArmoire = nomArmoire;
        this.bibliotheque = bibliotheque;
    }

    public int getNumArmoire()
    {
        return numArmoire;
    }

    public String getNomArmoire()
    {
        return nomArmoire;
    }

    public void setNomArmoire(String nomArmoire)
    {
        this.nomArmoire = nomArmoire;
    }
    
    public Bibliotheque getBibliotheque()
    {
		return bibliotheque;
	}

	public void setBibliotheque(Bibliotheque bibliotheque)
	{
		this.bibliotheque = bibliotheque;
	}

	public void setNumArmoire(int numArmoire)
	{
		this.numArmoire = numArmoire;
	}
}
