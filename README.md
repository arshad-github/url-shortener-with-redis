## Execution instructions

### Run application
* Run Spring Boot application (main class -> ShortApplication.java)
* The application will run by default on port 8080
* The default port can be amended to 1000 for example by adding server.port=1000 to application.properties

### Testing
* Included in the project, postman collection shortUrl.postman_collection.json contains 2 POST requests 
* /encode converts a normal url to a short.com url
* /decode converts a short.com url to a normal url
* the input url needs to be passed as raw text within the request body for both endpoints
* ShortUrlControllerTest.java, ValidatorTest.java and HashTest.java are simple tests for the application's Controller, Url validation and Hashing.