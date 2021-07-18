package com.example.newsapp.fragments

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
import com.example.newsapp.*
import com.example.newsapp.adapter.NewsItemClicked
import com.example.newsapp.adapter.NewsListAdapter
import kotlinx.android.synthetic.main.fragment_business.*


class EntertainmentFragment : Fragment() , NewsItemClicked {

    lateinit var madapter : NewsListAdapter
    lateinit var mfragment : FragmentClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        madapter= NewsListAdapter(this)
        mfragment = FragmentClass()
        fetchData()
        with(recyclerView){
            layoutManager= LinearLayoutManager(context)
            adapter=madapter
        }

    }

    fun fetchData(){
        val url="https://saurav.tech/NewsAPI/top-headlines/category/entertainment/in.json"                   //health,sports,business
        context?.let { mfragment.fetchData(it,url,madapter) }
    }
    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        context?.let { customTabsIntent.launchUrl(it, Uri.parse(item.url)) };
    }
}