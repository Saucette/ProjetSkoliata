package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Enseignant;

/**
 * Session Bean implementation class EntrepriseDAO
 * @author Hugo COLLET
 */

@Stateless
@LocalBean

public class EnseignantDAO

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
	public EnseignantDAO()
	{
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------

	/**
	 * Rend persistante l'instance (bean entity) de l'entreprise.
	 * @param enseignant bean entity représentant l'instance.
	 * @return l'instance de l'enseignant une fois persistée dans la base de données.
	 */

	public Enseignant persist(Enseignant enseignant)

	{

		entityManager.persist(enseignant);

		return enseignant;

	}

	//----------------------------------------------------------------------------

	public Enseignant findById(Integer id)

	{

		return entityManager.find(Enseignant.class, id);

	}

	//----------------------------------------------------------------------------

  @SuppressWarnings({ "rawtypes", "unchecked" })

  public List<Enseignant> findAll()

	{

		Query query = entityManager.createQuery("select enseignant from Enseignant enseignant order by enseignant.id");

		List l = query.getResultList(); 

		

		return (List<Enseignant>)l;

	}

	//-----------------------------------------------------------------------------

	public Enseignant update(Enseignant enseignant)

	{

		entityManager.merge(enseignant);

		return findById(enseignant.getId());

	}

	//-----------------------------------------------------------------------------

	public void remove(Enseignant enseignant)

	{

		if(!entityManager.contains(enseignant))         // Si l'entité n'est pas dans un état "géré" (managed),

		{                                               // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"

			enseignant = entityManager.merge(enseignant);	// Il faut la "rattacher" au contexte de persistance par l'appel		

		}                                               // de la méthode merge de l'EntityManager.

		

		// L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...

		entityManager.remove(enseignant);

	}

	//-----------------------------------------------------------------------------

}