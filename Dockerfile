FROM eclipse-temurin:21-jdk-jammy
ARG JAR_FILE=target/virtual-wallet-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app-virtual-wallet-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-virtual-wallet-server.jar"]