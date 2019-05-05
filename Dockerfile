FROM openjdk:8-jre-alpine

COPY /target/MySpring.jar /

CMD ["java", "-jar", "MySpring.jar"]
