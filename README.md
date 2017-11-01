# spring-example
Coding Challenge

-------


Generated project characteristics
-------------------------
* Spring Boot using Maven
 
Build source using maven  
------------

To build code locally extract source code zip, then execute following commands:

```
    mvn clean install
```


Run the project
----------------
 After installing  Maven on your server, you can run the application using as Spring Boot applications
```
	mvn spring-boot:run
	For additional details on the spring boot maven plug in see:[Maven Spring Boot](https://docs.spring.io/spring-boot/docs/current/maven-plugin/usage.html)
```

Assumptions
-------------------
1. API versioning not currently supported
2. Client authentication not supported
3. Data is persisted in a in memory h2 database
4. Expected phone number format is (NNN)NNN-NNNN
5. Birth date format is dd/MM/yyyy
6. endpoints are only exposed over http
7. The rest service is configured to use a random port. For the available port on you system please check the log file
e.g    Tomcat started on port(s): 56497 (http)  OR you can update the application.properties file by setting the server.port property

Test on the API
-------------------
Content-Type → application/json;charset=UTF-8

## GET : 
- Returns a list of all contacts

	http://localhost:{your port}/contacts

- Returns a list of all contacts for a state

	http://localhost:{your port}/contacts/state/{state code}
	e.g. http://localhost:{your port}/contacts/state/tx - returns all contact for State of Texas
	
-  Returns a list of all contacts for a city
	
	http://localhost:{your port}/contacts/city/{city name}
	e.g. http://localhost:{your port}/contacts/city/chicago - returns all contact for Chicago 
	
	
- Searches for  contact with given phone

	 http://localhost:{your port}/contacts?email={phone}
	 e.g. http://localhost:{your port}/contacts?phone=(773)224-1830	
		
- Searches for  contact with given email

	*http://localhost:{your port}/contacts?email={email address}*
	 e.g. http://localhost:{your port}/contacts?email=ASAS@ASAD.NOM 
		
##POST: 
- Create a contact

	http://localhost:{your port}/contacts
	Sample JSON Body: 
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

##DELETE:
- Delete contact # 3

	http://localhost:{your port}/contacts/{contact id}

##PUT:
- Update contact # 4

	http://localhost:{your port}/contacts/ 
	Sample JSON Body:  
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

	
