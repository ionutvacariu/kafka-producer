# Overview
This [Producer](/src/main/java/CustomProducer.java) write messages in "transactions" topic from kafka. If topic does not exists, will create it.

[Payment2](/src/main/java/Payment2.java) is generated with [avro-tool](avro-tools.jar) jar file with the following command:
`java -jar avro-tools.jar compile schema ./src/main/avro/bigavro.asvc /src/main/java/` which takes bigavro.asvc file and generate java file.  


For an example of how to use this Docker setup, refer to the [Confluent Platform quickstart](https://docs.confluent.io/current/quickstart/index.html?utm_source=github&utm_medium=demo&utm_campaign=ch.examples_type.community_content.cp-all-in-one)

#Prerequisites
[Confluent kafka is started locally](https://github.com/ionutvacariu/confluent-dockers) 