<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               model.Grille,
               model.Critere,
               front.utils.Utils,
               java.util.Set,
               java.util.List"%>

<%
  	String idString = request.getParameter("id");
  	String tmp = new String();
	IServiceGrille serviceGrille = (IServiceGrille) ServicesLocator.getInstance().getRemoteInterface("ServiceGrille");
	List<Grille> grilles = serviceGrille.getAllGrille();
%>


<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Skoliata ~ Informations de la grille </title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>

  <body>
  
		<%
		  if(idString == null)
		  {
		    %>
		    <p class="erreur">Erreur : il n'y a aucune grille qui corresponde à cette recherche.</p>
		    <%
		  }
		  else
		  {
        	int id = Integer.parseInt(idString);
		    Grille grille = serviceGrille.getById(id);
		  	System.out.println("--------------------> grille = " + grille);
		    %>
		    
		    <!-- Affichage des information récupérées --> 
		    <h2>Informations sur la grille : <%=grille.getNom() %></h2>
		    <table id="affichage">
		    <tr>
		        <th style="width: 170px;">ID : GRL <%=grille.getId()%></th>
		        <%if(grille.getValide()) tmp = "Oui"; else tmp = "Non"; %>     
		    </tr>
		    <tr>
		    <th>Valide : <%=tmp%></th> 
		    </tr>
		    <tr>
	        	<th>Niveaux :</th>
	        	<td>
	           	<%=Utils.text2HTML(grille.getNiveauPerformance1())%>
	        	</td>
	        	<td>
	           	<%=Utils.text2HTML(grille.getNiveauPerformance2())%>
	        	</td>
	        	<% if((tmp=grille.getNiveauPerformance3())==null) tmp = "";
		        else tmp=Utils.text2HTML(grille.getNiveauPerformance3());%> 
	        	<td>
	           	<%=tmp%>
	        	</td>
	        	<% if((tmp=grille.getNiveauPerformance4())==null) tmp = "";
		        else tmp=Utils.text2HTML(grille.getNiveauPerformance4());%> 
	        	<td>
	           	<%=tmp%>
	        	</td>	
	         </tr>   
		       <% 
		       Set <Critere> criteres = grille.getCriteres();
		       for(Critere critere : criteres)
		    	{
		       %>
		       <tr>
		        <th>Critère n°<%=critere.getId()%> : <%=critere.getDescription()%></th>
		         <td>
		        	<%=critere.getDescNiveauPerformance1()%>
		        </td>
		        <td>
		        	<%=critere.getDescNiveauPerformance2()%>
		        </td>
		        <td>
		        	<%=critere.getDescNiveauPerformance3()%>
		        </td>
		        <td>
		        	<%=critere.getDescNiveauPerformance4()%>
		        </td>
		       </tr>
		       <%
		  }
		%>  
		    </table>
        <a href="liste_grilles.jsp">Retour à la liste des grilles</a>

		    <%
		  }
		%>
		
  </body>
  
</html>



