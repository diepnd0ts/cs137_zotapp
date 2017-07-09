<%@ page import="java.util.ArrayList" %>
<%@ page import="zotapp.Product" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%
    Product product = (Product)request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>ZotApp: <%=product.getTitle()%></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./navbar.css" type="text/css" />
        <link rel="stylesheet" href="./description.css" type="text/css" />
        <style>
            img {
            height: 250px;
            width: 250px;
        }
        </style>
    </head>
    <body>
        <ul>
            <li><img src="images/logo.png" alt="logo" style="width:80px;height:50px;"></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a class="active" href="RESTDisplayProducts">Products</a></li>
            <li><a href="about.jsp">About</a></li>
            <li><a href="team.jsp">The Team</a></li>
            <li><a href='mycart.jsp'>My Cart</a></li>
        </ul>
        <div class="container">
            <div class="details">
                <h1><%=product.getTitle()%></h1>
                    <img src=<%=product.getIcon()%>>
                    <p><%=product.getDeveloper()%></p>
                    <p><%=product.getGenre()%></p>
                    <p><%=product.getContentRating()%></p>
                    <p><%=String.format("%,d",product.getDownloads())%> Downloads</p>
                    <p><%=product.getDownloadSize()%></p>
                    <p><%=product.getAppRating()%> Rating</p>
                    <div style='text-align: center'>
                        <form action='CartInfo' method='post'>
                           <input class='button' type ='submit' value='Buy $<%=String.format("%.2f", product.getPrice())%>'>
                            <input type ='hidden' name='game' value='<%=product.getTitle()%>'>
                            <input type ='hidden' name='developer' value='<%=product.getDeveloper()%>'>
                            <input type ='hidden' name='price' value='<%=product.getPrice()%>'>
                            <input type ='hidden' name='icon' value='<%=product.getIcon()%>'>
                        </form>
                    </div>
                </div>
            <div class='description'>
                       <%=product.getDescription()%>
            </div>
        </div>
    </body>
</html>
