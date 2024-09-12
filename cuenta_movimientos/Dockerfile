FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY build/libs/cuenta_movimientos-0.0.1-SNAPSHOT.jar tcs-cuenta-movimientos.jar
ENTRYPOINT ["java", "-jar", "/tcs-cuenta-movimientos.jar"]