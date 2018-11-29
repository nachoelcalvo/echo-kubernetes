#Alpine library
FROM openjdk:8-jre-alpine
MAINTAINER jcasado

#Required by Tomcat
VOLUME /tmp

WORKDIR /opt/echo
COPY target/echo-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080

