package service;

import java.util.List;

import javax.ejb.Remote;

import model.Critere;
import model.Grille;

@Remote
public interface IServiceGrille {

	/** Obtention d'une grille par son nom
	 * @param nom nom de la grille
	 * @return retourne la grille concernée
	 */
	
	Grille getByName(String nom);
	
	/** Création d'une nouvelle grille
	 * 
	 * @param nb_niv nombre de niveaux de performance pour les différents critères de la grille
	 * @param n1 Description du niveau 1 (obligatoire)
	 * @param n2 Description du niveau 2 (obligatoire)
	 * @param n3 Description du niveau 3 
	 * @param n4 Description du niveau 4
	 * @return retourne la grille fraîchement créée
	 */
	
	Grille newGrille(Integer nb_niv, String n1, String n2, String n3, String n4);

	
	/** Obtention de toutes les grilles
	 * @return retourne la liste des grilles existantes
	 */
	
	List<Grille> getAllGrille();

	/** Ajout d'un critère dans une grille
	 * @param nom_grille nom de la grille
	 * @param description_critere description générale de critère
	 * @param description_n1 Description du niveau 1 (obligatoire)
	 * @param description_n2 Description du niveau 2 (obligatoire)
	 * @param description_n3 Description du niveau 3 (obligatoire)
	 * @param description_n4 Description du niveau 4 (obligatoire)
	 * @return retourne la grille concernée
	 */
	
	Grille addCritere(String nom_grille, String description_critere,
			String description_n1, String description_n2,
			String description_n3, String description_n4);

	/** Obtention d'un critère à partir de son id et du nom d'une grille
	 * 
	 * @param nom_grille nom de la grille
	 * @param id_critere id du critère
	 * @return retourne la grille concernée
	 */
	
	Critere getCritereByGrille(String nom_grille, Integer id_critere);

	/** Validation d'une grille
	 * 
	 * @param nom_grille à valider
	 */
	void validation(String nom_grille);

	/** Obtention d'une grille par son id
	 * @param id id de la grille
	 * @return retourne la grille concernée
	 */

	Grille getById(Integer id);

	/** Obtention du Set de criteres d'une grille
	 * 
	 * @param id_grille id de la grille
	 * @return
	 */
	List <Critere> getAllCritereById(Integer id_grille);

	/** Suppression d'une grille
	 * 
	 * @param id_grille id de la grille
	 */
	
	void delete(Integer id_grille);
	
	/** Copie d'une grille existante
	 * 
	 * @param nom_g1
	 * @return
	 */
	
	Grille copy(String nom_g1);
}
