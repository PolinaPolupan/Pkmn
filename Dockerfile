# Build stage
FROM gradle:latest AS build

# Set working directory and copy project files
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Build the Spring Boot application
RUN gradle bootJar --no-daemon

# Final stage
FROM bellsoft/liberica-openjre-alpine:21

# Create a volume for temporary files
VOLUME /tmp

# Add a non-root user for better security
RUN adduser -S spring-user
USER spring-user

# Set the working directory for the application
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/

# Copy migration scripts into the app directory
COPY db ./db

# Run the application directly with the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
