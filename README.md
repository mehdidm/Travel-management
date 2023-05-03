# Travel Management Microservices :world_map:

This is a set of microservices for managing travel reservations and bookings. The microservices included in the system are:

- :hotel: `hotel-ms`: Manages hotel reservations
- :detective: `fraud-ms`: Detects fraudulent bookings


The hotel reservation system that allows users to make and cancel reservations. The system has two microservices, namely the `hotel-ms` service and the `fraud-ms` service. The `hotel-ms` service is responsible for creating and managing reservations, while the `fraud-ms` service is responsible for detecting fraudulent reservations. When a reservation is made, the `hotel-ms` service sends a message to the `fraud-ms` service to check if the reservation is fraudulent. If it is, the `fraud-ms` service cancels the reservation.


## Technologies Used :computer:
The system uses the following technologies:

- Java
- Spring Boot
- Docker
- PostgreSQL
- RabbitMQ
- Zipkin
- Eureka Server


## Installation :gear:
To install the system, follow these steps:

1. Clone the repository to your local machine.
2. Install Docker and Docker Compose on your machine.
3. Create the necessary databases in PostgreSQL for each microservice.
4. Create a queue for each microservice in RabbitMQ.
5. Create an exchange named fraudExchange in RabbitMQ.
6. Bind the reservation queue to the fraudExchange exchange using the cancelReservation routing key.
7. Navigate to the root directory of the project and run the following command to start the system:

    ```
    docker-compose up
    ```
8. Access the microservices using the following ports:

- apigateway: 8080
- hotel-ms: 8081
- fraud-ms: 8082
- trips-ms: 8083
- agent-ms: 8084
- trip-ms: 8085

9. Access the Eureka Server using the following port:

- eureka server: 8761

## Usage :rocket:

To use the system, make HTTP requests to the apigateway service, which acts as the API gateway for the system.

The `hotel-ms` service creates and manages reservations, while the `fraud-ms` service detects fraudulent reservations. RabbitMQ is used as a message broker to communicate between the two microservices.

When a reservation is made, the `hotel-ms` service sends a message to the `fraud-ms` service to check if the reservation is fraudulent. If it is, the `fraud-ms` service cancels the reservation.

Zipkin is used to monitor and trace the requests between the microservices.


## License :page_facing_up:
This project is licensed under the MIT License.


