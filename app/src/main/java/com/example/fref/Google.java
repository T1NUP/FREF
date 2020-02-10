package com.example.fref;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Google extends AppCompatActivity {

    Intent e;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);


        webView= findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
 //       String add="car";
//        webView.loadUrl("http://www.google.com/"+add);
        e= getIntent();
        String add= e.getStringExtra("key");
        webView.loadUrl("https://www.google.com/search?q="+add);

    }

    @Override
    public void onBackPressed()
    {
        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

}
