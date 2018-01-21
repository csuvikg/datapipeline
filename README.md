## Data processing pipeline
The project is an experiment to implement a data processing pipeline using Spring
Boot and a consumer that can be fed through REST endpoints. Websockets are used
to broadcast the changes to the listeners.

### Contents
 * [Starting the application](#starting-the-application)
 * [REST endpoints](#rest-endpoints)
     * [Create payload example](#example-payload-for-message-creation)
 * [Known issues](#known-issues)
 * [Possible future development directions](#possible-future-directions)
 
### Starting the application
Before starting the application, make sure that you have the `docker-compose.yml`
running, as it provides the local MySQL and Redis instances. To run it, have Docker
installed and use the `docker-compose -f docker-compose.yml up` command.

The application can be started with the included Gradle wrapper. For that, use
the `./gradlew bootRun` command. The index page will be available at `http://localhost:8080`.
For information on the REST endpoints, see the relevant section.

### REST endpoints
| Endpoint         | HTTP Verb | Function                                                                 |
|:-----------------|:----------|:-------------------------------------------------------------------------|
| /actuator/health | GET       | Simple API health check.                                                 |
| /api/messages    | GET       | List messages from database.                                             |
| /api/messages    | POST      | Create message. Consumes JSON with the property of content. (See below.) |

#### Example payload for message creation
See below for an example request body for a `POST /api/messages`.
```json
{
    "content": "Message text"
}
```

### Known issues
 * The posting of a new message does not push the content to the WebSocket.
 This is the result of me not being able to properly invoke a send method to
 the correct target. However, I managed to use the configuration to invoke
 the said method from the other controller directly, through the front end
 interface.

### Possible future directions
 * As the project used technologies I am less familiar with, I did not manage
 to get it tested due to the available time - I did not manage to learn how to
 test the libraries I used. However, I tried using coding concepts that help
 code testability. (Like decoupling configuration beans, using Spring context
 for configuration, etc.). This could be a future direction if code is planned
 to be used and extended.
 * The missing feature/known issue should be implemented.