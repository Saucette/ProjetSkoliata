<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               service.IServiceCritere,
               model.Grille,
               model.Critere,
               java.util.List"%>

<%
	String idString = request.getParameter("id");
	IServiceGrille serviceGrille = (IServiceGrille) ServicesLocator.getInstance().getRemoteInterface("ServiceGrille");
	IServiceCritere serviceCritere = (IServiceCritere) ServicesLocator.getInstance().getRemoteInterface("ServiceCritere");
	String nb_niv = request.getParameter("nb_niv");
	int id = Integer.parseInt(idString);
	Grille grille = serviceGrille.getById(id);
	List<Critere>criteres = grille.getCriteres();  
	int tmp = 0;
	int cpt=1;
%>
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Skoliata</title>
		<link rel="stylesheet" href="styles.css" type="text/css" />
		</head>
 
 		<body>

<form action="ajout_critere.jsp" method="get">
      <input type="hidden" name="action" value="nouveau_critere" />
	  	<table id="affichage">
	  	  <tr>
          <th style="width: 170px;">Description du critère:</th>
	        <td>
	          <input type="text" name="nom" size="20" maxlength="50">
	        </td>
	  	  </tr>
	      <tr>
	    <%
	      
	      for(Critere c : criteres)
	    	  {
	    	  %> 
         <th>Description du niveau <%=cpt++%> :</th>
	        <td>
	          <textarea rows="7" cols="70" name="descriptif"></textarea>
	        </td>
	      </tr>
	      <%} %>
	  	</table>
		  <p>
		    <input type="submit" value="Enregistrer"/>
		   <a href="liste_grilles.jsp"> <input type="button" value="Annuler"></a>
		  </p>
		</form>
		

  
  
  </body>
</html>