<%@page contentType='text/html' pageEncoding='windows-1252'%>
<%@page import="zotapp.Item, zotapp.ShoppingCart"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ZotApp: Shopping Cart</title>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'> 
        <link rel='stylesheet' href='./navbar.css' type='text/css' />
        <link rel='stylesheet' href='./description.css' type='text/css' />
    </head>
    <script type='text/javaScript'>   
             function checkOrderForm()       
             {      
                 var firstName = document.OrderForm.firstname;      
                 if (firstName.value == '') {      
                     alert('Missing First Name Entry');      
                     return false;      
                 }      
                 if (!isNaN(firstName.value)) {      
                     alert('Incorrect First Name Entered');      
                     return false;      
                 }      
                 var lastName = document.OrderForm.lastname;      
                 if (lastName.value == '') {      
                     alert('Missing Last Name Entry');      
                     return false;      
                 }      
                 if (!isNaN(lastName.value)) {      
                     alert('Incorrect Last Name Entered');      
                     return false;      
                 }      
                 var address = document.OrderForm.address;      
                 if (address.value == '') {      
                     alert('Missing Address Entry');      
                     return false;      
                 }      
                 if (isNaN(address.value.split(' ')[0]) || !isNaN(address.value.split(' ')[1])) {      
                     alert('Incorrect Address Entered');      
                     return false;      
                 }      
                 var zipCode = document.OrderForm.zipcode;      
                 if (zipCode.value == '') {      
                     alert('Missing Zip Code Entry');      
                     return false;      
                 }      
                 if (isNaN(zipCode.value) || zipCode.value.length != 5) {      
                     alert('Incorrect ZIP Code Entry');      
                     return false;      
                 }      
                 var city = document.OrderForm.city;      
                 if (city.value == '') {      
                     alert('Missing City Entry');      
                     return false;      
                 }      
                 if (!isNaN(city.value)) {      
                     alert('Incorrect City Entered');      
                     return false;      
                 }      
                 var state = document.OrderForm.state;      
                 if (state.value == '') {      
                     alert('Missing State Entry');      
                     return false;      
                 }      
                 if (!isNaN(state.value)) {      
                     alert('Incorrect State Entry');      
                     return false;      
                 }      
                 var phone = document.OrderForm.phone;      
                 if (phone.value == '') {      
                     alert('Missing Phone Entry');      
                     return false;      
                 }      
                 if (isNaN(phone.value) || phone.value.length < 10) {      
                     alert('Incorrect Phone Entry');      
                     return false;      
                 }      
                 var email = document.OrderForm.email;      
                 if (email.value == '') {      
                     alert('Missing Email Entry');      
                     return false;      
                 }      
                 var atpos = email.value.indexOf('@');      
                 var dotpos = email.value.lastIndexOf('.');      
                 if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {      
                     alert('Invalid e-mail address Entered');      
                     return false;      
                 }      
                 var creditCard = document.OrderForm.creditcard;      
                 if (creditCard.value == '') {      
                     alert('Missing Credit/Debit Card Entry');      
                     return false;      
                 }      
                 if (isNaN(creditCard.value) || creditCard.value.length < 16) {      
                     alert('Incorrect Credit/Debit Card Entry');      
                     return false;      
                 }      
                 //input type for expiration date checks for empty entries. No need to check in this function      
                 var securityCode = document.OrderForm.securitycode;      
                 if (securityCode.value == '') {      
                     alert('Missing Credit/Debit Card Security Code Entry');      
                     return false;      
                 }      
                 if (isNaN(securityCode.value) || securityCode.value.length < 3) {      
                     alert('Incorrect Credit/Debit Card Security Code Entry');      
                     return false;      
                 }      
             }      
    </script>
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
            <div class='container'>
                <fieldset class='details'>
                    <legend>
                        <h1><b>Order Form</b></h1>
                    </legend>
                    <form method='post' action='RESTProcessOrder' name='OrderForm' onsubmit='return (checkOrderForm())'>
                        <b>Billing Address</b><hr/>
                        First Name: <input type='text' name='firstname'><br>
                        Last Name: <input type='text' name='lastname'><br>
                        Address: <input type='text' name='address'><br>
                        ZIP Code: <input type='text' name='zipcode' maxlength='5'><br>
                        City: <input type='text' name='city' id='city'><br>
                        State: <input type='text' name='state' id='state' maxlength='2'><br>
                        <br>
                        <b>Contact Information</b><hr/>
                        Phone: <input type='text' name='phone' maxlength='10'><br>
                        Email: <input type='text' name='email'><br>
                        <br>
                        <b>Credit/Debit Card Information</b><hr/>
                        Credit/Debit Card Number: <input type='text' name='creditcard' maxlength='16'><br>
                        Expiration: <input type='date' name='expiration'><br>
                        Security Code: <input type='text' name='securitycode' maxlength='3'><br><br>
                        <center>
                            <input class='button' type='reset' value='Reset'><br>
                            <input class='button' type='submit' value='Place My Order'><br>
                        </center>
                    </form>
                </fieldset>
                <div class='description'>
                    <h1>Your Cart</h1>
                     <% ShoppingCart cart = (ShoppingCart)session.getAttribute("cart"); %>
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
                </div>
            </div>
        </div>
    </body>
</html>