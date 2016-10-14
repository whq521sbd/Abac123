package com.aotuo.vegetable.act;

import android.os.Bundle;
import android.webkit.WebView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.util.FinalContent;
import com.aotuo.vegetable.util.WebViewOpenUrl;
import com.aotuo.vegetable.view.TitleView;

public class WebPageActivity extends BaseActivity {
    private TitleView titleView;
    private WebView web_page_content;
    private String title = "新闻";
    private String newsID;
    private String messID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);
        messID = getIntent().getStringExtra("MessID");
        newsID = getIntent().getStringExtra("NewsID");
        title = getIntent().getStringExtra("title");
        initUI();
        initdate();
    }

    private void initUI() {
        titleView = new TitleView();
        titleView.initView(this, title);
        web_page_content = (WebView) findViewById(R.id.web_page_content);
    }

    private void initdate() {
        if(newsID != null)
            web_page_content.loadUrl(FinalContent.FinalUrl + "/NewsA/News"
                + "?NewsID=" + newsID);
        else if(messID != null)
            web_page_content.loadUrl(FinalContent.FinalUrl + "/NewsA/Messes"
                    + "?MessID=" + messID);
        WebViewOpenUrl.getInstence(this).openurl(web_page_content);
    }

}
