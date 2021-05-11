## Data integration

Data integration service (DIS) serves for the simplification of transfer of customer’s data into PB.
DIS exposes the RESTful endpoint `/api/asset/sento-to-ledger` (media type: `application/json`, http method: `POST`) that is capable of receiving asset’s data and propagate them to the distributed ledger of PB.
This endpoint accepts an array of assets and after validation send those to a specific tenant kafka topic.

## API (backend)

Java [Spring framework](https://docs.spring.io/spring-framework/docs/current/javadoc-api/) is used as a server side solution.

### Build

Build tool is [maven](https://maven.apache.org/) so application can be built  with `mvn clean install`. 

### Configuration

General configuration is defined in `src/main/resources/application.properties`.

#### application.properties

```
# SPRING
spring.main.allow-bean-definition-overriding=true
spring.devtools.add-properties=false
springdoc.api-docs.path=/api-docs

# LOGGING
logging.level.org.apache.http=info

# HTTP
server.port=8080
server.servlet.context-path=/api
server.http2.enabled=true
server.maxHttpHeaderSize=102400

# SECURITY
keycloak.auth-server-url= **KEYCLOAK URL**

# KAFKA
kafka.bootstrapAddress= **kakfa host**
ledger.gateway=kafka
airs.gateway=kafka
```
