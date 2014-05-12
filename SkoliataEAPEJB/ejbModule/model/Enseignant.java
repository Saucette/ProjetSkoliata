package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


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
	private Set<Asso3e> asso3es;

	//bi-directional many-to-one association to Grille
	@OneToMany(mappedBy="enseignant")
	private Set<Grille> grilles;

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

	public Set<Asso3e> getAsso3es() {
		return this.asso3es;
	}

	public void setAsso3es(Set<Asso3e> asso3es) {
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

	public Set<Grille> getGrilles() {
		return this.grilles;
	}

	public void setGrilles(Set<Grille> grilles) {
		this.grilles = grilles;
	}

	public Grille addGrille(Grille grille) {
		getGrilles().add(grille);
		grille.setEnseignant(this);

		return grille;
	}

	public Grille removeGrille(Grille grille) {
		getGrilles().remove(grille);
		grille.setEnseignant(null);

		return grille;
	}

}