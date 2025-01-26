# Microsservice_Catalogo

## Overview

The **Microsservice_Catalogo** is a Java-based microservice built using [Spring Boot](https://spring.io/projects/spring-boot). This service provides functionality to manage catalog items, including details such as price, status, discounts, and more. The database used is designed to support storage of binary image data along with other textual and numeric fields.

---

## Features

- Create, read, update, and delete catalog items.
- Manage item attributes such as price, discounts, and descriptions.
- Store and retrieve images associated with catalog items.
- Cross-Origin Resource Sharing (CORS) enabled for `http://localhost:4200`.

---

## Prerequisites

1. **Java Development Kit (JDK)**: Version 17 or higher.
2. **Maven**: For dependency management and building the project.
3. **Database**: Any database supported by Spring Data JPA (e.g., MySQL, PostgreSQL).
4. **IDE**: IntelliJ IDEA, Eclipse, or any other Java-compatible IDE.

---

## Database Schema

| Column       | Type          | Constraints |
|--------------|---------------|-------------|
| ID           | binary(16)    | Primary Key |
| PRICE        | double        | Not Null    |
| STATUS       | varchar(50)   |     |
| DISCOUNTED   | varchar(50)   |     |
| DISCOUNT     | double        |     |
| NAME         | varchar(255)  | Not Null    |
| DESCRIPTION  | text          |     |
| IMAGE        | mediumblob    |     |

---

## Model

```java
@Data
@Entity
@Table(name = "Product")
public class CatalogoModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private UUID id;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DISCOUNTED")
	private String discounted;
	
	@Column(name = "DISCOUNT")
	private Double discount;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Lob
	@Column(name = "IMAGE", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
}
```

## Controller

The `CatalogoController` class defines endpoints for the API.

---

## Endpoints

### Base URL

```
POST
http://<host>:<port>/catalogo
```

### Endpoints Description

#### 1. Create a Catalog Item

**POST** `/product`

**Request Body:**
```json
{
    "price": 19.99,
    "status": "sale",
    "discounted": "discounted",
    "discount": 5.00,
    "name": "Sample Item",
    "description": "A sample catalog item.",
    "image": "<base64-encoded-image>"
}
```

**Response:**
- `201 Created`: When the item is successfully created.
- `400 Bad Request`: When validation fails.

#### 2. Get All Catalog Items

**GET** `/products`

**Response:**
```json
[
    {
        "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
        "price": 19.99,
        "status": "Available",
        "discounted": "Yes",
        "discount": 5.00,
        "name": "Sample Item",
        "description": "A sample catalog item.",
        "image": "<base64-encoded-image>"
    }
]
```

#### 3. Get a Catalog Item by NAME and PRICE

**GET** `/product/name?name=&price=`

**Response:**
- `200 OK`: Returns the catalog item.
- `404 Not Found`: When the item does not exist.

#### 4. Update a Catalog Item

**PUT** `/{id}`

**Request Body:**
```json
{
    "price": 25.99,
    "status": "Out of Stock",
    "discounted": "No",
    "discount": 0.00,
    "name": "Updated Item",
    "description": "An updated catalog item.",
    "image": "<base64-encoded-image>"
}
```

**Response:**
- `200 OK`: When the item is successfully updated.
- `404 Not Found`: When the item does not exist.

#### 5. Delete a Catalog Item

**DELETE** `/{id}`

**Response:**
- `204 No Content`: When the item is successfully deleted.
- `404 Not Found`: When the item does not exist.

---

## Running the Application

1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:
    ```bash
    cd microsservice_catalogo
    ```

3. Build the project using Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

5. Access the application:
    ```
    http://localhost:8080
    ```

---

## License

This project is licensed under the MIT License.




