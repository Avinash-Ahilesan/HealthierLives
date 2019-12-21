package network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class Nutrionix {

    private HttpsURLConnection connection;
    private String appId = ""; // appId
    private String apiKey = ""; // appKey

    //quoted from: https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
    public String findCaloriesBurned() {
        return "";
    }


    private JsonArray toJsonArrayFoodList(String str) {
        JsonObject json = new Gson().fromJson(str, JsonObject.class);

        JsonArray jsonArray = json.getAsJsonArray("branded");
        outputFoodList(jsonArray);

        return jsonArray;
    }

    private void outputFoodList(JsonArray jsonArray) {
        for (JsonElement object : jsonArray) {
            JsonObject jsonObject = object.getAsJsonObject();
            System.out.println(object);
            System.out.println(jsonObject.get("food_name"));
            System.out.println(jsonObject.get("serving_unit"));
            System.out.println(jsonObject.get("tag_name"));
        }
    }

    private void setGetConnection(URL url, String requestPropertykey, String requestPropertyValue)
            throws IOException {
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //connection.setRequestProperty("User-Agent", "PostmanRuntime/7.19.0");
        connection.setRequestProperty("x-app-id", "d945959f");
        connection.setRequestProperty("x-app-key", "2106962a026217738b1c97202cdf130a");
        connection.setRequestProperty("User-Agent", "PostmanRuntime/7.19.0");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Postman-Token",
                "4d75feee-61ae-404e-b0f5-ca62daf2b894,3ba89f84-3357-4bc2-b60e-4b8aefb70055");
        connection.setRequestProperty("Host", "trackapi.nutritionix.com");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("cache-control", "no-cache");
        //connection.setRequestProperty("x-remote-user-id","0");


        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode() + connection.getResponseMessage());
        }
    }

    //EFFECTS: returns the response from a specific query to the Nutrinoix
    //         search API
    public String getFood(String query) {
        OkHttpClient client = new OkHttpClient();
        query = query.replaceAll(" ", "%20");
        Request request = createFoodRequest(query);
        try {
            Response response = client.newCall(request).execute();
            toJsonArrayFoodList(response.body().string());
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //EFFECTS: returns the results from query as a list of NutrionixFoodResult objects
    public ArrayList<NutrionixFoodResult> getFoodResults(String query) {
        OkHttpClient client = new OkHttpClient();
        query = query.replaceAll(" ", "%20");
        Request request = createFoodRequest(query);
        try {
            Response response = client.newCall(request).execute();
            return extractNutrionixResult(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //EFFECTS: parses a response and extracts elements to create a NutrionixFoodResult
    private ArrayList<NutrionixFoodResult> extractNutrionixResult(String response) {
        JsonArray jsonArray = toJsonArrayFoodList(response);
        ArrayList<NutrionixFoodResult> results = new ArrayList<>();
        for (JsonElement object : jsonArray) {
            NutrionixFoodResult foodResult = new NutrionixFoodResult();
            JsonObject jsonObject = object.getAsJsonObject();

            foodResult.setFoodName(jsonObject.get("food_name").toString());
            foodResult.setCalories(jsonObject.get("nf_calories").toString());
            JsonObject imageResult = jsonObject.getAsJsonObject("photo");
            String url =  imageResult.get("thumb").toString();
            foodResult.setImageResult(url);
            //more parsing
            results.add(foodResult);
        }
        return results;
    }

    private Request createFoodRequest(String query) {
        Request request = new Request.Builder()
                .url("https://trackapi.nutritionix.com/v2/search/instant?query=" + query + "&branded=1")
                .get()
                .addHeader("x-app-id", "d945959f")
                .addHeader("x-app-key", "2106962a026217738b1c97202cdf130a")
                .addHeader("User-Agent", "PostmanRuntime/7.19.0")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "4d75feee-61ae-404e-b0f5-ca62daf2b894,3ba89f84-3357-4bc2-b60e-4b8aefb70055")
                .addHeader("Host", "trackapi.nutritionix.com")
                .addHeader("Connection", "keep-alive")
                .addHeader("cache-control", "no-cache")
                .build();
        return request;

    }


}


