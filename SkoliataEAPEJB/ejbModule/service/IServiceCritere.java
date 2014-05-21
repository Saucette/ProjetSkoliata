package service;

import javax.ejb.Remote;

import model.Critere;

@Remote
public interface IServiceCritere {

	Critere getCritere(Integer id_critere);

	
	
	
}
