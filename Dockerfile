# Set the base image to use
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle wrapper files into the container
COPY gradlew .
COPY gradle gradle

# Copy the build.gradle and settings.gradle files into the container
COPY build.gradle .
COPY settings.gradle .

# Copy the source code into the container
COPY src src

# Build the application using the Gradle wrapper
RUN chmod +x gradlew && \
    ./gradlew clean build -x test

# Set the entrypoint for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/build/libs/exam-0.0.1-SNAPSHOT.war"]