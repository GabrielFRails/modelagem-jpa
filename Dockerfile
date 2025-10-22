FROM maven:4.0.0-rc-4-ibm-semeru-25-noble AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests  # Garante que o build funcione mesmo sem testes

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/modelagem-orm.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]