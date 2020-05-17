<%@page import="pack_forexSim.controller.NoticiasController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>ForexSim</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--Bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <!-- Plotly -->
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
    <!--CSS-->
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body onLoad="setUp()">
    <h2 class="container-fluid">
        Forex$im
    </h2>
    <!-- Usuario -->
    <div class="row" style="margin-left:2%;">
    	Usuario:
    </div>
    <!-- Searcher -->
    <div class="row" style="margin-left:2%;">
		<form id="searchForm" action="SearchController" method="post">
			<input type="text" name="searchQuery" id="searchQuery" required/> 
			<input  type="submit" value="Search">
		</form>
		<button onClick="getGraph()"> Reload Graph</button>
    </div>
    <div id="searchRes"><p>Look for a product first</p></div>
    <script>
    	function setUp() {
    		if ("<c:out value='${requestScope.datesAsString}'/>"!="") { //Printea la gráfica automáticamente
    			getGraph();
    		}
    	}
    	function getGraph() {
    		var producto= document.getElementById('searchQuery');
    		document.getElementById("searchRes").innerHTML='<c:out value="${requestScope.producto}"/>';
    		var tra1 = [<c:out value="${requestScope.pricesAsString}"/>];
    		var tra2 = "<c:out value='${requestScope.datesAsString}'/>";
    		var a = [...tra2.split(',')];
			var trace = {
					  y: tra1,
					  x: a,
					  type: 'scatter'
					};
			this.graphShow(trace);
    	}
    </script>
    <script src="/js/graphShow.js"></script>
    <!-- Content -->
    <div class="row">
    	<!-- Graph --> 
        <div id="graph" class="col-6"></div>
		<!-- Noticias -->
		<div class="col-6 cubo noticiasDiv">
			<c:forEach items="${requestScope.noticias}" var="noticias">
	            <div class="articulo">
	                <h3>
	                    <c:out value="${noticias.title}"/>
	                </h3>
	                <p>
	                    <c:out value="${noticias.content}"/>
	                </p>
	                <a href="${noticias.url}"><button>Go to news!</button></a>
	            </div>
			</c:forEach>
			<a href="/NoticiasController"><button style="margin-top:1%">-->More news!</button></a>
		</div>
    </div>		
    <!-- Formulario de compra/venta -->
	<form name="buyProducto" action="/TelegramBuyController" onsubmit="sendMessageTelegram()">
		<label for="quantity">Quantity:</label>
		<input type="number" id="quantity" name="quantity" min="1" max="1000" required>
		<input type="hidden" id="productoForm" name="product">
		<input type="submit" value="Buy!">
		<script>
			function sendMessageTelegram() {
				document.getElementById("productoForm").value = "${requestScope.producto}";
				if (!"${requestScope.producto}"=="") {
					return true;
				}
				else {
					return false;
				}
			}
		</script>
	</form>
</body>
</html>