# Coding Challenge
## What's Provided
A SpringBoot web application that integrates with PostgreSQL database and uses Docker to run in a container. The application contains REST API that would allow users to perform CRUD operations on a data model representing an e-commerce platform. The REST API is defined using OpenAPI.

### How to Run
Clone the project by executing ```git clone https://github.com/apriltm/e-commerce-application.git``` or download and unzip. Please check if in the correct path of the project folder and execute the following commands:
#### Running Dockerfile
If running the Dockerfile, use the commands below:
```aidl
> docker build -t springbootapp -f Dockerfile . 
> docker run -d --name springbootapp -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres
```
#### Running Docker Compose
If running Docker Compose, use the commands below:
```aidl
> docker compose up -d --build
```
#### Connecting to PostgresQL
Once you are done running either Dockerfile or Docker Compose, execute the following commands:
```aidl
> docker exec -it springbootapp bash
> psql -h localhost -U postgres

> \c ecommerce_db;
```

Once connected, start up the application named CodingChallengeApplication and enter <http://localhost:8080/swagger-ui/index.html#/> into the browser.

The application also may be executed by running `mvn spring-boot:run`.

### How to Use
The following endpoints are available to use:

#### Product

```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/api/products
    * PAYLOAD: Product
    * RESPONSE: Product
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/api/products/{id}
    * RESPONSE: Product
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/api/products
    * PAYLOAD: Product
    * RESPONSE: Product
* DELETE
    * HTTP Method: DELETE
    * URL: localhost:8080/api/products/{id}
    * PAYLOAD: Product
```

#### Order

```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/api/orders
    * PAYLOAD: Order
    * RESPONSE: Order
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/api/orders/{id}
    * RESPONSE: Order
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/api/orders
    * PAYLOAD: Order
    * RESPONSE: Order
* DELETE
    * HTTP Method: DELETE
    * URL: localhost:8080/api/orders/{id}
    * PAYLOAD: Order
```

#### Order Item

```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/api/order-items
    * PAYLOAD: OrderItem
    * RESPONSE: OrderItem
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/api/order-items/{id}
    * RESPONSE: OrderItem
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/api/order-items
    * PAYLOAD: OrderItem
    * RESPONSE: OrderItem
* DELETE
    * HTTP Method: DELETE
    * URL: localhost:8080/api/order-items/{id}
    * PAYLOAD: OrderItem
```

The schemas are listed on the OpenAPI specification. Various payloads are available to be executed.

### How to Test

Test classes have been written using Mockito and Junit are automated. Located under ```src/test/java/com/example/codingchallenge```, they are readily available to be executed.