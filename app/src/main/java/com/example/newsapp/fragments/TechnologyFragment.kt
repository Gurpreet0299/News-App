package com.example.newsapp.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.News
import com.example.newsapp.adapter.NewsItemClicked
import com.example.newsapp.adapter.NewsListAdapter
import com.example.newsapp.R
import kotlinx.android.synthetic.main.fragment_health.*


class TechnologyFragment : Fragment() , NewsItemClicked {

    lateinit var madapter : NewsListAdapter
    lateinit var fragment : FragmentClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        madapter= NewsListAdapter(this)
        fragment= FragmentClass()
        fetchData()
        with(recyclerView){
            layoutManager= LinearLayoutManager(context)
            adapter=madapter
        }


    }

    fun fetchData(){
        val url="https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json"     //health,sports,business
        context?.let { fragment.fetchData(it,url,madapter) }
    }
    override fun onItemClicked(item: News) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        context?.let { customTabsIntent.launchUrl(it, Uri.parse(item.url)) };
    }
}