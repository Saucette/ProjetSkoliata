package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the objectif_pedagogique database table.
 * 
 */
@Entity
@Table(name="objectif_pedagogique")
@NamedQuery(name="ObjectifPedagogique.findAll", query="SELECT o FROM ObjectifPedagogique o")
public class ObjectifPedagogique implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OBJECTIF_PEDAGOGIQUE_ID_GENERATOR", sequenceName="OBJECTIF_PEDAGOGIQUE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OBJECTIF_PEDAGOGIQUE_ID_GENERATOR")
	private Integer id;

	private String description;

	//bi-directional many-to-one association to AssoOge
	@OneToMany(mappedBy="objectifPedagogique")
	private Set<AssoOge> assoOges;

	public ObjectifPedagogique() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AssoOge> getAssoOges() {
		return this.assoOges;
	}

	public void setAssoOges(Set<AssoOge> assoOges) {
		this.assoOges = assoOges;
	}

	public AssoOge addAssoOge(AssoOge assoOge) {
		getAssoOges().add(assoOge);
		assoOge.setObjectifPedagogique(this);

		return assoOge;
	}

	public AssoOge removeAssoOge(AssoOge assoOge) {
		getAssoOges().remove(assoOge);
		assoOge.setObjectifPedagogique(null);

		return assoOge;
	}

}