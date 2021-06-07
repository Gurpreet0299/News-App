package com.example.newsapp

import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

   lateinit var madapter :NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        madapter= NewsListAdapter(this)
        fetchData()
        with(recyclerView){
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=madapter
        }
    }
    fun fetchData(){
        val url="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
// Request a string response from the provided URL.
        val jsonObjectRequest =JsonObjectRequest(Request.Method.GET,
            url,null,
            Response.Listener {
                Toast.makeText(this,"Loading...",Toast.LENGTH_LONG).show()
            val newsJsonArray=it.getJSONArray("articles")
                val newsArray=ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                   val newsArrayObject=newsJsonArray.getJSONObject(i)

                    //This is called parsing of data
                   val news=News( newsArrayObject.getString("title"),
                       newsArrayObject.getString("url"),
                       newsArrayObject.getString("urlToImage"),
                       newsArrayObject.getString("author")
                       )
                    newsArray.add(news)

                    //adding data in adapter
                    madapter.updatedNews(newsArray)
                }
            },
            Response.ErrorListener {  Toast.makeText(this,"Error occured [${it.localizedMessage}] ",Toast.LENGTH_LONG).show()})

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
       val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}