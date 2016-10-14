package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.ContactListAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.dialog.DefaultDialog;
import com.aotuo.vegetable.entity.ContactEntity;
import com.aotuo.vegetable.entity.ContactListData;
import com.aotuo.vegetable.hx.ChatActivity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StaticDialog;
import com.aotuo.vegetable.view.TitleView;
import com.aotuo.vegetable.view.XListView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 牛XX on 2016/9/2.
 */
public class ContactListActivity extends BaseActivity implements View.OnClickListener, XListView.IXListViewListener{
    private TitleView titleView;
    private EditText search;
    private TextView toSearch;
    private XListView listView;
    private List<ContactEntity> contactEntityList = new ArrayList<ContactEntity>();
    private ContactListAdapter adapter;
    private DefaultDialog deleteDialog;
    private int curPage;
    private int pageLen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contact_list);
        initUI();
    }

    private void initUI() {
        titleView = new TitleView();
        titleView.initView(this, "通讯录");
        search = (EditText) findViewById(R.id.search);
        toSearch = (TextView) findViewById(R.id.toSearch);
        listView = (XListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position - 1;
                if(pos < contactEntityList.size() && pos >= 0){
                    Intent intent = new Intent(ContactListActivity.this, ChatActivity.class);
                    intent.putExtra("RecID", contactEntityList.get(pos).getRecID());
                    intent.putExtra("RecName", contactEntityList.get(pos).getRecName());
                    startActivity(intent);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position - 1;
                if(pos < contactEntityList.size() && pos >= 0){
                    toDelete(contactEntityList.get(pos).getRecID());
                    return true;
                }
                return false;
            }
        });
        deleteDialog = new DefaultDialog(this, R.style.MyDialogStyle,
                new DefaultDialog.DialogListener() {

                    @Override
                    public void onclick(View v) {
                        switch (v.getId()) {
                            case R.id.cannel_clear:
                                deleteDialog.dismiss();
                                break;
                            case R.id.sure_clear:
                                HashMap<String, String> param = new HashMap<String, String>();
                                param.put("UserNum", deleteDialog.getStrParem());
                                param.put("Token", CommonTools.getToken(ContactListActivity.this));
                                MainActivity.getRequest(3, sHandler, "/UserA/TalkUserDel", param);

                                deleteDialog.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                });
        deleteDialog.setTitle("是否删除？");
        toSearch.setOnClickListener(this);

        hasNewsList();
        adapter = new ContactListAdapter(this, contactEntityList);
        listView.setAdapter(adapter);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        listView.setXListViewListener(this);
        curPage = 1;
        getUserList(curPage);
    }

    private void toDelete(String UserNum) {
        StaticDialog sd = new StaticDialog();
        deleteDialog.setStrParem(UserNum);
        deleteDialog.setStrContent("是否删除？");
        sd.init_dialog(deleteDialog);
    }

    /**
     * 获取当前联系人列表
     */
    private void getUserList(int page){
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(ContactListActivity.this));
        param.put("curPage", "" + page);
        MainActivity.postRequest(2, sHandler, "/UserA/TalkUser", param);
    }
    /**
     * 获取当前是否有新信息
     */
    private void hasNewsList(){
        HashMap<String,Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(ContactListActivity.this));
        MainActivity.postRequest(1, sHandler, "/UserA/IsNewTalk", param);
    }
    private Handler sHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:{
                    boolean isHas = false;
                    try {
                        isHas = new Gson().fromJson(msg.obj.toString(), Boolean.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if(isHas){

                    }
                }
                break;
                case 2:{//new List<TalkUserA>() { new CustomerStat() { RecID = "123", RecName="ssssss" } }
                    listView.stopRefresh();
                    listView.stopLoadMore();

                    ContactListData cld = null;
                    try {
                        cld = new Gson().fromJson(msg.obj.toString(), ContactListData.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }

                    if(cld != null){
                        if(cld.getCurPage() == 1 || cld.getCurPage() == 0)
                            contactEntityList.clear();
                        if(cld.getUsers() != null && cld.getUsers().size() > 0)
                            contactEntityList.addAll(cld.getUsers());
                        curPage = cld.getCurPage();
                        pageLen = cld.getPageSize();
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
                case 3:{
                    if(msg.arg1 > 0){
                        MyToast.showToast(ContactListActivity.this, "删除成功！", 2);
                        curPage = 1;
                        getUserList(curPage);
                    }
                }
                break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toSearch:{
                Intent intent = new Intent(ContactListActivity.this, ChatActivity.class);
//                intent.putExtra("RecID", "370101000002");
 //               intent.putExtra("RecName", "buy");
//                intent.putExtra("RecID", "370101000001");
//                intent.putExtra("RecName", "sell");
//                startActivity(intent);
            }
            break;
        }
    }

    @Override
    public void onRefresh() {
        curPage = 1;
        getUserList(curPage);
    }

    @Override
    public void onLoadMore() {
        if(curPage + 1 <= pageLen)
            getUserList(curPage + 1);
    }
}
