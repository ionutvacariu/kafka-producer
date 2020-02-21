call mvn clean
call mvn install
call docker build -t ionut/kafka-producer .
call docker run ionut/kafka-producer