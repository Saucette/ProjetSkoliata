package service;

import java.util.List;

import javax.ejb.Remote;

import model.Grille;

@Remote
public interface IServiceGrille {

	/** Obtention d'une grille par son nom
	 * 
	 * @param nom 
	 * @return
	 */
	
	Grille getGrille(String nom);
	
	/** Création d'une nouvelle grille
	 * 
	 * @param nb_niv nombre de niveaux de performance pour les différents critères de la grille
	 * @param n1 Description du niveau 1 (obligatoire)
	 * @param n2 Description du niveau 2 (obligatoire)
	 * @param n3 Description du niveau 3 
	 * @param n4 Description du niveau 4
	 * @return
	 */
	
	Grille newGrille(Integer nb_niv, String n1, String n2, String n3, String n4);

	
	/** Obtention de toutes les grilles
	 * @return
	 */
	List<Grille> getAllGrille();

}
