import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class CustomPartitioner implements Partitioner {
    private static final int PARTITION_COUNT = 2;

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos =
                cluster.availablePartitionsForTopic(topic);

        for (PartitionInfo po : partitionInfos){
            System.out.println(po.toString());
        }
        Integer hCode = key.hashCode();
        return hCode % PARTITION_COUNT;
    }

    @Override
    public void close() {
    }
}