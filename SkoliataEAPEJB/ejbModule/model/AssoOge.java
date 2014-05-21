package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asso_oge database table.
 * 
 */
@Entity
@Table(name="asso_oge")
@NamedQuery(name="AssoOge.findAll", query="SELECT a FROM AssoOge a")
public class AssoOge implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssoOgePK id;

	//bi-directional many-to-one association to Enseignement
	@ManyToOne
	@JoinColumn(name="id_enseignement")
	private Enseignement enseignement;

	//bi-directional many-to-one association to Grille
	@ManyToOne
	@JoinColumn(name="id_grille")
	private Grille grille;

	//bi-directional many-to-one association to ObjectifPedagogique
	@ManyToOne
	@JoinColumn(name="id_op")
	private ObjectifPedagogique objectifPedagogique;

	public AssoOge() {
	}

	public AssoOgePK getId() {
		return this.id;
	}

	public void setId(AssoOgePK id) {
		this.id = id;
	}

	public Enseignement getEnseignement() {
		return this.enseignement;
	}

	public void setEnseignement(Enseignement enseignement) {
		this.enseignement = enseignement;
	}

	public Grille getGrille() {
		return this.grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public ObjectifPedagogique getObjectifPedagogique() {
		return this.objectifPedagogique;
	}

	public void setObjectifPedagogique(ObjectifPedagogique objectifPedagogique) {
		this.objectifPedagogique = objectifPedagogique;
	}

}