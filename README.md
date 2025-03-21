# Sanlam Banking Project
The project is using spring boot application, therefore it uses an MVC design pattern to separate concerns in the application where I have BankAccountController.java
which has less code because is dedicating more logic to the service being injected to the class.
And change the given code that was using JDBC to use JPA and hibernate where Hibernate Query Language(HQL) is used to query or manipulate data, which is making the application to work with java objects rather than a the direct tables from the database.
The application is connecting/uses h2 in memory database which can be accessed by "http://localhost:8085/h2-console/" once the application is started, and put "jdbc:h2:mem:bankingdb" on JDBC URL field. 


# Required Configuration
Java 17
Maven 

# Get Started
To start/run the application, download the dependencies since the project is a spring boot application, you open terminal/command line to run the below commands

mvn clean install, then <br>
mvn spring-boot:run <br><br>
The application will start on port 8085
then on postman you can hit <br>
"http://localhost:8085/bank/withdraw?accountId=23&amount=100.00"
