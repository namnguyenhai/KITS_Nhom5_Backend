FROM openjdk:17-jdk-alpine
COPY target/nhom5-0.0.1-SNAPSHOT.war app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

