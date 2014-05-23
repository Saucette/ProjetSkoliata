package service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.util.List;
import java.util.Set;

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
    public ServiceGrille() {
    }
    
    	// FD1 : Créer une grille d'évaluation
    	@Override
        public Grille newGrille(Integer nb_niv, String n1, String n2, String n3, String n4)
    	{
    	    Grille grille = new Grille();
    	    grille.setNiveauPerformance1(n1);
    	    grille.setNiveauPerformance2(n2);
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

    	
    	@Override 
    	public Grille getById(Integer id)
    	{
    		return grilleDAO.findById(id);
    	}
    	
    	// FD2 : Obtenir les informations de définition d'une grille d'évaluation
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
			// Vérification pour que l'utilisateur rentre le bon nombre de niveaux (critère <---> grille)
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
		
		@Override
		public Set <Critere> getAllCritereById(Integer id_grille)
		{
			Grille grille = grilleDAO.findById(id_grille);
			Set <Critere> criteres = grille.getCriteres();

			return criteres;
		}
		//FD5 : Obtenir les informations de définition d'un critère d'une grille
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
		
		//FD7 : Créer une grille d'évaluation à partir d'une grille existante
		public Grille copy(String nom_g1, String nom_g2)
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
