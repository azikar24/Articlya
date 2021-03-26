package com.azikar24.articlya.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.azikar24.articlya.Models.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ArticleRepository {
    private RequestQueue queue;
    private static ArticleRepository instance;
    private ArrayList<Article> dataSet1 = new ArrayList<>();
    private ArrayList<Article> dataSet2 = new ArrayList<>();
    private ArrayList<Article> dataSet3 = new ArrayList<>();
    public static final int DAY = 1;
    public static final int WEEK = 7;
    public static final int MONTH = 30;
    private MutableLiveData<ArrayList<Article>> data = new MutableLiveData<>();


    private static Context context;

    /**
     * Singleton instance
     * @param context : getBaseContext() or getContext()
     * @return an instance
     */
    public static ArticleRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ArticleRepository();
        }
        ArticleRepository.context = context;

        return instance;
    }


    /**
     * Load the data from the api and retrieve it as a MutableLiveData
     * @param option
     * @return MutableLiveData of the articles called from the api
     */
    public MutableLiveData<ArrayList<Article>> getArticles(int option) {
        queue = Volley.newRequestQueue(context);
        // first StringRequest: getting items searched
        StringRequest stringRequest = fetchRequest(option);
        // executing the request (adding to queue)
        queue.add(stringRequest);

        if (option == DAY) {
            data.setValue(dataSet1);
        } else if (option == WEEK) {
            data.setValue(dataSet2);
        } else if (option == MONTH) {
            data.setValue(dataSet3);
        }
        return data;
    }

    /**
     * Setup the api url for different period
     * @param priod : DAY, WEEK, MONTH
     * @return the api url
     */
    public String getApiUrl(int priod) {
        return "https://api.nytimes.com/svc/mostpopular/v2/viewed/" + priod + ".json?api-key=LreWSkDH18CMup3Frk3imMeLK6mLxeVL";
    }

    /**
     * Prepare the request for the Volley call
     * @param option : Period Option
     * @return StringRequest for volley
     */
    private StringRequest fetchRequest(final int option) {

        return new StringRequest(Request.Method.GET, getApiUrl(option), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONObject(response).getJSONArray("results");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject first = result.getJSONObject(i);
                        String url = first.getString("url");
                        String title = first.getString("title");
                        String by = first.getString("byline");
                        String date = first.getString("published_date");
                        String category = first.getString("section");
                        String abstractt = first.getString("abstract");
                        JSONArray imageArr = first.getJSONArray("media");
                        String imageUrl = "";
                        if (imageArr.length() != 0) {
                            JSONObject imageObject = imageArr.getJSONObject(0).getJSONArray("media-metadata").getJSONObject(2);
                            imageUrl = imageObject.getString("url");
                        }

                        Article article = new Article(title, by, date, category, abstractt, imageUrl, url);
                        if (option == DAY) {
                            dataSet1.add(article);
                        } else if (option == WEEK) {
                            dataSet2.add(article);
                        } else if (option == MONTH) {
                            dataSet3.add(article);
                        }

                    }
                    if (option == DAY) {
                        data.setValue(dataSet1);
                    } else if (option == WEEK) {
                        data.setValue(dataSet1);
                    } else if (option == MONTH) {
                        data.setValue(dataSet1);
                    }
                    // catch for the JSON parsing error
                } catch (JSONException e) {
                    Log.d("ResultError", e.getMessage());
                }
            } // public void onResponse(String response)
        }, // Response.Listener<String>()
                new Response.ErrorListener() {
                    // 4th param - method onErrorResponse lays the code procedure of error return
                    // ERROR
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // display a simple message on the screen

                    }
                });
    }


}
