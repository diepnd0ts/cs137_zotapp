<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="zotapp.Item, zotapp.ShoppingCart"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./navbar.css" type="text/css" />
        <link rel="stylesheet" href="./description.css" type="text/css" />
        <title>ZotApp: Shopping Cart</title>
    </head>
    <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th {
                height: 25px;
                vertical-align: middle;
                text-align: center;
                background-color: #4CAF50;
            }

            td {
                height: 90px;
                width: 90px;
                vertical-align: middle;
                text-align: center;
            }

            tr:nth-child(even) {
                background-color: #f1f1f1
            }

            tr:hover {
                background-color: #54D04C;
            }

            a {
                color: black;
            }

            .product:hover {
                width: 85px;
                height: 85px;
                background-color: black;
            }
    </style>
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
        <div class='cartInfo'>
        <h1>Shopping Cart</h1>
        <%  ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");

            if (cart != null && cart.getCartSize() > 0) { %>
              <table>
                <tr>
                    <th></th>
                    <th>Game</th>
                    <th>Developer</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>      
                <tbody id = "table">
                    <%
                      for (int i = 0; i < cart.getCartSize(); i++) {  
                    %>
                    <tr>
                      <td><a href= "<%= "SessionTracking?game="+cart.getItemName(i)%>"><img src=<%=cart.getItemIcon(i)%> height='75' width='75' class='product'/></a></td>
                      <td><a href= "<%= "SessionTracking?game="+cart.getItemName(i)%>"><%=cart.getItemName(i)%></a></td>
                      <td><%=cart.getItemDeveloper(i)%></td>
                      <td><%=cart.getItemPrice(i)%></td>
                      <td><%=cart.getItemQuantity(i)%></td>
                    </tr>
                    <%} %>
                </tbody>
            </table>
                <hr>
                <p style="color:red; text-align:right;"><b style="color:black;">Total (<%=cart.getTotalQuantity()%> Items):</b> <%=cart.getTotalPrice()%></p>
                <div style="padding-bottom:30px;">
                    <form style="float:left;" action="RESTDisplayProducts">
                        <input class='button' type='submit' value='Continue Shopping'/>
                    </form> 
                    <form style="float:right;" action="orderform.jsp" method='post'>
                        <input class='button' type='submit' value='Proceed to Check Out'/>
                    </form> 
                </div>

                    
            <% }
            else {
                  %><h2 style="text-align:center;">Your cart is empty!</h2><%
            }
            %>      
            </div>
            </div>
            </body>
          </html>
