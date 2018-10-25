FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="devfest"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file

# Add the application's jar to the container
ADD spring-boot-prometheus-example-0.0.1-SNAPSHOT.jar app.jar
# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
