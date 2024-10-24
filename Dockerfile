FROM maven:3.8.5-openjdk-17 as builder

WORKDIR /app

COPY pom.xml .
COPY ./src ./src

RUN mvn clean package

FROM quay.io/keycloak/keycloak:25.0.1

COPY --from=builder /app/target/*.jar /opt/keycloak/providers/

COPY ./src/main/resources/themes/ /opt/keycloak/themes/

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]
