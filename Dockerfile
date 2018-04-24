FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/echo-0.0.1.jar echo-0.0.1.jar
RUN sh -c 'touch /echo-0.0.1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/echo-0.0.1.jar"]