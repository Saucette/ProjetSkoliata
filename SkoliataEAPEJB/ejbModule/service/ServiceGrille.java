package service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.util.List;

import model.*;
import dao.*;

/**
 * Session Bean implementation class ServiceGrille
 */
@Stateless
@LocalBean
public class ServiceGrille implements IServiceGrille {

	@EJB private GrilleDAO grilleDAO;
	@EJB private CritereDAO critereDAO;
	@EJB private CritereDAO droitDAO;
    /**
     * Default constructor. 
     */
	/*Quelques problèmes rencontrés :
	 * 
	 * - Je n'arrive pas à agir via le DAO Grille sur les autres tables/DAO, je ne sais pas si c'est possible.
	 * Il me semble qu'une requête en JPQL peut convenir (lourd), ou une solution en Java si on change la structure des tables
	 * La suppression d'une grille, et de ses données en cascade dans la table critere et ASSO_OGE est un exemple
	 * 
	 * - ASSO_OGE est une table d'association à trois clés primaires, et ne possède pas de DAO. 
	 * Les opérations d'insertion, de modification, de suppression dans cette table sont donc un peu compliquées à faire en Java.
	 * Idem pour ASSO_3E.
	 * 
	 * - la fonction copy ne marche pas :
	 *
	 */
    public ServiceGrille() {
    }
    
    	// FD1 : Créer une grille d'évaluation
    	@Override
        public Grille newGrille(Integer nb_niv, String n1, String n2, String n3, String n4)
    	{
    	    Grille grille = new Grille();
    	    grille.setNiveauPerformance1(n1);
    	    grille.setNiveauPerformance2(n2);
    	    // Nom de test en attendant un auto-génération...
    	    grille.setNom("Grille1");
    	    grille.setValide(false);
    	    
    	    // TODO : Insertion dans la table DROITS pour la notion de propriétaire 
  
    	    if(nb_niv>2)
    	    {
    	    	grille.setNiveauPerformance3(n3);
    	    	grille.setNiveauPerformance4(n4);
    	    }
    	    return grilleDAO.persist(grille);
    	   // TODO : Idée pour l'auto génération ? : grille.setNom("grille"+grille.getId());
    	    
        }

    	// FD2 : Obtenir les informations de définition d'une grille d'évaluation (par son nom ou son id)
    	@Override 
    	public Grille getById(Integer id)
    	{
    		return grilleDAO.findById(id);
    	}
    	
		@Override
		public Grille getByName(String nom) 
		{
			return grilleDAO.findByName(nom);
		}
    	
		// FD3 : Lister les grilles d'évaluations
		@Override
		public List<Grille> getAllGrille()
		{
			return grilleDAO.findAll();
		}
		
		// FD4 : Ajouter un critère à une grille d'évaluation
		@Override
		public Grille addCritere(String nom_grille, String description_critere, String description_n1,String description_n2,String description_n3,String description_n4)
		{
			Integer i = 0;
			Grille grilletmp = grilleDAO.findByName(nom_grille);
			Critere critere = new Critere();
			critere.setDescription(description_critere);
			critere.setDescNiveauPerformance1(description_n1);
			critere.setDescNiveauPerformance2(description_n2);
			i=2;
			
			/* Vérification pour que l'utilisateur rentre le bon nombre de niveaux (critère <---> grille)
			Si les descriptions des critères 3 et 4 sont vides, alors on ne les ajoute pas, 
			et on vérifie bien que le nombre de descriptions du criètre à ajouter correspond au nombre de critères de la grille.
			*/
			if(description_n3 != "")
			{ 
				critere.setDescNiveauPerformance3(description_n3);
				i=3;
				if(description_n4 != "")
				{
					critere.setDescNiveauPerformance4(description_n4);
					i=4;
				}
			}
			if(i == grilletmp.getCriteres().size())
			{
				grilletmp.addCritere(critere);
				return grilletmp;
				
			}
			return null;
		}
		
		//FD5 : Obtenir les informations de définition d'un critère d'une grille (via ID du critère ou nom de la grille et ID du critère)
		@Override
		public List <Critere> getAllCritereById(Integer id_grille)
		{
			Grille grille = grilleDAO.findById(id_grille);
			List<Critere> criteres = grille.getCriteres();

			return criteres;
		}
		
		@Override
		public Critere getCritereByGrille(String nom_grille, Integer id_critere)
		{
			if(grilleDAO.findByName(nom_grille).getId() == id_critere) return critereDAO.findById(id_critere);	
			return null;	
		}
		
		//FD6 : Valider une grille d'évaluation
		@Override
		public void validation(String nom_grille)
		{
			grilleDAO.validation(nom_grille);
			return;
		}
		// Supprimer tous les critères existants d'une grille
		@Override
		public void delete(Integer id_grille)
		{
			Grille grille = getById(id_grille);
			for(Critere critere : grille.getCriteres())
			{
				critereDAO.remove(critere);
			}
			grilleDAO.remove(grilleDAO.findById(id_grille));
		}
		
		//FD7 : Créer une grille d'évaluation à partir d'une grille existante
		@Override
		public Grille copy(String nom_g1)
		{
			Grille grille = new Grille();
			Grille tmp = grilleDAO.findByName(nom_g1);
			
			grille.setCriteres(tmp.getCriteres());
			grille.setNiveauPerformance1(tmp.getNiveauPerformance1());
			grille.setNiveauPerformance2(tmp.getNiveauPerformance2());
			grille.setNiveauPerformance3(tmp.getNiveauPerformance3());
			grille.setNiveauPerformance4(tmp.getNiveauPerformance4());
    	    grille.setNom("Grille2");
    	    grille.setValide(false);
    	    
    	    return grille;
		}
}
