package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the eleve database table.
 * 
 */
@Entity
@NamedQuery(name="Eleve.findAll", query="SELECT e FROM Eleve e")
public class Eleve implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ELEVE_ID_GENERATOR", sequenceName="ELEVE_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ELEVE_ID_GENERATOR")
	private Integer id;

	@Column(name="adresse_mail")
	private String adresseMail;

	private String nom;

	private String prenom;

	//bi-directional many-to-one association to Asso3e
	@OneToMany(mappedBy="eleve")
	private Set<Asso3e> asso3es;

	public Eleve() {
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
		asso3e.setEleve(this);

		return asso3e;
	}

	public Asso3e removeAsso3e(Asso3e asso3e) {
		getAsso3es().remove(asso3e);
		asso3e.setEleve(null);

		return asso3e;
	}

}