## Data processing pipeline
The project is an experiment to implement a data processing pipeline using Spring
Boot and a consumer that can be fed through REST endpoints. Websockets are used
to broadcast the changes to the listeners.

### Contents
 * [Starting the application](#starting-the-application)
 
### Starting the application
Before starting the application, make sure that you have the `docker-compose.yml`
running, as it provides the local MySQL and Redis instances. To run it, have Docker
installed and use the `docker-compose -f docker-compose.yml up` command.

The application can be started with the included Gradle wrapper. For that, use
the `./gradlew bootRun` command. The API will be running on `https://localhost:8080`.