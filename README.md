# kafka-sapmles
Repository for sample applications of Kafka and kafka streams 

**TweetsProducer**

Application for producing the twitter tweets into the kafka topic on real-time.
- `KafkaConfig` class defines the Kafka Producer properties. Class has set the base setting, however the real-world projects will requires full properties of configuration.

- `TwitterClient` class creates a Twitter client and authentication of Twitter developer account with hosebird client.

- `Producer` class creates a run function that creates Kafka producer based on `KafkaConfig`, then connects to Twitter using the `TwitterClient` class configuration and produces the real-time streaming of the tweets based on the inputted search terms in the main function class. 



