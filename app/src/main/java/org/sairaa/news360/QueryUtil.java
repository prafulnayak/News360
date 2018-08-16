package org.sairaa.news360;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

class QueryUtil {
    private static final String LOG_TAG_QUERY_UTIL = QueryUtil.class.getName();
    private  List<News> newsList1 = new ArrayList<News>();
    public  List<News> QueryUtilsForNewtwork(String mUrl) {
        URL url;
        url = CreateUrl(mUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractFeatureFromJson(jsonResponse);

    }

    private List<News> extractFeatureFromJson(String newsJson) {
        List<News> newsData = new ArrayList<News>();
//        newsData = null;
        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsJson);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONObject newsObject = baseJsonResponse.getJSONObject("response");
            JSONArray newsArray = newsObject.getJSONArray("results");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < newsArray.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentNews = newsArray.getJSONObject(i);

                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
//                JSONObject results = currentNews.getJSONObject("");

                // Extract the value for the key called "mag"
                String id = currentNews.getString("id");
                String type = currentNews.getString("type");
                String sectionId = currentNews.getString("sectionId");
                String sectionName = currentNews.getString("sectionName");
                String webPublicationDate = currentNews.getString("webPublicationDate");
                String webTitle = currentNews.getString("webTitle");
                String webUrl = currentNews.getString("webUrl");
                String apiUrl = currentNews.getString("apiUrl");

                String thumbnail;
                try {
                    JSONObject fields = currentNews.getJSONObject("fields");
                    thumbnail = fields.getString("thumbnail");
                } catch (JSONException e) {
                    Log.e("QueryUtils", "Problem parsing the news JSON results", e);
                    thumbnail = null;
                }
                String publisher = "";
                JSONArray tags = currentNews.getJSONArray("tags");
                for(int j = 0; j<tags.length() ; j++){
                    JSONObject tagObject = tags.getJSONObject(j);

                    publisher = publisher+" / "+tagObject.getString("webTitle");
                }
                String isHosted = currentNews.getString("isHosted");
//                String pillarId = currentNews.getString("pillarId");
//                String pillarName = currentNews.getString("pillarName");
                newsData.add(new News(id,type,sectionId,sectionName,webPublicationDate,webTitle,webUrl,apiUrl,thumbnail,
                        publisher,isHosted,"12345", "ssssss"));
                // Extract the value for the key called "place"
//                String location = properties.getString("place");
//
//

                Log.i(LOG_TAG_QUERY_UTIL,"jsonconvert");
                Log.i(LOG_TAG_QUERY_UTIL,id+"\n"+type+"\n"+thumbnail);

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return newsData;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(20000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG_QUERY_UTIL, "Error response code: " + urlConnection.getResponseCode());
            }
        }catch (EOFException e){
            Log.e(LOG_TAG_QUERY_UTIL, "EOFException Problem retrieving the News JSON results.", e);
        } catch (IOException e) {
            //Log.e(LOG_TAG, "Problem retrieving the News JSON results."+ urlConnection.getResponseCode() );
            Log.e(LOG_TAG_QUERY_UTIL, "Problem retrieving the News JSON results.", e);
            //Log.e(LOG_TAG, "Problem retrieving the News JSON results."+ urlConnection.getResponseCode() );
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;


    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private URL CreateUrl(String urlNews) {
        URL url2 = null;
        try{
            url2 = new URL(urlNews);
        }catch (MalformedURLException e) {
            Log.e(LOG_TAG_QUERY_UTIL, "Error with creating URL ", e);
        }
        return url2;
    }
}
