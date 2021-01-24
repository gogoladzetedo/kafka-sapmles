import com.google.common.collect.Lists;
import com.twitter.hbc.core.Client;
import org.apache.kafka.clients.producer.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer {
    public Producer(){}
    public static void main(String[] args) {
    final List<String> searchTerms = Lists.newArrayList("TSLA", "TESLA", "Musk", "COVID");
        final String kafkaTopic = "twitter_tweets";
        final String bootstrapServer = "127.0.0.1:9092";

        new Producer().run(searchTerms, kafkaTopic, bootstrapServer);
    }

    public void run(List<String> searchTerms, String kafkaTopic, String bootstrapServer){
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(1000);

        // Connect a Twitter client
        TwitterClient twClient = new TwitterClient();
        twClient.setSearchTerm(searchTerms);
        twClient.setMsgQueue(msgQueue);
        Client client = twClient.getHosebirdClient();
        client.connect();

        // Create Kafka configuration
        KafkaConfig config = new KafkaConfig();
        config.setBootstrapServer(bootstrapServer);
        KafkaProducer<String, String> producer = config.createProducer();

        Runtime.getRuntime().addShutdownHook(new Thread( ()-> { client.stop(); producer.close(); }));

        while (!client.isDone()) {
            String msg = null;
            try { msg = msgQueue.poll(3, TimeUnit.SECONDS); }
            catch (InterruptedException e) {e.printStackTrace(); client.stop(); }
            if (msg != null){producer.send(new ProducerRecord<>(kafkaTopic, null, msg)); }
        }
    }
}
