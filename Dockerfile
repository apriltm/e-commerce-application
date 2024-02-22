FROM amazoncorretto:21
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} springbootapp.jar
ENTRYPOINT ["java","-jar","/springbootapp.jar"]
