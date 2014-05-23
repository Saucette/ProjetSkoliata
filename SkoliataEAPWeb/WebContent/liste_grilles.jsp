<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               model.Grille,
               java.util.List"%>

<%
	IServiceGrille serviceGrille = (IServiceGrille) ServicesLocator.getInstance().getRemoteInterface("ServiceGrille");
	List<Grille> grilles = serviceGrille.getAllGrille();
	String tmp= "";
%>

<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Skoliata ~ Liste des grilles</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>

  <body>
  
		<h2>Liste des grilles référencées :</h2>

		<table id="affichage">
		  <tr>
		    <th>Identifiant</th>
		    <th>Nom</th>
		    <th>Validée</th>
		    <th>Niveau 1</th>
		    <th>Niveau 2</th>
		    <th>Niveau 3</th>
		    <th>Niveau 4</th>
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
