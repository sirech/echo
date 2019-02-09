FROM openjdk:8-jre-alpine3.9

WORKDIR /app
EXPOSE 4000

COPY build/libs/echo.jar .

RUN adduser -D runner

USER runner
CMD ["java", "-jar", "echo.jar"]
