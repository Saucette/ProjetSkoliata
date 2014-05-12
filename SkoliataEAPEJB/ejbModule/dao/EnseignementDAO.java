package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Enseignement;

/**
 * Session Bean implementation class EntrepriseDAO
 * @author Hugo COLLET
 */

@Stateless
@LocalBean

public class EnseignementDAO

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
	public EnseignementDAO()
	{
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------

	/**
	 * Rend persistante l'instance (bean entity) de l'entreprise.
	 * @param enseignement bean entity repr�sentant l'instance.
	 * @return l'instance de l'enseignement une fois persist�e dans la base de donn�es.
	 */

	public Enseignement persist(Enseignement enseignement)

	{

		entityManager.persist(enseignement);

		return enseignement;

	}

	//----------------------------------------------------------------------------

	public Enseignement findById(Integer id)

	{

		return entityManager.find(Enseignement.class, id);

	}

	//----------------------------------------------------------------------------

  @SuppressWarnings({ "rawtypes", "unchecked" })

  public List<Enseignement> findAll()

	{

		Query query = entityManager.createQuery("select enseignement from Enseignement enseignement order by enseignement.id");

		List l = query.getResultList(); 

		

		return (List<Enseignement>)l;

	}

	//-----------------------------------------------------------------------------

	public Enseignement update(Enseignement enseignement)

	{

		entityManager.merge(enseignement);

		return findById(enseignement.getId());

	}

	//-----------------------------------------------------------------------------

	public void remove(Enseignement enseignement)

	{

		if(!entityManager.contains(enseignement))         // Si l'entit� n'est pas dans un �tat "g�r�" (managed),

		{                                               // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"

			enseignement = entityManager.merge(enseignement);	// Il faut la "rattacher" au contexte de persistance par l'appel		

		}                                               // de la m�thode merge de l'EntityManager.

		

		// L'entit� �tait d�j� attach�e ou a �t� rattach�e, on peut donc la supprimer...

		entityManager.remove(enseignement);

	}

	//-----------------------------------------------------------------------------

}