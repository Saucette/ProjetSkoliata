package front.controlesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import dao.*;
import front.utils.*;
import model.*;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ControlesDAO")
public class ControlesDAOServlet extends HttpServlet
{
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlesDAOServlet()
	{
		super();
	}
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
	// Flot de sortie pour écriture des résultats.
    PrintWriter out = response.getWriter();
    
    // Récupération de la référence vers le(s) DAO(s)
		EleveDAO eleveDAO = null;
    try
    {
	    eleveDAO = (EleveDAO) ServicesLocator.getInstance().getRemoteInterface("EleveDAO");
    }
    catch (ServicesLocatorException e)
    {
    	e.printStackTrace();
    }
    out.println("----------------------------------------------------------------------------");
		
    out.println("Contrôles de fonctionnement du DAO EleveDAO");
		
    out.println();
		
		// Contrôle(s) de fonctionnalités
		out.println("Liste des eleves :");
		List<Eleve> eleves = eleveDAO.findAll();
		
		for(Eleve eleve : eleves)
		{
			out.println(eleve.getNom());
		}
		out.println();
		
		out.println("Obtention de l'eleve n° 1 :");
		Eleve e = eleveDAO.findById(1);
		out.println(e.getId());
		out.println(e.getNom());
		out.println(e.getPrenom());
		out.println(e.getAdresseMail());
		out.println(e.getAsso3es());
		out.println();

		
		out.println("Obtention de l'eleve n° 2 :");
		e = eleveDAO.findById(2);
		out.println(e.getId());
		out.println(e.getNom());
		out.println(e.getPrenom());
		out.println(e.getAdresseMail());
		out.println(e.getAsso3es());
		out.println();

		out.println("Obtention de l'eleve n° 3 :");
		e = eleveDAO.findById(3);
		out.println(e.getId());
		out.println(e.getNom());
		out.println(e.getPrenom());
		out.println(e.getAdresseMail());
		out.println(e.getAsso3es());
		out.println();

		
	}
}
