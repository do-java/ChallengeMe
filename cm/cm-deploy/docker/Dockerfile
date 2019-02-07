FROM openjdk:8
ADD cm-app/target/cm-app-1.0.0-SNAPSHOT.jar cm-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=production", "-jar", "cm-app.jar"]

# Usage:
#
# docker run --name cm-postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -d postgres
# V1: docker exec -it cm-postgres bash
#    psql -U postgres postgres
#	 create database cm;
# V2: docker run -it --link cm-postgres:postgres --rm postgres sh -c 'exec psql -h "$POSTGRES_PORT_5432_TCP_ADDR" -p "$POSTGRES_PORT_5432_TCP_PORT" -U postgres'
#
# persistence.properties/spring.datasource.url=jdbc:postgresql://cm-postgres:5432/cm
# cm$ mvn clean install
# cm$ docker build -f cm-deploy/docker/Dockerfile -t cm-app .
# V1: cm$ docker-compose -f cm-deploy/docker/docker-compose.yml up
# V2: cm$ docker run -it --name cm-app-container --link cm-postgres:cm-postgres -p 8080:8080 -d cm-app
