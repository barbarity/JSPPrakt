<%--
  Created by IntelliJ IDEA.
  User: barbarity
  Date: 11/06/15
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="main.java.de.tum.in.dbpra.model.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<html>
<head>
    <title>Data Base Lab Course Team 05</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Team 05</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Assignment7
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/">Exercise71</a></li>
                        <li><a href="/exercise72">Exercise72</a></li>
                        <li><a href="/exercise73">Exercise73</a></li>
                    </ul>
                </li>
                <li><a href="#">Assignment8</a></li>
                <li><a href="#">Assignment9</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
<h1>Orders with status: OK</h1>
<div class="row">
    <div class="col-xs-12 col-sm-6 col-md-8">
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th>Orderkey</th>
        <th>Custkey</th>
        <th>Orderstatus</th>
        <th>Totalprice</th>
        <th>Orderdate</th>
    </tr>
    </thead>
    <tbody>
    <%
        // retrieve your list from the request, with casting
        ArrayList<Order> ordersOK = (ArrayList<Order>) request.getAttribute("ordersOK");
// print the information about every category of the list
        for(Order order : ordersOK) {
            out.println("<tr>");
            out.println("<td><a href=\"/exercise74?orderkey="+ order.getOrderkey() + "\"> " + order.getOrderkey() + "</a></td>");
            out.println("<td>" + order.getCustkey() + "</td>");
            out.println("<td>" + order.getOrderstatus() + "</td>");
            out.println("<td>" + new DecimalFormat("###,###.00 €").format(order.getTotalprice()) + "</td>");
            out.println("<td>" + order.getOrderdate() + "</td>");
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-6 col-md-8">
<h1>Orders with status: NO</h1>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th>Orderkey</th>
        <th>Custkey</th>
        <th>Orderstatus</th>
        <th>Totalprice</th>
        <th>Orderdate</th>
    </tr>
    </thead>
    <tbody>
    <%
        // retrieve your list from the request, with casting
        ArrayList<Order> ordersNO = (ArrayList<Order>) request.getAttribute("ordersNO");
// print the information about every category of the list
        for(Order order : ordersNO) {
            out.println("<tr>");
            out.println("<td><a href=\"/exercise74?orderkey="+ order.getOrderkey() + "\"> " + order.getOrderkey() + "</a></td>");
            out.println("<td>" + order.getCustkey() + "</td>");
            out.println("<td>" + order.getOrderstatus() + "</td>");
            out.println("<td>" + new DecimalFormat("###,###.00 €").format(order.getTotalprice()) + "</td>");
            out.println("<td>" + order.getOrderdate() + "</td>");
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
    </div>
</div>
</div>
</body>
</html>
