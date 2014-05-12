package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the asso_3e database table.
 * 
 */
@Embeddable
public class Asso3ePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_enseignant", insertable=false, updatable=false)
	private Integer idEnseignant;

	@Column(name="id_enseignement", insertable=false, updatable=false)
	private Integer idEnseignement;

	@Column(name="id_eleve", insertable=false, updatable=false)
	private Integer idEleve;

	public Asso3ePK() {
	}
	public Integer getIdEnseignant() {
		return this.idEnseignant;
	}
	public void setIdEnseignant(Integer idEnseignant) {
		this.idEnseignant = idEnseignant;
	}
	public Integer getIdEnseignement() {
		return this.idEnseignement;
	}
	public void setIdEnseignement(Integer idEnseignement) {
		this.idEnseignement = idEnseignement;
	}
	public Integer getIdEleve() {
		return this.idEleve;
	}
	public void setIdEleve(Integer idEleve) {
		this.idEleve = idEleve;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Asso3ePK)) {
			return false;
		}
		Asso3ePK castOther = (Asso3ePK)other;
		return 
			this.idEnseignant.equals(castOther.idEnseignant)
			&& this.idEnseignement.equals(castOther.idEnseignement)
			&& this.idEleve.equals(castOther.idEleve);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEnseignant.hashCode();
		hash = hash * prime + this.idEnseignement.hashCode();
		hash = hash * prime + this.idEleve.hashCode();
		
		return hash;
	}
}