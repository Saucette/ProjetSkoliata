package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the droit database table.
 * 
 */
@Embeddable
public class DroitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_enseignant", insertable=false, updatable=false)
	private Integer idEnseignant;

	@Column(name="id_grille", insertable=false, updatable=false)
	private Integer idGrille;

	public DroitPK() {
	}
	public Integer getIdEnseignant() {
		return this.idEnseignant;
	}
	public void setIdEnseignant(Integer idEnseignant) {
		this.idEnseignant = idEnseignant;
	}
	public Integer getIdGrille() {
		return this.idGrille;
	}
	public void setIdGrille(Integer idGrille) {
		this.idGrille = idGrille;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DroitPK)) {
			return false;
		}
		DroitPK castOther = (DroitPK)other;
		return 
			this.idEnseignant.equals(castOther.idEnseignant)
			&& this.idGrille.equals(castOther.idGrille);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idEnseignant.hashCode();
		hash = hash * prime + this.idGrille.hashCode();
		
		return hash;
	}
}