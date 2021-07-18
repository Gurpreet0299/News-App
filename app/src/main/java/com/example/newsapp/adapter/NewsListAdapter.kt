package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.News
import com.example.newsapp.R
import kotlinx.android.synthetic.main.list_item.view.*

class NewsListAdapter(private val listener : NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    val newsArray=ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //LayoutInflator converts the list_item.xml into view
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        val viewHolder = NewsViewHolder(view)

        //We need callback in adapter which tells activity item has been clicked in reyclerView
        //Handles click on item in recyclerView
        view.setOnClickListener {
            listener.onItemClicked(newsArray[viewHolder.adapterPosition])
        }
      return viewHolder
    }
    override fun getItemCount(): Int {
        return newsArray.size
    }

    //it takes items one by one and fill corresponding data in it(Bind data with view)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        //position tells where item is
        val currentItem = newsArray[position]
        holder.bind(currentItem)
    }
    fun updatedNews(news :ArrayList<News>){
        newsArray.clear()
        newsArray.addAll(news)

        //when it is called above implemented members of RecyclerView.Adapter called again
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    fun bind(item : News){
        with(itemView){
            title.text=item.title
            author.text=item.author
            Glide.with(itemView.context).load(item.ImageUrl).into(imageView)
        }
    }
}

//This is callback we need to tell activity item has clicked in recyclerView
interface NewsItemClicked{
    fun onItemClicked(item : News)
}