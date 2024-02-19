FROM openjdk:8-jdk-alpine
COPY . /opt
WORKDIR /opt
RUN ./gradlew clean build
CMD ["java", "-jar", "build/libs/chatmate-chats-0.0.1-SNAPSHOT.jar"]
