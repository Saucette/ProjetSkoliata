package service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import java.util.List;
import java.util.UUID;

import model.*;
import dao.GrilleDAO;

/**
 * Session Bean implementation class ServiceGrille
 */
@Stateless
@LocalBean
public class ServiceGrille implements IServiceGrille {

	@EJB private GrilleDAO grilleDAO;
//	@EJB private NiveauQualificationDAO  niveauDAO;
//	@EJB private CandidatureDAO  candidatureDAO;
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
    	    grille.setNom("Exemple_grille");
    	    // Insertion dans la table DROITS pour la notion de propriétaire
    	    // Etat non validé ? Attribut de l'objet grille, ou bien :  processus de création de la grille = non validé / grille complétée = validé
    	    if(nb_niv>2)
    	    {
    	    	grille.setNiveauPerformance3(n3);
    	    	grille.setNiveauPerformance4(n4);
    	    }
    	    return grilleDAO.persist(grille);
    	   // Idée pour l'auto génération ? : grille.setNom("grille"+grille.getId());
    	    
        }

    	// FD2 : Obtenir les informations de définition d'une grille d'évaluation
		@Override
		public Grille getGrille(String nom) 
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
		public Grille addCritere(String nom_grille, String description_critere, String description_n1,String description_n2,String description_n3,String description_n4)
		{
			Integer i = 0;
			Grille grilletmp = grilleDAO.findByName(nom_grille);
			Critere critere = new Critere();
			critere.setDescription(description_critere);
			critere.setDescNiveauPerformance1(description_n1);
			critere.setDescNiveauPerformance2(description_n2);
			i=2;
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
			if(i== grilletmp.getCriteres().size())
			{
				grilletmp.addCritere(critere);
				//grilleDAO.persist(grille) ???
				return grilletmp;
				
			}
			return null;
		}
}
