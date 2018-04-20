package classes;

import java.util.Date;

public interface Emprunter
{
	public boolean estEmprunte();
	public void emprunter(Emprunteur unEmprunteur, Date dateDEmprunt);
	public void restituer(Emprunter unEmprunteur, Date dateRestitution);
}
