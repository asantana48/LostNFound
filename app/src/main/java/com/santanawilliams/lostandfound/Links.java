package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.R.color.holo_blue_dark;
import static android.R.color.holo_blue_light;
import static android.R.color.holo_green_dark;
import static android.R.color.holo_green_light;
import static android.R.color.holo_red_dark;

public class Links extends Activity {
    private WebView links;
    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        background = (RelativeLayout) findViewById(R.id.activity_links);

        links = (WebView) findViewById(R.id.iframeView);
        links.setWebChromeClient(new WebChromeClient());
        links.getSettings().setJavaScriptEnabled(true);
        links.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        links.loadUrl("file:///android_asset/test2html.html");
    }

    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public String GetString(String thefield) {
            String backwards = "";
            for (int i = thefield.length() - 1; i >= 0; i--) {
                backwards += thefield.charAt(i);
            }
            return backwards;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


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
        }
        return super.onOptionsItemSelected(item);
    }

}
