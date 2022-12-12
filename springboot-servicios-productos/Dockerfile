FROM openjdk:12
VOLUME /tmp
#EXPOSE 8761 No va, porque Productos usa puerto random
ADD ./target/springboot-servicios-productos-0.0.1-SNAPSHOT.jar servicio-productos.jar
ENTRYPOINT [ "java","-jar","/servicio-productos.jar" ]