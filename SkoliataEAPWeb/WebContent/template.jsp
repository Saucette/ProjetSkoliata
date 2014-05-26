<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/2000/REC-xhtml1-20000126/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
	  <title>Skoliata</title>
		<link rel="stylesheet" href="styles.css" type="text/css" />
	</head>

	<body>
	  <table style="border-collapse: collapse; width: 100%; border: 1px;">
	    <tr>
        <td  style="vertical-align: middle; font-size: 18px; color: #564b47; font-weight: bolder;">Skoliata</td>
        <td align="right"><img src="images/loupe.png" alt="" width="70" border="0" /></td>
	    </tr>
	  </table>
	  <div id="content">
	    <%
	      String action = request.getParameter("action");
	      if(action == null)
	      {
	      	%>
	      	<h2>Bienvenue</h2>
          <p>Système d'information "Skoliata"</p>
          <p>Choisir une action dans le menu.</p>
	      	<%
	      }
	      else
	      {
	      	try
	      	{
	          %>
	          <jsp:include page="<%=action + ".jsp"%>" />
	          <%
	      	}
	      	catch(java.io.FileNotFoundException fnfe)
	      	{
            %>
            <h2>Fonctionnalité inexistante</h2>
            <%
	      	}
	      }
	    %>
	  </div>

	</body>

</html>
