package network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Nutrionix {

    private HttpsURLConnection connection;
    private String appId = "d945959f";
    private String apiKey = "2106962a026217738b1c97202cdf130a";

    //quoted from: https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
    public String searchFood(String query) {
        try {
            URL url = new URL("https://trackapi.nutritionix.com/v2/search/instant?query=grilled cheese");
            setGetConnection(url, "Accept", "*/*");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));
            String output;
            StringBuilder builder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                builder.append(output);
            }
            connection.disconnect();
            return builder.toString();
            //return parseJsonToString(builder.toString());
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
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //connection.setRequestProperty("User-Agent", "PostmanRuntime/7.19.0");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0"
                + " (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setRequestProperty(requestPropertykey, requestPropertyValue);
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setRequestProperty("x-app-id", "d945959f");
        connection.setRequestProperty("x-app-key", "2106962a026217738b1c97202cdf130a");
        //connection.setRequestProperty("x-remote-user-id","0");


        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode() + connection.getResponseMessage());
        }
    }


}


