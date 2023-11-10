FROM openjdk:17
ADD target/encurtador-docker-compose.jar encurtador-docker-compose.jar
ENTRYPOINT ["java","-jar","encurtador-docker-compose.jar"]