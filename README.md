## What is Spring

- Spring is a dependency injection framework.

## What is dependency?

- 3 layers: Web - Business - Data
- Business layer is "dependent" on Data layer. Like that, Web layer is also "dependent" on Business layer.
- In class below, class ComplexBusinessService is dependent on sortAlgorithm. In other words, sortAlgorithm is dependency of the class.
```
// ...
public class ComplexBusinessService {
    SortAlgorithm sortAlgorithm;
// ...
```
- Dependencies of a class is data that the class needs to provide service.
- Consider below example. There will be many of classes that directly instantiate classes. They are all "tightly coupled". Just say you want to change the algorithm for a class.
```
// ...
public class ComplexBusinessService {
    SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm();
// ...
public class BubbleSortAlgorithm implements SortAlgorithm {
// ...
```
- Tightly coupled parts are not considered good in software architecture.
- Normally, people solve like below without Spring:
```
// ...
public class ComplexBusinessService {
    SortAlgorithm sortAlgorithm;

    public ComplexBusinessService(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }
// ...
```
- When you use above logic you will write codes like:
```
// ...
SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm();
ComplexBusinessService businessService = new ComplexBusinessService(sortAlgorithm);
//...
```
- Spring will instantiate a class and pass it to the logic(like above).

## How Spring works then?

- You, as a programmer, need to let Spring framework know what objects need to be managed and what are the dependencies.
```
// ...
@Component
public class ComplexBusinessService {

    @Autowired
    SortAlgorithm sortAlgorithm;
// ...
@Component
public class BubbleSortAlgorithm implements SortAlgorithm {
// ...
```
- By using @Component you tell the framework, "Okay, these are object you gotta manage."
- @Autowired let Spring know this is a dependency and Spring will start among the component and instances(classes) to find this one.
- Then the framework will populate the dependency with an instance that matches.
- This process is called "Dependency Injection".

## Basic terminology

- Beans: Instances managed by Spring framework.
- Autowiring: The process where Spring identifies the dependencies, identifies the matches for the dependencies and populates them.
- Dependency injection: Explained already :p
- Inversion of control: As you see in the example above, classes were usually in charge of instantiating object it needs. If you use Spring, however, the framework instantiates classes on behalf of controlled objects. This concept is IOC.
- IOC Container: Framework for implementing automatic dependency injection.
- Application context: IOC Container Spring framework concerns. Application context is the one where all the beans are created and managed.

## Setting up a Spring project

- https://start.spring.io would help when you start from scratch.

## When you use Spring, you need to tell the framework about:

- What are beans? => @Component
- What are dependencies of a bean? @Autowired
- Where to search for beans? => basically no need to(package)

## If there are instances that implement same interface?

- Spring will generate exception as it cannot decide which bean to use
- You can set @Primary in this case.
- Or you can simply use name directly ...
- @Primary has priority.
- There is another option: @Qualifire("name")

## Setter injection and constructor injection

- If a dependency is mandatory, constructor injection is recommended.
- Else, in case of optional dependency, you can use setter injection.
- In real world, however, just setter injection(actually, just @Autowired) is preferred.

## AOP

- Aspect Oriented Programming

## Crosscutting Concerns

- Considering many layers: like test modules

## ORM

- Object Relational Mapping

## Spring Modules

- Data Access/Integration: JDBC, ORM(like Hibernate), Transaction...
- Web: WebSocket, Servlet, Web...
- AOP
- Aspects
- Messaging
- Core Container: Beans, Core, Context...
- Test 

## Spring Projects

- Spring Boot: quickly build application
- Spring Cloud: build cloud native apps
- Spring Data: consistent data access
- Spring Integration: application integration
- Spring Batch: batch application
- Spring Security: protect your application
- ...

## Why Spring?

- it enables testable code. With JUnit, Mockito...
- There is no plumbing code: makes your code simple and clear.
- Flexible architecture. Lots of projects and modules.
- Staying current.

## Why Spring Boot?

- Enables building produection ready applications quickly
- Provides common non-functional features
    - embedded servers
    - metrics
    - health checks
    - externalized configuration

## What Spring Boot is NOT!

- Code generation
- Neither an application server nor a web server

## Spring Boot Features

- Quick Starter Projects with Auto Configuration
    - Web
    - JPA
- Embedded Servers: Tomcat, Jetty or Undertow
- Production-ready features
    - metrics, health checks and externalized configuration

## Bean Scope

- singleton: One instance per Spring Context. Default.
- prototype: New bean whenever requested. @Scope("prototype") or @Scope(ConfigurableBeanFactory.SCOPE.PROTOTYPE) below @Component
- request: One bean per HTTP request
- session: One bean per HTTP session
- You ought to keep minimum number of instances.

## DAO

- Data Access Object

## GOF

- Gang of Four Design Patterns in Java. It refers design patterns which were defined by four authors: Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides in their book Design Patterns: Elements of Reusable Object-Oriented Software.

## Difference betwoen GOF singletone and Spring singletone

- GOF singletone is "one instance in JVM" while Spring singletone is "one instance per Spring Context.

## What if spring applcation scan components in other package(s)?

- @ComponentScan("com.root.to.package")

## Bean Lifecycle

- @PostConstruct
- @PreDestroy

## CDI

- JAVA EE Dependency Injection Standard (JSR-330)
- Spring supports most anntations
    - @Inject: @Autowired
    - @Named: @Component & @Qualifier
    - @Singleton: @Scope

## Very basic two dependencies

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
</dependency>
```

## Spring without Spring Boot

- @SpringBootApplication => @Configuration & @ComponentScan
- You cannot SpringApplication.run() to get application context.
- Instead, you will do 
```
new AnnotationConfigApplicationContext(ContextClass.class)
```
- And, you need to edit pom.xml

## XML setting for application context(without Spring Boot)

- https://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/beans.html
- In case ling changes, google spring documentation application context example

## How are they different from each other? - Disambiguation

- IOC Container: Program or framework provide IOC.
- Application Context
    - In Spring, there are two implementation of IOC Container. Application context is one of them.
    - It is actually Bean Factory++
        - Spring's AOP features
        - Intenationallization: I18n capabilities. Like currency, language kind of locale stuffs.
        - WebApplicationContext for web applications etc.
- Bean Factory
    - It only provides basic contols of beans.

## Few more annotations for components

- @Component: Generic Component.
- @Repository: encapsulating storage, retrieval and search behavior typically from a relational datbase.
- @Service: Buisness Service Facade or Buisness Logic.
- @Controller: Controller in MVC pattern.

## To read values from property file

- @Value("${value.name}") from component side
- @PropertySource("classpath:property.file.name")

## Let's take a look at Maven

- POM stands for Projects Object Model
- Meven has a lifecycle
    - Validate: checks if pom.xml and codes are valid
    - Compile: compiles main codes and test codes
    - Test: unit test
    - Package: it will create jar
    - Integration Test
    - Verify
    - Install: install artifacts to local storage
    - Deploy: to remote maven repository
- If you do "mvn clean install" then it will do all the lifecycle up to Install.
- You must follow the structure for maven. You can search it (or your IDE will do this).
- Local Repository: Repository on your local system. It's like cache for remote mvn repo.
- Remote Maven Repository: Central Repositories. It stores all the versions of all dependencies.
- You can google and add dependencies to your pom.xml.
- mvn --version check version
- "clean" cleans up previous files. mvn clean: deletes target directory.
- mvn compile: just validate and compile.
- mvn test-compile: compiles source coes and test codes.
- mvn package: create a jar
- mvn test: all the unit tests will run(up to test: compile and test)
- help:effective-settings: will show all the mvn settings
- help:effective-pom: it will print pom and its super poms
- dependency:tree
- dependency:sources
- --debug: provides details for debugging

## JUnit

- Unit test: testing a specific method or group of methods
- Screen test: testing total deployment
- Junit is a framework that helps us to do unit test.
- As Junit is automated it's convenient to run test.

## Mockito

- Mockito will make your life a lot easier by providing mocks to your unit tests.

## For MVC 

- @RestController
- @GetMapping

## Spring Boot Starter Project Options

- spring-boot-starter-web-services
- spring-boot-starter-web
- spring-boot-starter-test
- spring-boot-starter-jdbc
- spring-boot-starter-hateos
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-cache
- spring-boot-starter-data-rest
- spring-boot-starter-actuator
- spring-boot-starter-undertow
- srping-boot-starter-jetty
- spring-boot-starter-tomcat
- spring-boot-starter-logging
- spring-boot-starter-log4j2
- ...

## Spring Boot Actuator

- http://localhost:8080
- http://localhost:8080/actuator/ => mappings, metrics, health, beans and so on
- pom.xml:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-rest-hal-browser</artifactId>
</dependency>
```
- property: management.endpoints.web.exposure.include = * 

## Srping Boot Developer Tools

- seems not working for now...
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

## Spring AOP

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
- Aspect: An aspect is a class that implements enterprise application concerns that cut across multiple classes, such as transaction management.
```
// AOP
// Configuration
@Aspect // Pointcut + Advice = Aspect
@Configuration
public class UserAccessAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // What kind of method calls I would intercept and when
    @Before("execution(* com.nick.spring.aop.business.*.*(..))") // Pointcut
    public void before(JoinPoint joinPoint) {
        // Advice
        logger.info("Intercepted Method Calls - {}", joinPoint);
    }

}
```
- Pointcut: where(= which execution) to intercept
- Joint: specific pointcut
- Advice: what to do?
- Weaving: Implementing AOP(the process itself)
- Weaver: framework implementing weaving
- @After, @AfterThrowing and @AfterReturning
- @Around and ProceedingJoinPoint
- It is recommended to use common pointcut with @Pointcut
- With Pointcut you can sellectively do something on certain annotationed methods or classes

## H2

- In memory DB to test things.

## CommandLineRunner

- It is an interface that helps you to run things on SpringBootApplication class.

## Logger

- Be familiar with 'private Logger logger = LoggerFactory.getLogger(this.getClass());'

## JPA

- Java Persistence API
- Hibernate is one of the famous implement of JPA(actually it was introduced as ORM way before JPA)
- It controls interface to DB.
```
// ...
@Entity
// @Table(name = "person") // you don't need as long as it matches to the table
public class Person {

    @Column(name = "id") // you don't need as long as it matches to the column
    @Id // primary key
    @GeneratedValue // auto generated
    private int id;
    private String name;
    private String location;
    private Date lastSeen;

    // you have to create a constructor with no parameters
    public Person() { }
// ...
```
- @Transactional: if one fails it fails all.
- spring.jpa.show-sql=true in property to see hibernate's queries

## Connecting to My SQL and Other Databases

Spring Boot makes it easy to switch databases.

- Install MySQL and Setup Schema
- Remove H2 dependency from pom.xml
- Add MySQL (or your database) dependency to pom.xml
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
- Configure application.properties

```properties
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://localhost:3306/person_example
spring.datasource.username=personuser
spring.datasource.password=YOUR_PASSWORD
```
- Restart the app and You are ready!

> Spring Boot can setup the database for you using Hibernate

Things to note:
- Spring Boot chooses a default value for you based on whether it thinks your database is embedded (default create-drop) or not (default none).
- ```spring.jpa.hibernate.ddl-auto``` is the setting to perform SchemaManagementTool actions automatically
   - none : No action will be performed.
   - create-only : Database creation will be generated.
   - drop : Database dropping will be generated.
   - create : Database dropping will be generated followed by database creation.
   - validate : Validate the database schema
   - update : Update the database schema
- Reference : https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#configurations-hbmddl

application.properties
```
#none, validate, update, create, create-drop
spring.jpa.hibernate.ddl-auto=create
```
## jar, war

- ar stands for Archive.

## Servlet

- https://docs.oracle.com/javaee/7/tutorial/servlets001.htm
- Servlet is a Java programming language class used to extend the capabilities of servers that host applications accessed by means of a request-response programming model.
- Servlets are usually added in web.xml or Annotation @Controller is used
- Dispatcher Servlet is a Front Controller

## JSP

- Java Server Pages

## scriptlets

- <% %>
- But business logics should be in Logic classes
- Avoid script expressions to do logics.

## Spring with Tomcat

- dispatcher: or first controller (map root dir)
- controller: or handler(map sub dir)
- view resolver: sets prefix and suffix
- view: files like jsp

## Spring MVC Request Flow

- DispatcherServlet receives HTTP Request. 
- DispatcherServlet identifies the right Controller based on the URL.
- Controller executes Business Logic.
- Controller returns a) Model b) View Name Back to DispatcherServlet.
- DispatcherServlet identifies the correct view (ViewResolver).
- DispatcherServlet makes the model available to view and executes it.
- DispatcherServlet returns HTTP Response Back.
- Flow: http://docs.spring.io/spring-framework/docs/2.0.8/reference/images/mvc.png

## Logging with log4j

- Logging levels
    - TRACE: Everything
    - DEBUG: logs more detailed than info
    - INFO: logs with informations
    - WARN: logs only when there is warning
    - ERROR: logs only when there is error
