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
<%@ page import="main.java.de.tum.in.dbpra.model.Lineitem" %>
<%@ page import="main.java.de.tum.in.dbpra.model.Customer" %>
<%@ page import="main.java.de.tum.in.dbpra.model.Part" %>
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
<%
    ArrayList<Part> parts = (ArrayList<Part>) request.getAttribute("parts");
    ArrayList<Lineitem> lineitems = (ArrayList<Lineitem>) request.getAttribute("lineitems");
    Order order = (Order) request.getAttribute("order");
    Customer customer = (Customer) request.getAttribute("customer");
%>
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
    <div class="row">
        <div class="col-xs-12">
            <div class="invoice-title">
                <h2>Invoice</h2><h3 class="pull-right">Order # <%= order.getOrderkey() %></h3>
            </div>
            <hr>
            <div class="row">
                <div class="col-xs-6">
                    <address>
                        <strong>Billed To:</strong><br>
                        <%= customer.getName() %><br>
                        <%= customer.getAddress() %><br>
                        <%= customer.getPhone() %>
                    </address>
                </div>
                <div class="col-xs-6 text-right">
                    <address>
                        <strong>Order Date:</strong><br>
                        <%= order.getOrderdate() %><br><br>
                    </address>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>Order summary</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <td><strong>Item</strong></td>
                                <td><strong>Name</strong></td>
                                <td class="text-center"><strong>Quantity</strong></td>
                                <td class="text-right"><strong>Price</strong></td>
                                <td class="text-right"><strong>Tax</strong></td>
                                <td class="text-right"><strong>Discount</strong></td>
                                <td class="text-right"><strong>Total</strong></td>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- foreach ($order->lineItems as $line) or some such thing here -->
                            <%
                                for(int i = 0; i < lineitems.size(); i++) {
                                    out.println("<tr>");
                                    out.println("<td>"+ lineitems.get(i).getLinenumber() +"</td>");
                                    out.println("<td>"+ parts.get(i).getName() +"</td>");
                                    out.println("<td class=\"text-right\">"+ lineitems.get(i).getQuantity() +"</td>");
                                    out.println("<td class=\"text-right\">"+ parts.get(i).getRetailprice() +"</td>");
                                    out.println("<td class=\"text-right\">"+ lineitems.get(i).getTax() +"</td>");
                                    out.println("<td class=\"text-right\">"+ lineitems.get(i).getDiscount() +"</td>");
                                    out.println("<td class=\"text-right\">"+ lineitems.get(i).getExtendedprice() +"</td>");
                                    out.println("</tr>");
                                }
                            %>

                                <td class="no-line"></td>
                                <td class="no-line"></td>
                                <td class="no-line"></td>
                                <td class="no-line"></td>
                                <td class="no-line"></td>
                                <td class="no-line text-center"><strong>Total</strong></td>
                                <td class="no-line text-right"><%= order.getTotalprice() %></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>