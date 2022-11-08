FROM --platform=amd64 openjdk:17.0.2-oraclelinux8

COPY target/jwt-app.jar jwt-app.jar

ENTRYPOINT ["java","-jar","/jwt-app.jar"]