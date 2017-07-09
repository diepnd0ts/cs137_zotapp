<html>
<head>
    <title>Form to create a new resource</title>
</head>
<body>
<!-- This form will submit a POST request and the POST method defined in TodoResource will respond to it -->
<center><h2>Create a New Product</h2></center>
<form action="../jerseyrest/v1/api/products" method="POST">
    Title <input name="title" >
    <br/>
    Developer <input name="developer" >
    <br/>
    Price <input name="price" >
    <br/>
    Genre <input name="genre" >
    <br/>
    Downloads <input name="downloads" >
    <br/>
    Download Size <input name="downloadSize" >
    <br/>
    Content Rating <input name="contentRating" >
    <br/>
    App Rating <input name="appRating" >
    <br/>
    Icon <input name="icon" >
    <br/>
    Description <input name="description" >
    <br/>
    <input type="submit" value="Submit" />
</form>
<form action="../jerseyrest/v1/api/orders" method="POST">
    Game <input name="game" >
    <br/>
    First Name <input name="firstName" >
    <br/>
    Last Name <input name="lastName" >
    <br/>
    Address <input name="address" >
    <br/>
    City <input name="city" >
    <br/>
    State <input name="state" >
    <br/>
    Zip Code <input name="zipCode" >
    <br/>
    Phone <input name="phone" >
    <br/>
    Email <input name="email" >
    <br/>
    Credit Card <input name="creditCard" >
    <br/>
    Expiration <input name="expiration" >
    <br/>
    Security Code <input name="securityCode" >
    <br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
