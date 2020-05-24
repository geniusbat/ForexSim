<%@page import="pack_forexSim.controller.NoticiasController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Telegram</title>
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
    <p style="margin-left: 2%">
    	Your action was sent as: "
    	${requestScope.UserBuy}
    	" to Telegram.
    </p>	
	<a href="https://t.me/joinchat/NOPaaRR98-fcSofLHPiSlw" style="margin-left: 2%">Join ForexSim Telegram!</a>
 </body>