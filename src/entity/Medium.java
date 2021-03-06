package entity;

public class Medium
{
    private int numMedia;
    private String Titre;
    private String interRealAuteur;
    private int contenant;
    private String dateParution;
    private String dateStockage;
    private Float prix;
    private int dureeLocation;
    private String type;
    private Armoire armoire;

    public Medium(int numMedia, String titre, String interRealAuteur, int contenant, String dateParution, String dateStockage, Float prix, int dureeLocation, String type, Armoire armoire)
    {
        this.numMedia = numMedia;
        Titre = titre;
        this.interRealAuteur = interRealAuteur;
        this.contenant = contenant;
        this.dateParution = dateParution;
        this.dateStockage = dateStockage;
        this.prix = prix;
        this.dureeLocation = dureeLocation;
        this.type = type;
        this.setArmoire(armoire);
    }

    public Medium(String titre2, String auteurRealInter, int dureeNbPagesChansons, String dateParution2,
			String dateStockage2, float prix2, int dureeDeLocation, String type2, Armoire armoire) {
         Titre = titre2;
         interRealAuteur = auteurRealInter;
         contenant = dureeNbPagesChansons;
         dateParution = dateParution2;
         dateStockage = dateStockage2;
         prix = prix2;
         dureeLocation = dureeDeLocation;
         type = type2;
         setArmoire(armoire);
	}

	public int getNumMedia()
    {
        return numMedia;
    }

    public String getTitre()
    {
        return Titre;
    }

    public void setTitre(String titre)
    {
        Titre = titre;
    }

    public String getInterRealAuteur()
    {
        return interRealAuteur;
    }

    public void setInterRealAuteur(String interRealAuteur)
    {
        this.interRealAuteur = interRealAuteur;
    }

    public int getContenant()
    {
        return contenant;
    }

    public void setContenant(int contenant)
    {
        this.contenant = contenant;
    }

    public String getDateParution()
    {
        return dateParution;
    }

    public void setDateParution(String dateParution)
    {
        this.dateParution = dateParution;
    }

    public String getDateStockage()
    {
        return dateStockage;
    }

    public void setDateStockage(String dateStockage)
    {
        this.dateStockage = dateStockage;
    }

    public Float getPrix()
    {
        return prix;
    }

    public void setPrix(Float prix)
    {
        this.prix = prix;
    }

    public int getDureeLocation()
    {
        return dureeLocation;
    }

    public void setDureeLocation(int dureeLocation)
    {
        this.dureeLocation = dureeLocation;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

	public Armoire getArmoire() {
		return armoire;
	}

	public void setArmoire(Armoire armoire) {
		this.armoire = armoire;
	}
}
