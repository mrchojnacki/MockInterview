FROM adoptopenjdk/openjdk17:alpine-jre

WORKDIR /app

COPY target/your-application.jar /app/your-application.jar

EXPOSE 8080

CMD ["java", "-jar", "MockInterview.jar"]