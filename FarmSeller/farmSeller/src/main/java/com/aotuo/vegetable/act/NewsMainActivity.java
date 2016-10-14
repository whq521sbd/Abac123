package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.MessageFlag;
import com.aotuo.vegetable.entity.MessageHome;
import com.aotuo.vegetable.view.TitleView;


public class NewsMainActivity extends BaseActivity implements OnClickListener {
    private TitleView titleView;
    private LinearLayout order, news, customer;
    private TextView newsMessFlags, newsFlags, newsUserFlags;
    private TextView content_order, content_service, content_customer;
    private String key;
    private MessageHome msgHome;
    private MessageFlag messageFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        key = getIntent().getStringExtra("key");
        messageFlag = (MessageFlag) getIntent().getSerializableExtra("flag");
        initUI();
    }

    private void initUI() {
        // TODO Auto-generated method stub
        titleView = new TitleView();
        titleView.initView(this, "消息中心");
        customer = (LinearLayout) findViewById(R.id.customer);
        order = (LinearLayout) findViewById(R.id.order);
        news = (LinearLayout) findViewById(R.id.news);
        newsUserFlags = (TextView) findViewById(R.id.newsUserFlags);
        newsFlags = (TextView) findViewById(R.id.newsFlags);
        newsMessFlags = (TextView) findViewById(R.id.newsMessFlags);
        content_customer = (TextView) findViewById(R.id.content_customer);
        content_order = (TextView) findViewById(R.id.content_order);
        content_service = (TextView) findViewById(R.id.content_service);
        order.setOnClickListener(this);
        news.setOnClickListener(this);
        customer.setOnClickListener(this);
        if (messageFlag != null) {
            if(messageFlag.isHasMess())//市场
                newsMessFlags.setVisibility(View.VISIBLE);
            if(messageFlag.isHasNews())//新闻
                newsFlags.setVisibility(View.VISIBLE);
            if(messageFlag.isHasMsg())//用户
                newsUserFlags.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.order: {
                Intent intent_news = new Intent(NewsMainActivity.this,
                        MarketNewsCenterActivity.class);
                startActivity(intent_news);
            }
            break;
            case R.id.news: {
                Intent intent_news = new Intent(NewsMainActivity.this,
                        NewsCenterActivity.class);
                startActivityForResult(intent_news, 1002);
            }
            break;
            case R.id.customer: {
                Intent intent_com = new Intent(NewsMainActivity.this,
                        ContactListActivity.class);
                startActivity(intent_com);
            }
            break;
        }
    }

}
