package dao;

import java.sql.Connection;
import java.util.List;

import util.DBUtil;

public abstract class Dao<T>
{
	Connection connexion = DBUtil.getInstance();

    /**
     * Cherche un objet T avec un identifiant id.
     * @param id l'identifiant de l'objet T cherché
     * @return T l'objet T ou null si celui-ci n'est pas trouv� ou si une erreur est survenue
     */
    public abstract T find(int id);

    /**
     * Retourne tous �l�ments de la table
     * @return liste contenant tous les �l�ments
     */
    public abstract List<T> findAll();

    /**
     * Ins�re l'objet obj dans la db
     * @param obj l'objet que l'on souhaite persister
     * @return T l'objet T ins�r� dans la db ou null si celui-ci n'est pas trouv�
     */
    public abstract T create(T obj);

    /**
     * Met � jour l'obj obj dans la db
     * @param obj qui sera mis � jour
     * @return un bool�en indiquant true si la requ�te s'est bien ex�cut�e, false sinon
     */
    public abstract boolean update(T obj);

    /**
     * Supprime l'objet obj de la db en le s�lectionnant via son id
     * @param obj l'objet que l'on souhaite supprimer de la db
     * @return un bool�en indiquant true si la requ�te s'est bien ex�cut�e, false sinon
     */
    public abstract boolean delete(T obj);
}