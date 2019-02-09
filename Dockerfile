FROM openjdk:8-jre-alpine3.9

WORKDIR /app

COPY build/libs/echo.jar .
