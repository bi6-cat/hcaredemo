## Maven
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /src
COPY . .
RUN mvn install -DskipTests=true

## Jar
FROM eclipse-temurin:21-jre-alpine

COPY --from=build src/target/hcaredemo-0.0.1-SNAPSHOT.jar /run/hcaredemo-0.0.1-SNAPSHOT.jar

EXPOSE 8888 

ENV JAVA_OPTIONS="-Xmx2048m -Xms256m"
ENTRYPOINT java -jar $JAVA_OPTIONS /run/hcaredemo-0.0.1-SNAPSHOT.jar


