package dao;

import java.sql.Connection;
import java.util.List;

import util.DBUtil;

public abstract class Dao<T>
{
	Connection connexion = DBUtil.setConnection("localhost", "bibliotheque");

    /**
     * Cherche un objet T avec un identifiant id.
     * @param id l'identifiant de l'objet T cherché
     * @return T l'objet T ou null si celui-ci n'est pas trouvé ou si une erreur est survenue
     */
    public abstract T find(int id);

    /**
     * Retourne tous éléments de la table
     * @return liste contenant tous les éléments
     */
    public abstract List<T> findAll();

    /**
     * Insère l'objet obj dans la db
     * @param obj l'objet que l'on souhaite persister
     * @return T l'objet T inséré dans la db ou null si celui-ci n'est pas trouvé
     */
    public abstract T create(T obj);

    /**
     * Met à jour l'obj obj dans la db
     * @param obj qui sera mis à jour
     * @return un booléen indiquant true si la requête s'est bien exécutée, false sinon
     */
    public abstract boolean update(T obj);

    /**
     * Supprime l'objet obj de la db en le sélectionnant via son id
     * @param obj l'objet que l'on souhaite supprimer de la db
     * @return un booléen indiquant true si la requête s'est bien exécutée, false sinon
     */
    public abstract boolean delete(T obj);
}