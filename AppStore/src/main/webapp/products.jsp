<%@ page import="java.util.ArrayList" %>
<%@ page import="zotapp.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <title>ZotApp: Products</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./navbar.css" type="text/css" />
        <link rel="stylesheet" href="./description.css" type="text/css" />
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
            .livesearch a {
                text-decoration: none;
            }
            .livesearch p:hover {
                background-color: #54D04C;
            }
            .product:hover {
                width: 85px;
                height: 85px;
                background-color: black;
            }
            input[type=text] {
                width: 130px;
                box-sizing: border-box;
                border: 2px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
                background-color: white;
                background-image: url('images/searchicon.png');
                background-size: 40px 40px; 
                background-repeat: no-repeat;
                padding: 12px 20px 12px 40px;
                -webkit-transition: width 0.4s ease-in-out;
                transition: width 0.4s ease-in-out;
            }

            input[type=text]:focus {
                width: 100%;
            }
        </style>
        <script type="text/javaScript">
            function autocompletesearch(search) {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        document.getElementById("table").innerHTML = xhr.responseText;                        
                    }
                }
                xhr.open("GET", "AutoComplete?sql=" + search, true);
                xhr.send();
            }
        </script>
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
    
        <div style="padding:20px;margin-top:40px;">
            <form>
                <input type="text" name="search" placeholder="Search.." onkeyup="autocompletesearch(this.value)">
                <div id="livesearch" class="livesearch"></div>
            </form>
            <%
                ArrayList<Product> productsList = (ArrayList<Product>)request.getAttribute("productsList");
                ArrayList<String> recentProducts = (ArrayList<String>)session.getAttribute("recentVisits");
                
                //Make this into a dropdown
                if (recentProducts != null && recentProducts.size() > 0) {
                    out.println("<h1>Recently Visited Products</h1>");
                    for (int i = 0; i < recentProducts.size(); i++) {
                        out.println("<p>" + recentProducts.get(i) + "</p>");
                    }
                }
            %>
            <table>
                <tr>
                    <th></th>
                    <th>Game</th>
                    <th>Price</th>
                    <th>Genre</th>
                    <th>Downloads</th>
                    <th>Download Size</th>
                    <th>Content Rating</th>
                    <th>App Rating</th>
                </tr>
                <tbody id = "table">
                    <%
                      for (int i = 0; i < productsList.size(); i++) {  
                    %>
                    <tr>
                      <td><a href= "<%= "SessionTracking?id="+(productsList.get(i)).getId() + "&title=" + (productsList.get(i)).getTitle()%>"><img src=<%=(productsList.get(i)).getIcon()%> height='75' width='75' class='product'/></a></td>
                      <td><a href= "<%= "SessionTracking?id="+(productsList.get(i)).getId() + "&title=" + (productsList.get(i)).getTitle()%>"><%=(productsList.get(i)).getTitle()%></a></td>
                      <td>$<%=String.format("%.2f", (productsList.get(i)).getPrice())%></td>
                      <td><%=(productsList.get(i)).getGenre()%></td>
                      <td><%=String.format("%,d", productsList.get(i).getDownloads())%></td>
                      <td><%=(productsList.get(i)).getDownloadSize()%></td> 
                      <td><%=(productsList.get(i)).getContentRating()%></td> 
                      <td><%=(productsList.get(i)).getAppRating()%></td> 
                    </tr>
                    <%} %>
                </tbody>
            </table>
        </div>
    </body>
</html>

