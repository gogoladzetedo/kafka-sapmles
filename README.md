# kafka-sapmles
Repository for sample applications of Kafka and kafka streams 

**TweetsProducer**

Application for producing the twitter tweets into the kafka topic on real-time.
- `KafkaConfig` class defines the Kafka Producer properties. Class has set the base setting, however the real-world projects will requires full properties of configuration.

- `TwitterClient` class creates a Twitter client and authentication of Twitter developer account with hosebird client.

- `Producer` class creates a run function that creates Kafka producer based on `KafkaConfig`, then connects to Twitter using the `TwitterClient` class configuration and produces the real-time streaming of the tweets based on the inputted search terms in the main function class. 


**TweetsStreamFilter**

Application for a real-time filtering of the incoming tweets. It consumes the twitter tweets data, checks the posts that have been retweeted from verified and popular sources, and then pushes them to a new Kafka topic.

- `KafkaStreamsConfig` class defines the base configuration of Kafka Streams properties and streams builder.

- `Parser` class has functionalities to parse and fetch the needed data from inputted Json.

- `FilterStream` class runs the KafkaStreams application, takes the input topic of tweets as the data source, filters the messages in real-time by parsing the record json values, in this case - while subscribed to certain Twitter tweet records, it checks that record is retweet, retweet source user has `verified` flag and more than 10k followers, and the actual tweet user has more than 500 followers. Tweet records that meet the given criteria will be pushed to an output Kafka topic.


