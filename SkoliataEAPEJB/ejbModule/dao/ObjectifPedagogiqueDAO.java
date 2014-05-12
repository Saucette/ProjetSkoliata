package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.ObjectifPedagogique;

/**
 * Session Bean implementation class EntrepriseDAO
 * @author Hugo COLLET
 */

@Stateless
@LocalBean

public class ObjectifPedagogiqueDAO

{
	//-----------------------------------------------------------------------------
	/**
	* R�f�rence vers le gestionnaire de persistance.
	*/
	@PersistenceContext
	EntityManager entityManager;
	//-----------------------------------------------------------------------------
	/**
	 * Default constructor.
	 */
	public ObjectifPedagogiqueDAO()
	{
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------

	/**
	 * Rend persistante l'instance (bean entity) de l'entreprise.
	 * @param objectifPedagogique bean entity repr�sentant l'instance.
	 * @return l'instance de l'objectif p�dagogique une fois persist�e dans la base de donn�es.
	 */

	public ObjectifPedagogique persist(ObjectifPedagogique objectifPedagogique)

	{

		entityManager.persist(objectifPedagogique);

		return objectifPedagogique;

	}

	//----------------------------------------------------------------------------

	public ObjectifPedagogique findById(Integer id)

	{

		return entityManager.find(ObjectifPedagogique.class, id);

	}

	//----------------------------------------------------------------------------

  @SuppressWarnings({ "rawtypes", "unchecked" })

  public List<ObjectifPedagogique> findAll()

	{

		Query query = entityManager.createQuery("select objectifPedagogique from ObjectifPedagogique objectifPedagogique order by objectifPedagogique.id");

		List l = query.getResultList(); 

		

		return (List<ObjectifPedagogique>)l;

	}

	//-----------------------------------------------------------------------------

	public ObjectifPedagogique update(ObjectifPedagogique objectifPedagogique)

	{

		entityManager.merge(objectifPedagogique);

		return findById(objectifPedagogique.getId());

	}

	//-----------------------------------------------------------------------------

	public void remove(ObjectifPedagogique objectifPedagogique)

	{

		if(!entityManager.contains(objectifPedagogique))         // Si l'entit� n'est pas dans un �tat "g�r�" (managed),

		{                                               // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"

			objectifPedagogique = entityManager.merge(objectifPedagogique);	// Il faut la "rattacher" au contexte de persistance par l'appel		

		}                                               // de la m�thode merge de l'EntityManager.

		

		// L'entit� �tait d�j� attach�e ou a �t� rattach�e, on peut donc la supprimer...

		entityManager.remove(objectifPedagogique);

	}

	//-----------------------------------------------------------------------------

}