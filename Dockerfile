FROM eclipse-temurin:17
LABEL maintainer="victorllunes@gmail.com"
WORKDIR /app
COPY target/Lommebok-0.0.1-SNAPSHOT.jar /app/Lommebok.jar
ENTRYPOINT ["java", "-jar", "Lommebok.jar"]
