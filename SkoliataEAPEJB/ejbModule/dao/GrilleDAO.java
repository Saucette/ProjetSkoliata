package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Grille;

/**
 * Session Bean implementation class EntrepriseDAO
 * @author Hugo COLLET
 */

@Stateless
@LocalBean

public class GrilleDAO

{
	//-----------------------------------------------------------------------------
	/**
	* Référence vers le gestionnaire de persi
	* stance.
	*/
	@PersistenceContext
	EntityManager entityManager;
	//-----------------------------------------------------------------------------
	/**
	 * Default constructor.
	 */
	public GrilleDAO()
	{
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------

	/**
	 * Rend persistante l'instance (bean entity) de l'entreprise.
	 * @param grille bean entity représentant l'instance.
	 * @return l'instance de la grille une fois persistée dans la base de données.
	 */

	public Grille persist(Grille grille)

	{

		entityManager.persist(grille);

		return grille;

	}

	//----------------------------------------------------------------------------

	public Grille findById(Integer id)

	{

		return entityManager.find(Grille.class, id);

	}

	//----------------------------------------------------------------------------

  @SuppressWarnings({ "rawtypes", "unchecked" })

  public List<Grille> findAll()

	{

		Query query = entityManager.createQuery("select grille from Grille grille order by grille.id");

		List l = query.getResultList(); 

		

		return (List<Grille>)l;

	}

	//-----------------------------------------------------------------------------

	public Grille update(Grille grille)

	{

		entityManager.merge(grille);

		return findById(grille.getId());

	}

	//-----------------------------------------------------------------------------

	public void remove(Grille grille)

	{

		if(!entityManager.contains(grille))         // Si l'entité n'est pas dans un état "géré" (managed),

		{                                               // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"

			grille = entityManager.merge(grille);	// Il faut la "rattacher" au contexte de persistance par l'appel		

		}                                               // de la méthode merge de l'EntityManager.

		

		// L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...

		entityManager.remove(grille);

	}

	//-----------------------------------------------------------------------------

}