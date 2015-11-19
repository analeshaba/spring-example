# spring-example
Sample REST API

-------


Generated project characteristics
-------------------------
* Spring Boot
 
Installation
------------

To install in your local repository execute following commands:

```bash
    git https://github.com/analeshaba/spring-example.git
    cd spring-example
    mvn clean install
```


Run the project
----------------
 Maven, you can run the application using 
```bash
	mvn spring-boot:run
```

Test on the API
-------------------
Content-Type → application/json;charset=UTF-8

GET : Get a list of all house
	http://localhost:8080/house - Returns a list of all houses
	
POST: Create a house
	http://localhost:8080/house
	JSON Body: House with bathroom of 5th floor
	e.g.  [{"house":null,"type":"BATHROOM","length":6.0,"width":5.0,"floor":5} ]

DELETE: Delete house # 3
	http://localhost:8080/house/3




	
