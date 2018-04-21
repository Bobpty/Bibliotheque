package entity;

public class Louer
{
    private Personne personne;
    private Medium medium;
    private String dateLocation;
    private String dateRestitution;
    private String commentaire;

    public Louer(String dateLocation, String dateRestitution, String commentaire, Personne personne, Medium medium)
    {
        this.dateLocation = dateLocation;
        this.dateRestitution = dateRestitution;
        this.commentaire = commentaire;
        this.personne = personne;
        this.medium = medium;
    }

    public Personne getPersonne()
    {
        return personne;
    }

    public Medium getMedium()
    {
        return medium;
    }

    public void setMedium(Medium medium)
    {
        this.medium = medium;
    }

    public String getDateLocation()
    {
        return dateLocation;
    }

    public void setDateLocation(String dateLocation)
    {
        this.dateLocation = dateLocation;
    }

    public String getDateRestitution()
    {
        return dateRestitution;
    }

    public void setDateRestitution(String dateRestitution)
    {
        this.dateRestitution = dateRestitution;
    }

    public String getCommentaire()
    {
        return commentaire;
    }

    public void setCommentaire(String commentaire)
    {
        this.commentaire = commentaire;
    }
}
