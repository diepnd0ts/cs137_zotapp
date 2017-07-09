REST Service Methods Implemeneted
- RESTDisplayProducts Servlet
	- GET Request generated in products.jsp (gathers all the products in the database)
	- GET Request with ID generated for viewproducts.jsp (gathers the information for a single product for a more detailed product page)
- RESTProcessOrders Servlet
	- POST Request using form parameters (Inserts order entries after the user enters their billing information in orderform.jsp)

In JerseyRest Folder
- Request and Responses for:
- Products REST API
	- GET http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/products
	- GET http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/products/1	
	- POST http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/products
	- PUT http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/products/1
	- DELETE http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/products/1
- Orders REST API
	- GET http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/orders
	- GET http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/orders/3892
	- POST http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/orders
	- PUT http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/orders/3867
	- DELETE http://andromeda-50.ics.uci.edu:5050/jerseyrest/v1/api/orders/3868
