FROM openjdk:17-jdk-slim
COPY build/libs/downtimer-1.0.0.jar.jar downtimer.jar
ENTRYPOINT ["java", "-jar", "downtimer.jar"]
EXPOSE 8080
