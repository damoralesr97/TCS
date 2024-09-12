FROM openjdk:17-jdk-slim
EXPOSE 8180
COPY build/libs/cliente-persona-0.0.1-SNAPSHOT tcs-cliente-persona.jar
ENTRYPOINT ["java", "-jar", "/tcs-cliente-persona.jar"]