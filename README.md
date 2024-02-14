# SensorSync Fleet MQTT Client

The SensorSync Fleet MQTT Client is a microservice designed to handle MQTT communication for the fleet management service. This service establishes a connection to a broker and subscribes to the lifecycle topic, where all connected IoT devices publish their data. The service then processes this data, mapping it to a message Data Transfer Object (DTO), and exposes these messages through an API created with Spring Boot.

## Functionality

- Establishes MQTT connection to broker.
- Subscribes to the lifecycle topic to receive data from connected IoT devices.
- Processes incoming data and maps it to message DTOs.
- Exposes messages through an API built with Spring Boot.

## Usage

1. Ensure the necessary configurations are set up for MQTT connection.
2. Deploy the microservice.
3. The service will automatically connect to the MQTT broker and start receiving messages from IoT devices.
4. Access the messages through the provided API endpoints.

## Technologies Used

- MQTT for messaging protocol.
- Spring Boot for API development.
- Java for implementation.

## Installation

1. Clone the repository.
2. Install required dependencies.
3. Configure the MQTT broker details.
4. Build and deploy the service.

## API Endpoints

- `/messages`: Retrieves messages received from IoT devices.