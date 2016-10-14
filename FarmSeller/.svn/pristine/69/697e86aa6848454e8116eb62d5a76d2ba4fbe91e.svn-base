package com.aotuo.vegetable.act;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.view.TitleView;

/**
 * Created by Administrator on 2016/8/23.
 */
public class CollectClassicActivity extends BaseActivity implements View.OnClickListener {
    private TitleView title;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_collect_classic);
        initUI();
    }

    private void initUI() {
        title = new TitleView();
        title.initView(CollectClassicActivity.this, "选择商品");
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}
