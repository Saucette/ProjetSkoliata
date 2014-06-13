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
	 * @param jndiName nom JNDI du composant EJB recherch√©
	 * @return le stub du composant EJB correspondant au nom JNDI.
	 * @throws ServiceLocatorException lev√© en cas de probl√®mes li√©s √† la recherche.
	 */
	public Object getRemoteInterface(String nomEJB) throws ServicesLocatorException
	{	
		String nomJNDI = null;
		
		if(nomEJB.equals("ServiceEnseignant"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ServiceEnseignant!service.ServiceEnseignant";

		else if(nomEJB.equals("ServiceEleve"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ServiceEleve!service.ServiceEleve";

		else if(nomEJB.equals("ServiceCritere"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ServiceCritere!service.ServiceCritere";

		else if(nomEJB.equals("ServiceObjectifPedagogique"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ServiceObjectifPedagogiqueDAO!service.ServiceObjectifPedagogique";

		else if(nomEJB.equals("ServiceEnseignement"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ServiceEnseignement!service.ServiceEnseignement";

		else if(nomEJB.equals("ServiceGrille"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ServiceGrille!service.ServiceGrille"; 

		// Pour les contrÙles de DAO par les ÈlËves
		// ========================================
		
		// ATTENTION !!! La rÈcupÈration d'un DAO n'existe ici que
		// pour les contrÙles (utilisÈs dans la servlet ControleDAOServlet) :
		// ils ne sont normalement pas appelÈs par la couche IHM.
		
		else if(nomEJB.equals("CritereDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/CritereDAO";
		
		else if(nomEJB.equals("EleveDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/EleveDAO";
		
		else if(nomEJB.equals("ObjectifPedagogiqueDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/ObjectifPedagogiqueDAO";
		
		else if(nomEJB.equals("EnseignementDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/EnseignementDAO";
		
		else if(nomEJB.equals("GrilleDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/GrilleDAO";
		
		else if(nomEJB.equals("EnseignantDAO"))
			nomJNDI = "java:global/SkoliataEAP/SkoliataEAPEJB/EnseignantDAO";
		
		else
			throw new ServicesLocatorException("Il n'y a pas d'EJB avec ce nom...");
		
		// La m√©thode recherche d'abord le stub dans le cache, s'il est absent,
		// il est r√©cup√©r√© via JNDI.
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
