FROM openjdk:11
COPY target/movies-0.0.1-SNAPSHOT.jar movies-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/movies-0.0.1-SNAPSHOT.jar"]