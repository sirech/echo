FROM adoptopenjdk/openjdk14:jre-14.0.1_7-alpine

WORKDIR /app
EXPOSE 4000

RUN apk add --update --no-cache dumb-init \
  && rm -rf /var/cache/apk/*

COPY build/libs/echo.jar .

RUN adduser -D runner

USER runner
ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD ["java", "-jar", "echo.jar"]
