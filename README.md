# Cardzy - Card Issuance Platform

Cardzy is a Java-based application designed to issue cards to clients. The application is divided into microservices, communicates asynchronously using RabbitMQ, and implements authentication through Keycloak.

## Features

- **Client Management**: Save and manage client information.
- **Card Issuance**: Issue cards for clients.
- **Client Rating**: Allows clients to be rated based on specific criteria.
- **Card Assignment**: Assign cards to specific clients.
- **Microservice Architecture**: The application is broken into multiple microservices
- **Asynchronous Communication**: Uses RabbitMQ to handle communication between microservices.
- **Authentication**: Uses Keycloak for secure authentication.

## Architecture Overview

Cardzy is structured into several microservices that work together to manage clients and issue cards. The application follows an asynchronous communication model using RabbitMQ for message brokering between services. Authentication is managed through Keycloak

### Some Components:
- **Client Service**: Responsible for saving and managing client data.
- **Card Service**: Responsible for issuing cards and managing card-related actions.
- **Rating Service**: Manages customer evaluations and ratings.
- **Auth Service**: Handles authentication and authorization using Keycloak.
- **Message Broker**: RabbitMQ is used for asynchronous communication between services.

## Technologies Used

- **Java**: The core programming language for the microservices.
- **RabbitMQ**: For asynchronous messaging between services.
- **Keycloak**: For authentication and authorization.
- **Docker**: For containerization of services.
- **Spring Boot**: Framework used for building the microservices.