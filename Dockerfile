 FROM openjdk:12-alpine

 COPY target/kafka-producer*-jar-with-dependencies.jar /kafka-producer.jar

 CMD [ "java", "-jar", "/kafka-producer.jar"]