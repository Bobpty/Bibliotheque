package entity;

public class Personne
{
    private int IDpersonne;
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;

    public Personne(int IDpersonne, String nom, String prenom, String adresse, String mail)
    {
        this.IDpersonne = IDpersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
    }

    public int getIDpersonne()
    {
        return IDpersonne;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }
}
