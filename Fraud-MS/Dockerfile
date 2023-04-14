FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory
WORKDIR /app

# Copy the jar file to the container
COPY target/Fraud-MS-0.0.1-SNAPSHOT.jar /app/

# Set the user to run the container
USER nobody:nobody

# Expose the port
EXPOSE 8081

# Set the entrypoint
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/app/Fraud-MS-0.0.1-SNAPSHOT.jar"]
