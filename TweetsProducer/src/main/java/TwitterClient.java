import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class TwitterClient {
    private String consumerKey = "cqPLaZuorTecXHmg6yj6Tk6pb";
    private String consumerSecret = "snNiTMcZEjyRPLlA8WAeRcnTEgS5tQqb5Vv1UbhH7beN1NLLP4";
    private String token = "1340604898161864705-PmqrSzpDzYyDSFXACpT13oepcpZHnC";
    private String tokenSecret = "S5oHOWagCCfKvJAxdb1t41EQatZP9OnpE3LptTSVhW9oF";
    final Hosts hbHosts = new HttpHosts(Constants.STREAM_HOST);
    final StatusesFilterEndpoint hbEndpoint = new StatusesFilterEndpoint();
    final Authentication hbAuth = new OAuth1(consumerKey, consumerSecret, token, tokenSecret);
    private BlockingQueue<String> msgQueue;
    private List<String> searchterm;

    public void setAuth(String consumerKey, String consumerSecret, String token, String tokenSecret){
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.token = token;
        this.tokenSecret = tokenSecret;
    }
    public void setSearchTerm(List<String> inputSearchterm){ this.searchterm = inputSearchterm; }
    private void setHosebirdEndpoint(List<String> searchTerm){hbEndpoint.trackTerms(searchTerm); }
    public void setMsgQueue(BlockingQueue<String> msgQueue){this.msgQueue = msgQueue; }
    private void setBuilder(ClientBuilder builder){
        setHosebirdEndpoint(searchterm);
        builder
                .name("my-hosebird-client")
                .hosts(hbHosts)
                .authentication(hbAuth)
                .endpoint(hbEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));
    }

    public Client getHosebirdClient(){
        ClientBuilder builder = new ClientBuilder();
        setBuilder(builder);
        return builder.build();
    }
}
