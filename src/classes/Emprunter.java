/**
 * 
 */
package classes;

import java.util.Date;

/**
 * @author Paul Payraudeau
 *
 */
public interface Emprunter {
	public boolean estEmprunte();
	public void emprunter(Emprunteur unEmprunteur, Date dateDEmprunt);
	public void restituer(Emprunter unEmprunteur, Date dateRestitution);
}
