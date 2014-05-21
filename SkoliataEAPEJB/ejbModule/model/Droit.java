package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the droit database table.
 * 
 */
@Entity
@NamedQuery(name="Droit.findAll", query="SELECT d FROM Droit d")
public class Droit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DroitPK id;

	private String description;

	//bi-directional many-to-one association to Enseignant
	@ManyToOne
	@JoinColumn(name="id_enseignant")
	private Enseignant enseignant;

	//bi-directional many-to-one association to Grille
	@ManyToOne
	@JoinColumn(name="id_grille")
	private Grille grille;

	public Droit() {
	}

	public DroitPK getId() {
		return this.id;
	}

	public void setId(DroitPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Grille getGrille() {
		return this.grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

}