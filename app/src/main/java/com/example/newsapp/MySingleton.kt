package com.example.newsapp

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

//singleton class who can only have one instance
class MySingleton constructor(context: Context) {
    companion object {
        @Volatile   //volatile so because it could be threat safe
        private var INSTANCE: MySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {                                //Once instance is created it'll not created once more
                INSTANCE ?: MySingleton(context).also {                           //"? :" Elvis operator is used ,It is used to return not null value
                    INSTANCE = it                                                    // even if the expression giving in situation is null
                }
            }
    }

   private  val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}