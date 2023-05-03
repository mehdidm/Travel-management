# Travel Management Microservice with Spring Boot

This is a microservice-based application developed with Spring Boot framework, designed to manage travel reservations and prevent fraudulent activities. This application consists of three microservices - Reservation, Fraud, and API Gateway - and utilizes Keycloak for secure authentication and authorization. The Eureka Server is also used for service registration and discovery.

## Microservices

### Reservation Microservice

This microservice is responsible for managing travel reservations. It receives requests from clients and communicates with other microservices to perform necessary actions. The Reservation microservice uses a MySQL database to store reservation-related data. 

### Fraud Microservice

This microservice is responsible for preventing fraudulent activities by analyzing reservation requests and determining their risk level. It receives reservation data from the Reservation microservice and uses machine learning algorithms to determine the likelihood of fraud. 

### API Gateway

The API Gateway microservice acts as a gateway for external clients to access the Reservation and Fraud microservices. It provides a unified interface for clients and routes requests to the appropriate microservice based on the request path. The API Gateway also provides security through Keycloak integration and rate limiting.

### Eureka Server

The Eureka Server is used for service registration and discovery. It allows microservices to register themselves and discover other services in the network. This enables dynamic scaling and failover of microservices.

## Security

This application uses Keycloak for secure authentication and authorization. Keycloak is an open-source identity and access management solution that provides features such as single sign-on, user management, and role-based access control. The API Gateway is responsible for communicating with Keycloak to validate client requests and authorize access to protected resources.
