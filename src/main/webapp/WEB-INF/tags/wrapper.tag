<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

<title><c:out value="${pageTitle}" /></title>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<script src="js/helpers.js"></script>
</head>

<body>
	<nav class="light-yellow lighten-1" role="navigation">
		<div class="nav-wrapper container">
			<ul class="right hide-on-med-and-down">
				<li><a href="/currency">Currency</a></li>
				<li><a href="/transaction">Transaction</a></li>
				<li><a href="/client">Client</a></li>
				<li><a href='#'>
						<%
						java.util.Date date = new java.util.Date();
						out.print(date.toString());
						%>
				</a></li>
						<li class="active"><a onclick="sendHTTPDelete('/login')">Logout</a></li>
			</ul>
		</div>
	</nav>


	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<jsp:doBody />
			<!-- Page body will be here -->
		</div>
	</div>
	<!-- Compiled and minified JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>