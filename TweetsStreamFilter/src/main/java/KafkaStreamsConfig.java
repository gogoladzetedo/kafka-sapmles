import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import java.util.Properties;

public class KafkaStreamsConfig {

    private final Properties properties = new Properties();
    private String bootstrapServer;
    private String applicationId;
    private final StreamsBuilder streamsBuilder = new StreamsBuilder();

    public StreamsBuilder getStreamsBuilder(){
        return this.streamsBuilder;
    }
    public void setBootstrapServer(String inputBootstrapServer){this.bootstrapServer = inputBootstrapServer; }
    public void setApplicationId(String inputApplicationId){this.applicationId = inputApplicationId; }

    private Properties getProperties() {
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        return properties;
    }


    public KafkaStreams createKafkaStreams(){
        return new KafkaStreams(streamsBuilder.build(), getProperties());
    }

}
