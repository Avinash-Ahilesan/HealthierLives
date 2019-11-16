package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class KanyeQuoteFetcher {

    private HttpURLConnection connection;
    private static KanyeQuoteFetcher kanyeQuoteFetcher;

    private KanyeQuoteFetcher() {

    }

    public static KanyeQuoteFetcher getInstance() {
        if (kanyeQuoteFetcher == null) {
            return new KanyeQuoteFetcher();
        }
        return kanyeQuoteFetcher;
    }

    //quoted from: https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
    public String getQuote() {
        try {
            URL url = new URL("https://api.kanye.rest/?format=json");
            setGetConnection(url, "Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));
            String output;
            StringBuilder builder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                builder.append(output);
            }
            connection.disconnect();
            return parseJsonToString(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fetcher Not Working";
    }

    private String parseJsonToString(String str) {
        JsonObject json = new Gson().fromJson(str, JsonObject.class);
        String quote = json.get("quote").toString();

        return quote;
    }

    private void setGetConnection(URL url, String requestPropertykey, String requestPropertyValue)
            throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) "
                + "AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setRequestProperty(requestPropertykey, requestPropertyValue);
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode() + connection.getResponseMessage());
        }
    }


}
