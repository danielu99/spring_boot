FROM openjdk:12
VOLUME /tmp
#EXPOSE 8761 No va, porque Productos usa puerto random
ADD ./target/springboot-servicio-usuarios-0.0.1-SNAPSHOT.jar servicio-usuario.jar
ENTRYPOINT [ "java","-jar","/servicio-usuario.jar" ]