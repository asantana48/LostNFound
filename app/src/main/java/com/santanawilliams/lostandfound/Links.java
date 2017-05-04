package com.santanawilliams.lostandfound;

/*
* Links Activity class
* Parent class: MainActivity
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import static android.R.color.holo_blue_dark;
import static android.R.color.holo_green_dark;
import static android.R.color.holo_red_dark;

public class Links extends Activity {
    private final String wikiURL = "https://en.wikipedia.org/wiki/Penn_State_Erie,_The_Behrend_College";
    private final String erieURL = "http://www.erieairport.org/lost_found.html";
    private String currentURL;
    private WebView links;
    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        background = (RelativeLayout) findViewById(R.id.activity_links);

        currentURL = "about:blank";

        // Initialize the WebView
        links = (WebView) findViewById(R.id.iframeView);
        links.setWebChromeClient(new WebChromeClient());
        links.getSettings().setJavaScriptEnabled(true);
        links.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        links.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        links.loadUrl("file:///android_asset/main.html");
    }

    // Javascript Interface class
    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        // Get the current URL
        @JavascriptInterface
        public String getURL(){
            return currentURL;
        }
    }

    // Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Handle the menu options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.backgroundGreen:
                background.setBackgroundColor(getResources().getColor(holo_green_dark));
                return true;

            case R.id.backgroundBlue:
                background.setBackgroundColor(getResources().getColor(holo_blue_dark));
                return true;

            case R.id.backgroundRed:
                background.setBackgroundColor(getResources().getColor(holo_red_dark));
                return true;

            case R.id.behrendTab:
                currentURL = wikiURL;
                // Call javascript function in html code
                links.evaluateJavascript("javascript:changeSource()", null);
                return true;

            case R.id.airportTab:
                currentURL = erieURL;
                // Call javascript function in html code
                links.evaluateJavascript("javascript:changeSource()", null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
