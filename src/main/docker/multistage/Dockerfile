FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -Dquarkus.package.type=fast-jar

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/*.jar /app/
COPY --from=build /app/target/quarkus-app/app/ /app/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /app/quarkus/
COPY --from=build /app/target/quarkus-app/quarkus-run.jar /app/quarkus-run.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/quarkus-run.jar"]
