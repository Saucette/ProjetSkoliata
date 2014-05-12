package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Eleve;

/**
 * Session Bean implementation class EntrepriseDAO
 * @author Hugo COLLET
 */

@Stateless
@LocalBean

public class EleveDAO

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
	public EleveDAO()
	{
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------

	/**
	 * Rend persistante l'instance (bean entity) de l'entreprise.
	 * @param eleve bean entity représentant l'instance.
	 * @return l'instance de l'eleve une fois persistée dans la base de données.
	 */

	public Eleve persist(Eleve eleve)

	{

		entityManager.persist(eleve);

		return eleve;

	}

	//----------------------------------------------------------------------------

	public Eleve findById(Integer id)

	{

		return entityManager.find(Eleve.class, id);

	}

	//----------------------------------------------------------------------------

  @SuppressWarnings({ "rawtypes", "unchecked" })

  public List<Eleve> findAll()

	{

		Query query = entityManager.createQuery("select eleve from Eleve eleve order by eleve.id");

		List l = query.getResultList(); 

		

		return (List<Eleve>)l;

	}

	//-----------------------------------------------------------------------------

	public Eleve update(Eleve eleve)

	{

		entityManager.merge(eleve);

		return findById(eleve.getId());

	}

	//-----------------------------------------------------------------------------

//	public List<Eleve> findbyEnseignantEnseignement(String nom_enseignement, int id_enseignant)
//	{
//		Query query = entityManager.createQuery("requete à définir" );
//		List <Eleve> l = query.getResultList();
//		return l;
//		
//	}
	
	
	//-----------------------------------------------------------------------------

	public void remove(Eleve eleve)

	{

		if(!entityManager.contains(eleve))         // Si l'entité n'est pas dans un état "géré" (managed),

		{                                               // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"

			eleve = entityManager.merge(eleve);	// Il faut la "rattacher" au contexte de persistance par l'appel		

		}                                               // de la méthode merge de l'EntityManager.

		

		// L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...

		entityManager.remove(eleve);

	}

	//-----------------------------------------------------------------------------

}