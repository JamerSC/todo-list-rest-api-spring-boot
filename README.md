### Project Setup - April 21, 2025

### todo-list-rest-api-spring-boot
-A simple todo rest api in spring boot.

- Spring Initializer Setup
  ![Screenshot 2025-04-21 222230.png](..%2F..%2F..%2F..%2FPictures%2FScreenshots%2FScreenshot%202025-04-21%20222230.png)
### Dependencies
- Spring Dev Tools, Spring Web, Spring Data JPA, Lombok, & MySQL Driver
### Application Architecture
React Web Application <-> Spring boot Rest API Service

### April 22, 2025

### Annotations
- `@RestController` is a convenience annotation in Spring Boot that is used to create RESTful web services. It is a combination of two annotations:
  - ✅ @RestController = @Controller + @ResponseBody
  - ✅ `@Controller`: Marks the class as a Spring MVC controller (i.e., a class that handles web requests).
  - ✅ `@ResponseBody`: Tells Spring to write the return value of the method directly to the HTTP response body, instead of trying to render a Thymeleaf or JSP template.
- `@RequestMapping` is an annotation in Spring used to map HTTP requests to handler methods or classes. It’s one of the core annotations for building web applications and REST APIs in Spring.
- `@CrossOrigin` annotation in Spring Boot is used to enable Cross-Origin Resource Sharing (CORS) for a REST controller or a specific handler method. This is necessary when your frontend (like Angular, React, etc.) is hosted on a different domain or port than your backend, and you want to allow it to make HTTP requests to your Spring Boot API.
- `@Service` is a Spring annotation used to mark a class as a service layer component in your application.
  - ✅ 📌 What it does:
    - a. Tells Spring “Hey, this class holds business logic.”
    - b. It makes the class a Spring-managed bean, so it can be autowired into other components like controllers or other services.
- `@Transactional` is a Spring annotation that manages transactions automatically for you — meaning it ensures that a block of code (usually a method) runs within a database transaction.
  - ✅ 🔧 What it does:
  - a. Starts a transaction before the method runs.
  - b. Commits the transaction if the method completes successfully.
  - c. Rolls back the transaction if the method throws an exception.

### April 23, 2025
- Prepare HTTP Methods - CRUD

### April 24, 2025
- Updated REST Controller, Front End updated the FETCH API CRUD

### January 13, 2025
- Code refactor todo, entity, controller/endpoint, etc.
- Implement JPA Specification, Pageable, & custom response
- Added Postman collection for api testing