package service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.*;
import dao.*;

/**
 * Session Bean implementation class ServiceCritere
 */
@Stateless
@LocalBean
public class ServiceCritere implements IServiceCritere {

	@EJB private CritereDAO critereDAO;
    /**
     * Default constructor. 
     */
    public ServiceCritere() {}
       
    	@Override
    	public Critere getCritere(Integer id_critere)
    	{
    		return critereDAO.findById(id_critere);
    	}
    }


