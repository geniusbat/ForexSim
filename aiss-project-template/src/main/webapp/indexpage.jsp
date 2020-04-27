<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test NewsApi</title>
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
<body>
    <h2 class="container-fluid">
        Forex$im
    </h2>
    <div class="row" style="margin-left:2%;">
    	<select id="cars">
		  <option value="volvo">Volvo</option>
		  <option value="saab">Saab</option>
		  <option value="opel">Opel</option>
		  <option value="audi">Audi</option>
		</select>
		<button onclick="getCar()">Search</button>
    </div>
    <div id="searchRes"></div>
    <script>
    	function getCar() {
    	var coche = document.getElementById("cars");
    	document.getElementById("searchRes").innerHTML=coche.value;
    	}
    </script>
    <div class="row">
        <div id="tester" class="col-6"></div>
		<script>
			TESTER = document.getElementById('tester');
			var trace1 = {
			  x: [1, 2, 3, 4],
			  y: [10, 15, 13, 17],
			  type: 'scatter'
			};
			var data = [trace1];
			var layout = { 
			  font: {size: 18},
				width:screen.width/2
			};
			var config = {responsive: true}
			Plotly.newPlot(TESTER, data, layout, config );
		</script>

		<!-- Noticias -->
		<div class="col-6 cubo">
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
		</div>
    </div>		

</body>
</html>