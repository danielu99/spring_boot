FROM openjdk:12
VOLUME /tmp
EXPOSE 8887
ADD ./target/springboot-servicios-config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT [ "java","jar","/config-server.jar" ]