# spring-example
Coding Challenge

-------


Generated project characteristics
-------------------------
* Spring Boot
 
Build source using maven  
------------

To build code locally repository execute following commands:

```bash
    mvn clean install
```


Run the project
----------------
 After installing  Maven on your server, you can run the application using as Spring Boot applications
```bash
	mvn spring-boot:run
	For additional details on the spring boot maven plug in see: https://docs.spring.io/spring-boot/docs/current/maven-plugin/usage.html
```

Test on the API
-------------------
Content-Type → application/json;charset=UTF-8

GET :  
	http://localhost:8080/contacts - Returns a list of all contacts
	
GET :  
	http://localhost:8080/contacts/state/{state code} - Returns a list of all contacts for a state
	e.g. http://localhost:8080/contacts/state/tx - returns all contact for State of Texas
	
GET :  
	http://localhost:8080/contacts/city/{city name} - Returns a list of all contacts for a city	
	e.g. http://localhost:8080/contacts/city/chicago - returns all contact for City of Chicago 
	
GET :  
	 http://localhost:8080/contacts?email={phone}- Searches for  contact with given phone
	e.g. http://localhost:8080/contacts?phone=(773)224-1830	
		
GET :  
	 http://localhost:8080/contacts?email={email address}- Searches for  contact with given email	
	e.g. http://localhost:8080/contacts?email=ASAS@ASAD.NOM 
		
POST: Create a contact
	http://localhost:8080/contacts
	JSON Body: 
	{
	"name": "Contact Nm",
	"company": "Contact Company",
	"image": "image URL",
	"email": "asas@asad.nom",
	"birthDate": "21/09/1978",
	"phoneNumber": "(773)224-1830",
	"address": "123 Main Street",
	"city": "Chicago",
	"state": "IL",
	"postalCode": "60606-1212",
	"country": "USA"
	}

DELETE: Delete contact # 3
	http://localhost:8080/contacts/{contact id}


PUT: Update contact # 4
	http://localhost:8080/contacts/ 
	JSON Body:  
	{
	  	"id":4,
		"name": "Contact Nmewe Update",
		"company": "Contact Company",
		"image": "image URL",
		"email": "asas@asad.nom",
		"birthDate": "21/09/1978",
		"phoneNumber": "(773)224-1830",
		"address": "123 Main Street",
		"city": "Chicago",
		"state": "IL",
		"postalCode": "60606-1212",
		"country": "USA"
	}


Assumptions
-------------------
1.API versioning not currently supported
2.Client authentication not supported
3.Data is persisted in a in memory h2 database
3.Expected phone number format is (NNN)NNN-NNNN
4.Birth date format is dd/MM/yyyy
5.endpoints are only exposed over http
6. The rest service is configured to use a random port


	
