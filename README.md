# Echo sample project

It implements a domain-centric "Hexagonal" approach of a common web application with Java and Spring Boot.

# Prerequisite

  - Java 11
  - Lombok

# Start the server 

```shell
./mvnw -pl echo-infrastructure -am spring-boot:run
```

# Run the test suites

```shell
./mvnw test
```

# Generate fat jar

```shell
./mvnw clean install
```
