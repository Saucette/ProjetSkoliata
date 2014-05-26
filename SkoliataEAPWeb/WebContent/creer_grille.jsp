<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               model.Grille,
               java.util.List"%>

<%
	IServiceGrille serviceGrille = (IServiceGrille) ServicesLocator.getInstance().getRemoteInterface("ServiceGrille");
	String nb_niv = request.getParameter("nb_niv");
	int tmp = 0;
%>

<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Skoliata ~ Création d'une grille</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
<body>
	<%
	if(nb_niv == null) // Pas de paramètre "nb_niv" => affichage du formulaire
	{
		%>
        <form method="get" action="creer_grille.jsp">
            <fieldset>
                <legend>Création d'une nouvelle grille</legend>
                <p>Vous pouvez vous créer votre grille via ce formulaire.</p>

                <label for="nombre_niveaux">Nombre de niveaux de performance pour les différents critères de la grille :</label>
					<br>
					<input type="radio" name="nb_niv" value="2">2 niveaux<br>
					<input type="radio" name="nb_niv" value="3">3 niveaux<br>
					<input type="radio" name="nb_niv" value="4">4 niveaux<br>
					
				 
                <input type="submit" value="Créer la grille" class="sansLabel"/>
                <!-- <input type="submit" value="Annuler" class="sansLabel" /> -->
                <input type="button" value="Annuler" onclick="location.href='index.html'" />
                
                <br />
            </fieldset>
        </form>
        <%
	  	}
	  	else
       	{
	  		tmp = Integer.parseInt(request.getParameter("nb_niv"));
	        if(tmp <= 4 && tmp >= 2)
	        	{
	        		Grille grille = serviceGrille.newGrille(tmp, "", "", "", "");
	        	}
	        else
	        {
        %>    
          		<p class="erreur">Impossible de référencer une grille sans saisir un nombre de niveaux compris entre 2 et 4</p>
 		 <%		
 			 }
	      }
	      %>
    </body>
</html>