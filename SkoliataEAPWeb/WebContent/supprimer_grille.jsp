<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               service.IServiceCritere,
               model.Grille,
               java.util.List"%>

<%
	String idString = request.getParameter("id");
	int id = Integer.parseInt(idString);
	IServiceGrille serviceGrille = (IServiceGrille) ServicesLocator.getInstance().getRemoteInterface("ServiceGrille");
	IServiceCritere serviceCritere = (IServiceCritere) ServicesLocator.getInstance().getRemoteInterface("ServiceCritere");
	
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Skoliata</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
<%
serviceGrille.delete(id);
%>
<h2> Votre grille a �t� supprim�e </h2>
<a href="liste_grilles.jsp">Retour � la liste des grilles</a>
</body>
</html>