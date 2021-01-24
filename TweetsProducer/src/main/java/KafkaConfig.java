import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaConfig {
    private String bootstrapServer;
    final Properties properties = new Properties();

    public void setBootstrapServer(String inputBootstrapServer){this.bootstrapServer = inputBootstrapServer; }
    private Properties getProperties(Properties properties){
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer .class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
    public KafkaProducer<String, String> createProducer(){
        return new KafkaProducer<>(getProperties(properties));
    }

}
