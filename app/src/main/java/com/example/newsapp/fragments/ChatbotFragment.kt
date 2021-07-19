package com.example.newsapp.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.newsapp.R


class ChatbotFragment : Fragment(){
    var web : WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_chatbot, container, false)
        web= v.findViewById<WebView>(R.id.webView)
        web?.settings?.javaScriptEnabled =true
        web?.webViewClient = WebViewClient()
        web?.let { it.loadUrl("https://console.dialogflow.com/api-client/demo/embedded/96793d37-141c-499e-b4f6-fdd3a7a8bf12") }
        web?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && web?.canGoBack()!!) {
                    web?.goBack()
                    return true
                }
                return false
            }
        })
        return v
    }
}