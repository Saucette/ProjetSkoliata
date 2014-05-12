package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the asso_oge database table.
 * 
 */
@Embeddable
public class AssoOgePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_grille", insertable=false, updatable=false)
	private Integer idGrille;

	@Column(name="id_enseignement", insertable=false, updatable=false)
	private Integer idEnseignement;

	@Column(name="id_op", insertable=false, updatable=false)
	private Integer idOp;

	public AssoOgePK() {
	}
	public Integer getIdGrille() {
		return this.idGrille;
	}
	public void setIdGrille(Integer idGrille) {
		this.idGrille = idGrille;
	}
	public Integer getIdEnseignement() {
		return this.idEnseignement;
	}
	public void setIdEnseignement(Integer idEnseignement) {
		this.idEnseignement = idEnseignement;
	}
	public Integer getIdOp() {
		return this.idOp;
	}
	public void setIdOp(Integer idOp) {
		this.idOp = idOp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssoOgePK)) {
			return false;
		}
		AssoOgePK castOther = (AssoOgePK)other;
		return 
			this.idGrille.equals(castOther.idGrille)
			&& this.idEnseignement.equals(castOther.idEnseignement)
			&& this.idOp.equals(castOther.idOp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGrille.hashCode();
		hash = hash * prime + this.idEnseignement.hashCode();
		hash = hash * prime + this.idOp.hashCode();
		
		return hash;
	}
}