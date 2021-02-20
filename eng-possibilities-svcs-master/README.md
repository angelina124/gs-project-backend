# eng-possibilities-svcs

#### Welcome to Deep Pockets (DePo), your Investing Forecasting service!

###### The service is a Spring boot application ready with the basic fixtures to run your APIs locally. Here are some resources to get you started with Java Spring:
- https://spring.io/guides/gs/spring-boot/
- https://www.baeldung.com/spring-boot
- https://www.springboottutorial.com/introduction-to-spring-boot-for-beginners

#### Let's take a tour....

- /src | You guessed it, this folder holds all of the application code.
    - `/main/java`
        - `/configuration` | Hold configuration files that set up beans (resources) definitions that can be used throughout your application at runtime.
        - `/controller` | Holds the controllers exposed by your application. A controller contains the business logic of an application and is the presentation layer of your code (exposes the API endpoints). 
        - `/model` | Contains the POJOs used within the application. Feel free to tweak the existing ones or add your own!
        - `/service` | Services are contain the executing business logic of our application. It manages the domain model objects (defined in /model) and communicates with other services and data sources (like the data found in /main/resources/data).
    - `/main/resources/application.properties` | Hold the application properties for the Spring Boot app (learn more here: https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html).
    - `/main/resources/data` | Holds the json formatted data you'll need to read and process.
    - `/test/java` | Holds all of the test code for your application.  
        - Tests are a critical component of app development.  
        - Tests help us confirm our code is working as expected, and can even be used to tell us how to write our code...  
        - Learn more about Test Driven Development, TDD, here: https://hackernoon.com/introduction-to-test-driven-development-tdd-61a13bc92d92  
  
 #### Prerequisites
 You will need these installed
  - Java (8 or above)
  - Maven - To install the dependencies needed for the backend
  - NodeJS - To install the components needed to spin up the UI
  