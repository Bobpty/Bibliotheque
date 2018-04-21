package entity;

import java.util.Date;

public class Louer
{
    private Personne personne;
    private Medium medium;
    private Date dateLocation;
    private Date dateRestitution;
    private String commentaire;

    public Louer(Date dateLocation, Date dateRestitution, String commentaire, Personne personne, Medium medium)
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

    public Date getDateLocation()
    {
        return dateLocation;
    }

    public void setDateLocation(Date dateLocation)
    {
        this.dateLocation = dateLocation;
    }

    public Date getDateRestitution()
    {
        return dateRestitution;
    }

    public void setDateRestitution(Date dateRestitution)
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
