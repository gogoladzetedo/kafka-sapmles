import org.apache.kafka.streams.kstream.KStream;

public class FilterStream {
    public FilterStream(){}
    public static void main(String[] args) {
        new FilterStream().run("twitter_tweets", "important_tweets"
                ,"kafka-streams-app", "127.0.0.1:9092");
    }

    public void run(String inTopic, String outTopic, String appId, String bootstrapServer){
        KafkaStreamsConfig streamsConfig= new KafkaStreamsConfig();
        Parser jsonParser = new Parser();

        KStream<String, String> inputTopic = streamsConfig.getStreamsBuilder()
                .stream(inTopic);

        KStream<String, String> filteredStream = inputTopic.filter((k, jsonTweet) ->
                jsonParser.parseRetweetUserVerified(jsonTweet) &
                jsonParser.parseRetweetUserFollowersCount(jsonTweet) > 10000 &
                jsonParser.parseFollowersCount(jsonTweet) > 1000
        );
        filteredStream.to(outTopic);

        streamsConfig.setApplicationId(appId);
        streamsConfig.setBootstrapServer(bootstrapServer);
        streamsConfig.createKafkaStreams().start();
    }
}
