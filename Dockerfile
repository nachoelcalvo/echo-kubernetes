#Alpine library
FROM openjdk:8-jre-alpine
MAINTAINER jcasado

ENV ECHO_USER="echo_user"
ENV ECHO_GROUP=$ECHO_USER

#Required by Tomcat
VOLUME /mytmp

WORKDIR /opt/echo
COPY target/echo-0.0.1.jar echo.jar

ENTRYPOINT ["java", "-jar", "echo.jar"]

RUN addgroup -S $ECHO_USER && adduser -S -G $ECHO_GROUP $ECHO_USER
USER $ECHO_USER

EXPOSE 8080