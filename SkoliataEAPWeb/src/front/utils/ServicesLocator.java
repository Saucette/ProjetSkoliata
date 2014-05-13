package front.utils;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServicesLocator
{
	//-----------------------------------------------------------------------------
	private Context initialContext;
	private Map<String, Object> cache;
	// Singleton
	private static final ServicesLocator instance = new ServicesLocator();
	//-----------------------------------------------------------------------------
	private ServicesLocator()
	{
    try
    {
	    initialContext = new InitialContext();
			cache = new HashMap<String, Object>();
    }
    catch (NamingException e)
    {
	    e.printStackTrace();
    }
	}
	//-----------------------------------------------------------------------------
	public static ServicesLocator getInstance()
	{
		return instance;
	}
	//-----------------------------------------------------------------------------
	/**
	 * Renvoie le stub du composant EJB.
	 * 
	 * @param jndiName nom JNDI du composant EJB recherché
	 * @return le stub du composant EJB correspondant au nom JNDI.
	 * @throws ServiceLocatorException levé en cas de problèmes liés à la recherche.
	 */
	public Object getRemoteInterface(String nomEJB) throws ServicesLocatorException
	{
		// Nom JNDI de la forme :
		//   java:global/<nom projet EAR>/<nom sous-projet EJB>/<nom bean session EJB>!<nom complet avec package de l'interface remote du bean>
		// Exemple :
		//   java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceEntreprise!eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise
		
		String nomJNDI = null;
		
		if(nomEJB.equals("ServiceEntreprise"))
			nomJNDI = "java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceEntreprise!eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise";

		else if(nomEJB.equals("ServiceOffreEmploi"))
			nomJNDI = "java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceOffreEmploi!eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi";

		else if(nomEJB.equals("ServiceIndexation"))
			nomJNDI = "java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceIndexation!eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation";

		else if(nomEJB.equals("ServiceCandidature"))
			nomJNDI = "java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceCandidature!eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature";

		else if(nomEJB.equals("ServiceMessageCandidature"))
			nomJNDI = "java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceMessageCandidature!eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageCandidature";

		else if(nomEJB.equals("ServiceMessageOffreEmploi"))
			nomJNDI = "java:global/CabinetRecrutement_EAR/CabinetRecrutement_EJB/ServiceMessageOffreEmploi!eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageOffreEmploi";

		// Pour les contrôles de DAO par les élèves
		// ========================================
		
		// ATTENTION !!! La récupération d'un DAO n'existe ici que
		// pour les contrôles (utilisés dans la servlet ControleDAOServlet) :
		// ils ne sont normalement pas appelés par la couche IHM.
		
		else if(nomEJB.equals("CritereDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/CritereDAO";
		
		else if(nomEJB.equals("EleveDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/EleveDAO";
		
		else if(nomEJB.equals("ObjectifPedagogiqueDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ObjectifPedagogiqueDAO";
		
		else if(nomEJB.equals("EnseignementDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/EnseignementDAO";
		
		else if(nomEJB.equals("GrilleDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/GrilleDAO!dao.GrilleDAO";
		
		else if(nomEJB.equals("EnseignantDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/EnseignantDAO";
		
		else
			throw new ServicesLocatorException("Il n'y a pas d'EJB avec ce nom...");
		
		// La méthode recherche d'abord le stub dans le cache, s'il est absent,
		// il est récupéré via JNDI.
		Object remoteInterface = cache.get(nomJNDI);
		if (remoteInterface == null)
		{
			try
			{
				remoteInterface = initialContext.lookup(nomJNDI);
				cache.put(nomJNDI, remoteInterface);
			}
			catch (Exception e)
			{
				throw new ServicesLocatorException(e);
			}
		}
		return remoteInterface;
	}
	//-----------------------------------------------------------------------------
}
