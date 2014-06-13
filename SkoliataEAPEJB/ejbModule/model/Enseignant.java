package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the enseignant database table.
 * 
 */
@Entity
@NamedQuery(name="Enseignant.findAll", query="SELECT e FROM Enseignant e")
public class Enseignant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ENSEIGNANT_ID_GENERATOR", sequenceName="ENSEIGNANT_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENSEIGNANT_ID_GENERATOR")
	private Integer id;

	@Column(name="adresse_mail")
	private String adresseMail;

	private String nom;

	private String prenom;

	//bi-directional many-to-one association to Asso3e
	@OneToMany(mappedBy="enseignant")
	private List<Asso3e> asso3es;

	//bi-directional many-to-one association to Droit
	@OneToMany(mappedBy="enseignant")
	private List<Droit> droits;

	//bi-directional many-to-many association to Grille
	@ManyToMany(mappedBy="enseignants")
	private List<Grille> grilles;

	public Enseignant() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdresseMail() {
		return this.adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Asso3e> getAsso3es() {
		return this.asso3es;
	}

	public void setAsso3es(List<Asso3e> asso3es) {
		this.asso3es = asso3es;
	}

	public Asso3e addAsso3e(Asso3e asso3e) {
		getAsso3es().add(asso3e);
		asso3e.setEnseignant(this);

		return asso3e;
	}

	public Asso3e removeAsso3e(Asso3e asso3e) {
		getAsso3es().remove(asso3e);
		asso3e.setEnseignant(null);

		return asso3e;
	}

	public List<Droit> getDroits() {
		return this.droits;
	}

	public void setDroits(List<Droit> droits) {
		this.droits = droits;
	}

	public Droit addDroit(Droit droit) {
		getDroits().add(droit);
		droit.setEnseignant(this);

		return droit;
	}

	public Droit removeDroit(Droit droit) {
		getDroits().remove(droit);
		droit.setEnseignant(null);

		return droit;
	}

	public List<Grille> getGrilles() {
		return this.grilles;
	}

	public void setGrilles(List<Grille> grilles) {
		this.grilles = grilles;
	}

}