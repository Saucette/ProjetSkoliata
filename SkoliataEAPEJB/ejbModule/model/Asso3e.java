package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asso_3e database table.
 * 
 */
@Entity
@Table(name="asso_3e")
@NamedQuery(name="Asso3e.findAll", query="SELECT a FROM Asso3e a")
public class Asso3e implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Asso3ePK id;

	//bi-directional many-to-one association to Eleve
	@ManyToOne
	@JoinColumn(name="id_eleve")
	private Eleve eleve;

	//bi-directional many-to-one association to Enseignant
	@ManyToOne
	@JoinColumn(name="id_enseignant")
	private Enseignant enseignant;

	//bi-directional many-to-one association to Enseignement
	@ManyToOne
	@JoinColumn(name="id_enseignement")
	private Enseignement enseignement;

	public Asso3e() {
	}

	public Asso3ePK getId() {
		return this.id;
	}

	public void setId(Asso3ePK id) {
		this.id = id;
	}

	public Eleve getEleve() {
		return this.eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Enseignement getEnseignement() {
		return this.enseignement;
	}

	public void setEnseignement(Enseignement enseignement) {
		this.enseignement = enseignement;
	}

}