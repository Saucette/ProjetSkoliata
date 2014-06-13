<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               model.Grille,
               java.util.List"%>
<!-- 
TODO : 

- creer_grille.jsp vers liste_grilles.jsp, problème de synchronisation (on doit recharger la page pour voir apparaître la grille fraîchement créée).
Cela doit venir de l'organisation du code HTML/JSP, on peut envisager de forcer le rechargement de la page via du Javascript.

- supprimer_grille.jsp : Problème au niveau du DAO. 
Si la grille à supprimer est associée à la table ASSO_OGE, on a une erreur sur le "delete cascade". Il n'existe pas de DAO pour la table ASSO_OGE (3 PK).
Je pense qu'il faut faire une longue requête PostgreSQL pour y remédier, une ébauche est dans GrilleDAO (fonction 'delete').

- couche REST à éventuellement rajouter pour un portage Androïd 

- Bootstrap pour une IHM plus jolie

- Documents de conception, manuel d'utilisation, d'installation à produire

- Fonctionnalités manquantes du CdC (quelques FD et le reste)

 -->
<%
	IServiceGrille serviceGrille = (IServiceGrille) ServicesLocator.getInstance().getRemoteInterface("ServiceGrille");
	List<Grille> grilles = serviceGrille.getAllGrille();
	String tmp= "";
	%>
	<%
	String niv1 = (request.getParameter("n1")==null) ? "" : request.getParameter("n1");
	String niv2 = (request.getParameter("n2")==null) ? "" : request.getParameter("n2");
	String niv3 = (request.getParameter("n3")==null) ? "" : request.getParameter("n3");
	String niv4 = (request.getParameter("n4")==null) ? "" : request.getParameter("n4");
	
	if(!niv1.equals("") && !niv2.equals(""))
	{
		if(!niv3.equals("") && niv4.equals(""))
		{
			serviceGrille.newGrille(3, niv1, niv2, niv3, "");
			
		}
		
		if(!niv3.equals("") && !niv4.equals(""))
		{
			serviceGrille.newGrille(2, niv1, niv2, "", "");
		}
		
		if(niv3.equals("") && niv4.equals(""))
		{
			serviceGrille.newGrille(4, niv1, niv2, niv3, niv4);
		}
	}
%>

<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Skoliata</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>

  <body>
  
		<h2>Liste des grilles référencées :</h2>

		<table id="affichage">
		  <tr>
		    <th>Identifiant</th>
		    <th>Nom</th>
		    <th>Validée</th>
		    <th>Niveau 1 </th>
		    <th>Niveau 2 </th>
		    <th>Niveau 3 </th>
		    <th>Niveau 4 </th>
		  </tr>
		  <%
		  for(Grille grille : grilles)
		  {
		    %>
		    <tr>
		     <td>GRL_<%=grille.getId()%></td>
		     <td><a href="info_grille.jsp?id=<%=grille.getId()%>"><%=grille.getNom()%></a></td>
		     <%if(grille.getValide()) tmp = "Oui"; else tmp = "Non"; %>
		     <td><%=tmp%>
		     <td><%=grille.getNiveauPerformance1() %></td>
		     <td><%=grille.getNiveauPerformance2() %></td>
		     <%if((tmp=grille.getNiveauPerformance3())==null) tmp="";%>
		     <td><%=tmp%></td>
		    <%if((tmp=grille.getNiveauPerformance4())==null) tmp="";%>
		     <td><%=tmp%></td>
		    </tr>
		    <%
		  }
		  %>
		</table>

    <a href="index.html">Retour au menu</a>

  </body>
  
</html>
