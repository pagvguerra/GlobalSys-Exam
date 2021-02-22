# WINE TEST
Project for the company WINE
In this project Rest APIs were created to control the stores and prevent the conflict of ranges between the CEPs.

The project was all written in English, only the name of the table and the name of the table fields that are in Portuguese, as they were the names that were order in the test.
In addition to the methods requested in the test, I created some other methods, as I saw an opportunity to do more than what was asked for.
To access the SWAGGER APIS documentation, the URL is: http://localhost:8080/swagger-ui.html

Technologies Used: SPRING BOOT, SPRING DATA JPA, LOMBOK, REST, SWAGGER, among others.
Database: MYSQL

The APIs made available for use in this project are:

Removes all Physical Stores: 						          /wine/phisicStores/removeAll 			       - Method: POST 

Removes a Specific Physical Store: 					      /wine/phisicStores/remove 				       - Method: POST - Send the Id field in the Body
Body Example:
{
    "id":52
}

Create a Physical Store: 							            /wine/phisicStores/create 				       - Method: POST - Send the fields in the Body (field ID ignored)
Body Example:
{
    "storesCode": "loja1",
    "initialRange": 22030002,
    "finalRange": 22080000
}

Update a Physical Store: 							            /wine/phisicStores/update 				       - Method: PUT  - Send fields in the Body (including ID)
Body Example:
{
    "id":32,
    "storesCode": "loja1",
    "initialRange": 22030003,
    "finalRange": 22030009
}

Note: To save or update a Store, there are validations to ensure that there is no overlapping of ranges between CEPs.

Retrieves a Physical Store By Id:					        /wine/phisicStores/{id}					        - Method: GET  - Send the Id in the URL

Recovers all Physical Stores:						          /wine/phisicStores/findAll				      - Method: GET  

Retrieves all Physical Stores in a paginated way:	/wine/phisicStores/findAllPaged/{page}	- Method: GET  - Send the page number in the URL	

Retrieves a Physical Store By Zip Range:			    /wine/phisicStores/findByZipCode		    - Method: GET  - Send the fields (initialRange e FinalRange) in the Body 
Body Example:
{
    "initialRange":22030002,
    "finalRange":22080000
}




