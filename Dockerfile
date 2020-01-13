FROM openjdk:8
ADD ./target/Forum-0.0.1-SNAPSHOT.jar Forum-0.0.1-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "Forum-0.0.1-SNAPSHOT.jar"]