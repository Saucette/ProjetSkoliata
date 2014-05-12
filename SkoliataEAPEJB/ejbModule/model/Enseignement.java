package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the enseignement database table.
 * 
 */
@Entity
@NamedQuery(name="Enseignement.findAll", query="SELECT e FROM Enseignement e")
public class Enseignement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ENSEIGNEMENT_ID_GENERATOR", sequenceName="ENSEIGNEMENT_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENSEIGNEMENT_ID_GENERATOR")
	private Integer id;

	private String nom;

	//bi-directional many-to-one association to Asso3e
	@OneToMany(mappedBy="enseignement")
	private Set<Asso3e> asso3es;

	//bi-directional many-to-one association to AssoOge
	@OneToMany(mappedBy="enseignement")
	private Set<AssoOge> assoOges;

	public Enseignement() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Asso3e> getAsso3es() {
		return this.asso3es;
	}

	public void setAsso3es(Set<Asso3e> asso3es) {
		this.asso3es = asso3es;
	}

	public Asso3e addAsso3e(Asso3e asso3e) {
		getAsso3es().add(asso3e);
		asso3e.setEnseignement(this);

		return asso3e;
	}

	public Asso3e removeAsso3e(Asso3e asso3e) {
		getAsso3es().remove(asso3e);
		asso3e.setEnseignement(null);

		return asso3e;
	}

	public Set<AssoOge> getAssoOges() {
		return this.assoOges;
	}

	public void setAssoOges(Set<AssoOge> assoOges) {
		this.assoOges = assoOges;
	}

	public AssoOge addAssoOge(AssoOge assoOge) {
		getAssoOges().add(assoOge);
		assoOge.setEnseignement(this);

		return assoOge;
	}

	public AssoOge removeAssoOge(AssoOge assoOge) {
		getAssoOges().remove(assoOge);
		assoOge.setEnseignement(null);

		return assoOge;
	}

}