FROM java:11
EXPOSE 8888
ARG JAR_FILE=build/libs/mentor-api-0.0.1-SNAPSHOT_20220225.jar.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]