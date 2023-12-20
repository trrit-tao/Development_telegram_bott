FROM openjdk:17
EXPOSE 8080
WORKDIR /opt/app
COPY build/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]