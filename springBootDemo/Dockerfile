
FROM openjdk:17

WORKDIR /app

ENV JAVA_HOME=C:/Users/Евгения/.jdks/ms-17.0.15

COPY target/authorization-service-1.0.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]