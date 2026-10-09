# Book Management System — CRUD Operations

A Spring Boot REST API for managing book records with create, read, and update operations.

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- MySQL (or the database configured in the project)
- Maven
- Postman for API testing

## Project Structure

```text
src/main/java/com/bookapplication/bookapplication/
├── Controller/
│   └── BookController.java
├── Entity/
│   └── Book.java
├── Repository/
│   └── BookRepository.java
├── Service/
│   └── BookService.java
└── BookApplication.java
```

## Run the Application

1. Configure your database connection in `src/main/resources/application.properties`.
2. Make sure the database server is running.
3. Start the application:

   **Windows**
   ```bat
   mvnw.cmd spring-boot:run
   ```

   **macOS/Linux**
   ```bash
   ./mvnw spring-boot:run
   ```

The examples below assume the application runs on port `8080` with no additional context path.

## API Endpoints

These endpoints match the controller code currently shared.

| Operation | Method | Endpoint |
|---|---|---|
| Add a book | `POST` | `/book/v1/addBook` |
| Get a book by title | `GET` | `/book/v1/getBook/{name}` |
| Update a book | `PUT` | `/book/v1/updateBook` |

### 1. Add a Book

**Request**

```http
POST http://localhost:8080/book/v1/addBook
Content-Type: application/json
```

**Body — Body → raw → JSON in Postman**

```json
{
  "title": "The Secret",
  "author": "Rhonda Byrne",
  "genre": "Self-help"
}
```

Expected response: `200 OK` with the saved book. The generated ID will be returned if the entity is configured to generate one.

### 2. Get a Book by Title

**Request**

```http
GET http://localhost:8080/book/v1/getBook/The%20Secret
```

The title is passed through the `{name}` path variable and looked up by the service using `findBookByTitle(name)`. URL-encode spaces and special characters.

Expected response: `200 OK` with the matching book, if found. If no matching book exists, the current implementation may return a null body unless not-found handling has been added.

### 3. Update a Book

**Request**

```http
PUT http://localhost:8080/book/v1/updateBook
Content-Type: application/json
```

**Body — example assumes numeric ID `1`**

```json
{
  "id": 1,
  "title": "The Secret",
  "author": "Rhonda Byrne",
  "genre": "Personal Development"
}
```

The current service calls `bookRepository.save(book)`. If the ID belongs to an existing record, JPA generally updates that record; if the ID is new or does not exist, `save()` may insert a new record instead.

**Important:** Select `PUT` in Postman. Ensure the ID data type matches the `Book` entity (for example, use `1` for a numeric ID rather than `"1"`).

For a production-ready update operation, load the existing book by ID, return `404 Not Found` if it does not exist, update its fields, and save it.

## Recommended CRUD Extensions

The following endpoints are useful additions, but they are **not confirmed in the controller code currently shared**.

| Operation | Method | Suggested endpoint |
|---|---|---|
| Get all books | `GET` | `/book/v1/books` |
| Get a book by ID | `GET` | `/book/v1/books/{id}` |
| Update by ID | `PUT` | `/book/v1/books/{id}` |
| Delete by ID | `DELETE` | `/book/v1/books/{id}` |

A conventional REST design uses resource-based URLs such as `PUT /book/v1/books/1` and verifies that the requested book exists before updating it.

## Testing with Postman

1. Start the Spring Boot application.
2. Create a request and select the HTTP method listed above.
3. Enter the complete URL.
4. For `POST` and `PUT`, choose **Body → raw → JSON**.
5. Send the request and inspect the status code and response body.
6. Verify the saved or updated record in the database.

## Common Issues

- **404 Not Found:** Check the URL, controller mappings, port, and whether the application was restarted after code changes.
- **405 Method Not Allowed:** Use the method declared by the controller (`POST`, `GET`, or `PUT`).
- **400 Bad Request:** Ensure JSON field names and data types match the `Book` entity.
- **Book not found:** Confirm that the title or ID exists in the database.
- **Update creates a new record:** Verify the ID exists and consider implementing update-by-ID logic that loads the existing record first.

## Future Improvements

- Implement `GET` all books, `GET` by ID, and `DELETE` endpoints.
- Use DTOs for request and response models.
- Add validation with `jakarta.validation`.
- Add a global exception handler with consistent error responses.
- Add pagination, sorting, and search filters.
- Add unit and integration tests using JUnit and Mockito.
- Document APIs with Swagger / OpenAPI.
