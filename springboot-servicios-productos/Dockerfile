FROM openjdk:12
VOLUME /tmp
#EXPOSE 8761 No va, porque Productos usa puerto random
ADD ./target/springboot-servicios-eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT [ "java","-jar","/eureka-server.jar" ]