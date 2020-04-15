FROM openjdk:12-alpine
COPY target/16098338.jar /16098338.jar
CMD ["java", "-jar", "/16098338.jar"]