package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grille database table.
 * 
 */
@Entity
@NamedQuery(name="Grille.findAll", query="SELECT g FROM Grille g")
public class Grille implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GRILLE_ID_GENERATOR", sequenceName="GRILLE_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRILLE_ID_GENERATOR")
	private Integer id;

	@Column(name="niveau_performance_1")
	private String niveauPerformance1;

	@Column(name="niveau_performance_2")
	private String niveauPerformance2;

	@Column(name="niveau_performance_3")
	private String niveauPerformance3;

	@Column(name="niveau_performance_4")
	private String niveauPerformance4;

	private String nom;

	private Boolean valide;

	//bi-directional many-to-one association to AssoOge
	@OneToMany(mappedBy="grille")
	private List<AssoOge> assoOges;

	//bi-directional many-to-one association to Critere
	@OneToMany(mappedBy="grille")
	private List<Critere> criteres;

	//bi-directional many-to-one association to Droit
	@OneToMany(mappedBy="grille")
	private List<Droit> droits;

	//bi-directional many-to-many association to Enseignant
	@ManyToMany
	@JoinTable(
		name="droit"
		, joinColumns={
			@JoinColumn(name="id_grille")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_enseignant")
			}
		)
	private List<Enseignant> enseignants;

	public Grille() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNiveauPerformance1() {
		return this.niveauPerformance1;
	}

	public void setNiveauPerformance1(String niveauPerformance1) {
		this.niveauPerformance1 = niveauPerformance1;
	}

	public String getNiveauPerformance2() {
		return this.niveauPerformance2;
	}

	public void setNiveauPerformance2(String niveauPerformance2) {
		this.niveauPerformance2 = niveauPerformance2;
	}

	public String getNiveauPerformance3() {
		return this.niveauPerformance3;
	}

	public void setNiveauPerformance3(String niveauPerformance3) {
		this.niveauPerformance3 = niveauPerformance3;
	}

	public String getNiveauPerformance4() {
		return this.niveauPerformance4;
	}

	public void setNiveauPerformance4(String niveauPerformance4) {
		this.niveauPerformance4 = niveauPerformance4;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getValide() {
		return this.valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	public List<AssoOge> getAssoOges() {
		return this.assoOges;
	}

	public void setAssoOges(List<AssoOge> assoOges) {
		this.assoOges = assoOges;
	}

	public AssoOge addAssoOge(AssoOge assoOge) {
		getAssoOges().add(assoOge);
		assoOge.setGrille(this);

		return assoOge;
	}

	public AssoOge removeAssoOge(AssoOge assoOge) {
		getAssoOges().remove(assoOge);
		assoOge.setGrille(null);

		return assoOge;
	}

	public List<Critere> getCriteres() {
		return this.criteres;
	}

	public void setCriteres(List<Critere> criteres) {
		this.criteres = criteres;
	}

	public Critere addCritere(Critere critere) {
		getCriteres().add(critere);
		critere.setGrille(this);

		return critere;
	}

	public Critere removeCritere(Critere critere) {
		getCriteres().remove(critere);
		critere.setGrille(null);

		return critere;
	}

	public List<Droit> getDroits() {
		return this.droits;
	}

	public void setDroits(List<Droit> droits) {
		this.droits = droits;
	}

	public Droit addDroit(Droit droit) {
		getDroits().add(droit);
		droit.setGrille(this);

		return droit;
	}

	public Droit removeDroit(Droit droit) {
		getDroits().remove(droit);
		droit.setGrille(null);

		return droit;
	}

	public List<Enseignant> getEnseignants() {
		return this.enseignants;
	}

	public void setEnseignants(List<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}

}