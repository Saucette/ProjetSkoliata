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
	* Référence vers le gestionnaire de persistance.
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
	 * @param objectifPedagogique bean entity représentant l'instance.
	 * @return l'instance de l'objectif pédagogique une fois persistée dans la base de données.
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

		if(!entityManager.contains(objectifPedagogique))         // Si l'entité n'est pas dans un état "géré" (managed),

		{                                               // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"

			objectifPedagogique = entityManager.merge(objectifPedagogique);	// Il faut la "rattacher" au contexte de persistance par l'appel		

		}                                               // de la méthode merge de l'EntityManager.

		

		// L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...

		entityManager.remove(objectifPedagogique);

	}

	//-----------------------------------------------------------------------------

}