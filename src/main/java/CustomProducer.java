import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;


public class CustomProducer {

    public static void main(String[] args) {
        runtProd2();
    }

    static void runtProd2() {
        Properties props = getProperties();
        KafkaProducer<String, Payment2> producer = new KafkaProducer<>(props);
        sendRequests(producer);
    }

    private static void sendRequests(KafkaProducer<String, Payment2> producer) {
        for (Integer orderId = 300; orderId < 350; orderId++) {
            final Payment2 payment = new Payment2(String.valueOf(orderId), 1000.00d + orderId * 100);
            final ProducerRecord<String, Payment2> record = new ProducerRecord<String, Payment2>(IKafkaConstants.TOPIC_NAME, payment.getId().toString(), payment);
            producer.send(record, new DemoProducerCallback());
        }
        // producer.flush();
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        String schemaRegURL = "http://localhost:8081/";
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegURL);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }
}

class DemoProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
        System.out.println(" Published record: " + recordMetadata);
    }
}