
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import serialize.CustomObject;


public class CustomProducer {
    public static void main(String[] args) {
        runProducer();
    }


    static void runProducer() {
        org.apache.kafka.clients.producer.Producer<String, CustomObject> producer = ProducerCreator.createProducer();


        for (int i = 0; i < 20; i++) {
            CustomObject value = new CustomObject(" ionut" + i, " vacariu " + i, 25 + i);
            ProducerRecord<String, CustomObject> record = new ProducerRecord<>(IKafkaConstants.TOPIC_NAME, "key", value);

            try {
                Future<RecordMetadata> send = producer.send(record, new DemoProducerCallback());
                RecordMetadata recordMetadata = send.get();
                System.out.println(" Blea am facut publish " + record);

            } catch (ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}

class DemoProducerCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
    }
}