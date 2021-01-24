import com.google.gson.JsonParser;

public class Parser {
    private static final JsonParser jsonParser = new com.google.gson.JsonParser();

    public Integer parseFollowersCount(String tweetJson) {
        try {
            return jsonParser.parse(tweetJson)
                    .getAsJsonObject()
                    .get("user")
                    .getAsJsonObject()
                    .get("followers_count")
                    .getAsInt();
        } catch (NullPointerException e) { return -1;}
    }

    public Long parseRetweetId(String tweetJson) {
        try {
            return jsonParser.parse(tweetJson)
                    .getAsJsonObject()
                    .get("retweeted_status")
                    .getAsJsonObject()
                    .get("id")
                    .getAsLong();
        } catch (NullPointerException e) { return Long.valueOf(-1);}
    }

    public boolean  parseRetweetUserVerified(String tweetJson) {
        try {
            return jsonParser.parse(tweetJson)
                    .getAsJsonObject()
                    .get("retweeted_status")
                    .getAsJsonObject()
                    .get("user")
                    .getAsJsonObject()
                    .get("verified")
                    .getAsBoolean();
        } catch (NullPointerException e) { return false; }
    }

    public Integer  parseRetweetUserFollowersCount(String tweetJson) {
        try {
            return jsonParser.parse(tweetJson)
                    .getAsJsonObject()
                    .get("retweeted_status")
                    .getAsJsonObject()
                    .get("user")
                    .getAsJsonObject()
                    .get("followers_count")
                    .getAsInt();
        } catch (NullPointerException e) { return -1; }
    }
}
