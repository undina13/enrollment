FROM amazoncorretto:11-alpine-jdk
COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]