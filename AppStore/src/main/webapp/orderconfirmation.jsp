<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page import="zotapp.*"%>
<%@page import="java.util.ArrayList"%>

<% ShoppingCart cart = (ShoppingCart)session.getAttribute("cart"); 
    String fullname = request.getParameter("firstname") + " " + request.getParameter("lastname");
    String creditcard = request.getParameter("creditcard");%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./navbar.css" type="text/css" />
        <link rel="stylesheet" href="./description.css" type="text/css" />
        <title>ZotApp: Order Confirmation</title>
    </head>
    <body>
        <ul>
            <li><img src='images/logo.png' alt='logo' style='width:80px;height:50px;'></li>
            <li><a href='index.jsp'>Home</a></li>
            <li><a href='RESTDisplayProducts'>Products</a></li>
            <li><a href='about.jsp'>About</a></li>
            <li><a href='team.jsp'>The Team</a></li>
            <li><a class='active' href='mycart.jsp'>My Cart</a></li>
        </ul>
        <div style='padding:20px;margin-top:70px;'>
            <div class='description' text-align='left'>
                <h1>Your Order Has Been Processed</h1>
                <hr>
                <p><b>Ordered By:</b> <%=fullname%></p>
                <p><b>Purchased with Card Ending In:</b> <%=creditcard.substring(creditcard.length()-4, creditcard.length())%></p>
                <form action="index.jsp">
                    <input class="button" type="submit" value="Return to ZotApp">
                </form>
            </div>
        </div>
    </body>
</html>
