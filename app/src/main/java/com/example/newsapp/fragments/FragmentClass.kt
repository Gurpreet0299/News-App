package com.example.newsapp.fragments

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsapp.MySingleton
import com.example.newsapp.News
import com.example.newsapp.adapter.NewsListAdapter

class FragmentClass() {

    fun fetchData(context: Context , url :String , madapter : NewsListAdapter){
                        //health,sports,business
// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,null,
                Response.Listener {
                    Toast.makeText(context,"Loading...", Toast.LENGTH_LONG).show()
                    val newsJsonArray=it.getJSONArray("articles")
                    val newsArray=ArrayList<News>()
                    for(i in 0 until newsJsonArray.length()){
                        val newsArrayObject=newsJsonArray.getJSONObject(i)

                        //This is called parsing of data
                        val news= News(newsArrayObject.getString("title"),
                                newsArrayObject.getString("url"),
                                newsArrayObject.getString("urlToImage"),
                                newsArrayObject.getString("author")
                        )
                        newsArray.add(news)

                        //adding data in adapter
                        madapter.updatedNews(newsArray)
                    }
                },
                Response.ErrorListener {  Toast.makeText(context,"Error occured [${it.localizedMessage}] ",
                        Toast.LENGTH_LONG).show()})

// Add the request to the RequestQueue.
        context?.let { MySingleton.getInstance(it).addToRequestQueue(jsonObjectRequest) }
    }
}