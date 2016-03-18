package com.example.admin.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    String text = "<p><div id='iii'>00000000</div></p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.wv_acticle_detail);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);//自适应
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        webView.addJavascriptInterface(this, "handler");
        setUpWebView(text);
    }

    private void setUpWebView(final String mdText) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadMarkDown(mdText.replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n"));
            }
        });
        webView.loadUrl("file:///android_asset/markdown.html");
    }

    private void loadMarkDown(String str) {
        webView.loadUrl("javascript:parseMarkdown(\"" + str + "\")");
    }

    @JavascriptInterface
    public void onVoice(){
        Toast.makeText(this,"onVoice",Toast.LENGTH_SHORT).show();
    }

}
