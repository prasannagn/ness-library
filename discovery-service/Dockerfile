FROM openjdk:8-jdk-alpine
EXPOSE 8761
VOLUME /tmp
ADD target/discovery-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Dspring.profiles.active=docker"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar