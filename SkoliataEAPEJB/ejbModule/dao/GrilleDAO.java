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
	* R�f�rence vers le gestionnaire de persistance.
	*/
	@PersistenceContext
	EntityManager entityManager;
	
	public GrilleDAO() {}
	//-----------------------------------------------------------------------------
	
	/**
	 * Rend persistante l'instance (bean entity) de l'entreprise.
	 * @param grille bean entity repr�sentant l'instance.
	 * @return l'instance de la grille une fois persist�e dans la base de donn�es.
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
		if(!entityManager.contains(grille))         // Si l'entit� n'est pas dans un �tat "g�r�" (managed),
		{                                               // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"
			grille = entityManager.merge(grille);	// Il faut la "rattacher" au contexte de persistance par l'appel		
		}                                               // de la m�thode merge de l'EntityManager.
		// L'entit� �tait d�j� attach�e ou a �t� rattach�e, on peut donc la supprimer...
		entityManager.remove(grille);
	}
	
	//-----------------------------------------------------------------------------
	public void validation(String nom_grille)
	{
		Query query = entityManager.createQuery("UPDATE grille SET valide = TRUE WHERE grille.nom = :nom_grille");
	  	query.setParameter("nom_grille",nom_grille);
		return;
	}
}