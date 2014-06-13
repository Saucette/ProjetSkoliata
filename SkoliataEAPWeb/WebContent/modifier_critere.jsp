<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="front.utils.ServicesLocator,
               service.IServiceGrille,
               service.IServiceCritere,
               model.Grille,
               model.Critere,
               java.util.List"%>
<%
IServiceCritere serviceCritere = (IServiceCritere) ServicesLocator.getInstance().getRemoteInterface("ServiceCritere");
String idCritere = request.getParameter("id");
int id = Integer.parseInt(idCritere);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Skoliata</title>
	<link rel="stylesheet" href="styles.css" type="text/css" />
	</head>
	
	
	<body>
		<label>Critere </label> 
	    <textarea rows="7" cols="70" name="descriptif"></textarea>
	</body>
</html>