<%--
  Created by IntelliJ IDEA.
  User: barbarity
  Date: 11/06/15
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="main.java.de.tum.in.dbpra.model.Part" %>
<%@ page import="java.util.ArrayList" %>
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
    <h1>Parts</h1>
    <div class="row">
        <div class="col-md-9">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th><a href="/exercise73?sort=partkey">Partkey</a></th>
                    <th><a href="/exercise73?sort=name">Name</a></th>
                    <th><a href="/exercise73?sort=type">Type</a></th>
                    <th><a href="/exercise73?sort=size">Size</a></th>
                    <th><a href="/exercise73?sort=container">Container</a></th>
                    <th><a href="/exercise73?sort=retailprice">Retail Price</a></th>
                </tr>
                </thead>
                <tbody>
                <%
                    // retrieve your list from the request, with casting
                    ArrayList<Part> parts = (ArrayList<Part>) request.getAttribute("parts");
// print the information about every category of the list
                    for(Part part : parts) {
                        out.println("<tr>");
                        out.println("<td>" + part.getPartkey() + "</td>");
                        out.println("<td>" + part.getName() + "</td>");
                        out.println("<td>" + part.getType() + "</td>");
                        out.println("<td>" + part.getSize() + "</td>");
                        out.println("<td>" + part.getContainer() + "</td>");
                        out.println("<td>" + new DecimalFormat("###,###.00 €").format(part.getRetailprice()) + "</td>");
                        out.println("</tr>");
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-md-3">
            <form action="/exercise73" method="post">
                <div class="form-group">
                    <label for="search">Search:</label>
                    <input name="search" type="text" class="form-control" id="search" placeholder="Search...">
                </div>
                <div class="form-group">
                    <label for="column">On Column:</label>
                    <select name="column" id="column" class="form-control">
                        <option>partkey</option>
                        <option>name</option>
                        <option>type</option>
                        <option>size</option>
                        <option>container</option>
                        <option>retailprice</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="method" id="like" value="like" checked> Like
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="method" id="exact" value="exact"> Exact
                    </label>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
