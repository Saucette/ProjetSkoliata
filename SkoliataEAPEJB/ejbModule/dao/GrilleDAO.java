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
	* Référence vers le gestionnaire de persistance.
	*/
	@PersistenceContext
	EntityManager entityManager;
	
	public GrilleDAO() {}
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
	
	public Grille findByName(String name)
	{
		return entityManager.find(Grille.class, name);
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
	//-------------------------------------------------------------------------------
//	public void delete(Integer id_grille)
//	{
//		Query query1 = entityManager.createQuery("DELETE FROM critere WHERE critere.id_grille = :id_grille");
//		query1.setParameter("id_grille", id_grille);
//		
//		Query query2 = entityManager.createQuery("DELETE FROM asso_oge WHERE asso_oge.id_grille = :id_grille");
//		query2.setParameter("id_grille", id_grille);
//		
//		Query query3 = entityManager.createQuery("DELETE FROM grille WHERE grille.id = :id_grille");
//		query3.setParameter("id_grille", id_grille);
//	}
	//-----------------------------------------------------------------------------
	public void validation(String nom_grille)
	{
		Query query = entityManager.createQuery("UPDATE grille SET valide = TRUE WHERE grille.nom = :nom_grille");
	  	query.setParameter("nom_grille",nom_grille);
		return;
	}
}