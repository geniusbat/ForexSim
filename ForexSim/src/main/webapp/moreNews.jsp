<%@page import="pack_forexSim.controller.NoticiasController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>More News</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--Bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <!--CSS-->
    <link rel="stylesheet" type="text/css" href="css/style.css">
<title>News</title>
</head>
<body>
    <h2 class="container-fluid">
        Forex$im
    </h2>
	<h3 style="margin-left: 2%">
		Más noticias
	</h3>
	<div class="container cont-noticias">
		<c:forEach items="${requestScope.noticias}" var="noticias">
	          <div class="articulo noticia">
	              <h3>
	                  <c:out value="${noticias.title}"/>
	              </h3>
	              <p>
	                  <c:out value="${noticias.content}"/>
	              </p>
	              <a href="${noticias.url}"><button>Go to news!</button></a>
	          </div>
		</c:forEach>
	</div>
	<a href="/SetUp"><button style="margin-left: 2%">Go back</button></a>
</body>
</html>