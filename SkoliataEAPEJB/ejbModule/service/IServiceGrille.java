package service;

import java.util.List;

import javax.ejb.Remote;

import model.Critere;
import model.Grille;

@Remote
public interface IServiceGrille {

	/** Obtention d'une grille par son nom
	 * @param nom nom de la grille
	 * @return retourne la grille concern�e
	 */
	
	Grille getGrille(String nom);
	
	/** Cr�ation d'une nouvelle grille
	 * 
	 * @param nb_niv nombre de niveaux de performance pour les diff�rents crit�res de la grille
	 * @param n1 Description du niveau 1 (obligatoire)
	 * @param n2 Description du niveau 2 (obligatoire)
	 * @param n3 Description du niveau 3 
	 * @param n4 Description du niveau 4
	 * @return retourne la grille fra�chement cr��e
	 */
	
	Grille newGrille(Integer nb_niv, String n1, String n2, String n3, String n4);

	
	/** Obtention de toutes les grilles
	 * @return retourne la liste des grilles existantes
	 */
	
	List<Grille> getAllGrille();

	/** Ajout d'un crit�re dans une grille
	 * @param nom_grille nom de la grille
	 * @param description_critere description g�n�rale de crit�re
	 * @param description_n1 Description du niveau 1 (obligatoire)
	 * @param description_n2 Description du niveau 2 (obligatoire)
	 * @param description_n3 Description du niveau 3 (obligatoire)
	 * @param description_n4 Description du niveau 4 (obligatoire)
	 * @return retourne la grille concern�e
	 */
	
	Grille addCritere(String nom_grille, String description_critere,
			String description_n1, String description_n2,
			String description_n3, String description_n4);

	/** Obtention d'un crit�re d'une grille
	 * 
	 * @param nom_grille nom de la grille
	 * @param id_critere id du crit�re
	 * @return retourne la grille concern�e
	 */
	
	Critere getCritereByGrille(String nom_grille, Integer id_critere);

	/** Passage de l'�tat de la grille � "valide"
	 * 
	 * @param nom_grille � valider
	 */
	void validation(String nom_grille);

}
