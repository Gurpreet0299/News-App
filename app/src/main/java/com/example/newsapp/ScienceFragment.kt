package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.fragment_health.*


class ScienceFragment : Fragment() , NewsItemClicked{

    lateinit var madapter :NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_science, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        madapter= NewsListAdapter(this)
        fetchData()
        with(recyclerView){
            layoutManager= LinearLayoutManager(context)
            adapter=madapter
        }

    }

    fun fetchData(){
        val url="https://saurav.tech/NewsAPI/top-headlines/category/science/in.json"                   //health,sports,business
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
            Response.ErrorListener {  Toast.makeText(context,"Error occured [${it.localizedMessage}] ",
                Toast.LENGTH_LONG).show()})

// Add the request to the RequestQueue.
        context?.let { MySingleton.getInstance(it).addToRequestQueue(jsonObjectRequest) }
    }
    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        context?.let { customTabsIntent.launchUrl(it, Uri.parse(item.url)) };
    }
}