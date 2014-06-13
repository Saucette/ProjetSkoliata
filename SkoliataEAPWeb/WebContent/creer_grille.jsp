<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               model.Grille,
               java.util.List"%>

<% 
	IServiceGrille serviceGrille = (IServiceGrille) ServicesLocator.getInstance().getRemoteInterface("ServiceGrille");
	String description = request.getParameter("desc");
	%>
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Skoliata</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
 	</head>	
	<body>
	<h2>Veuillez entrer au moins deux niveaux pour la grille</h2>
    <FORM ACTION="liste_grilles.jsp" METHOD="POST">
	    <label>Description des niveaux</label>  <br>
	    <label>Niveau 1</label> 
	    <input type="text" name="n1" required title="Ce champ est requis">*<br>
	    <label>Niveau 2</label> 
	    <input type="text" name="n2" required title="Ce champ est requis">*<br>
	    <label>Niveau 3</label> 
	    <input type="text" name="n3"><br>
	    <label>Niveau 4</label>
    	<input type="text" name="n4"><br>
    	<input type="submit" value="Ajouter"/>
    	<a href="index.html"> <input type="button" value="Annuler"></a>
    </FORM>
   
    </body>
</html>