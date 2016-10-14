package com.aotuo.vegetable.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.GoodsAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.dialog.EditDialog;
import com.aotuo.vegetable.dialog.ListDialog;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.entity.GoodsListData;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.DialogUtils;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StaticDialog;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 牛XX on 2016/8/26.
 */
public class GoodsList extends BaseActivity {
    private final static int TO_CREATE = 12;
    private final static int TO_EDIT = 13;
    private ListView listView;
    private TitleView title;
    private GoodsListData goodsData;
    private GoodsAdapter adapter;
    private List<GoodsA> goodsList = new ArrayList<GoodsA>();
    private ListDialog listDialog;
    private String inMode = "";// select
    private int curPage = 1;
    private EditDialog editDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_goods_list);
        inMode = getIntent().getStringExtra("mode");
        initUI();
        getList();
    }

    private void initUI() {
        title = new TitleView();
        title.initView(GoodsList.this, "我的商品", "创建");
        title.showRightBtn();
        title.setItitleBar(new TitleView.ITitleBar() {
            @Override
            public void onRightClick() {
                Intent intent = new Intent(GoodsList.this, ClassicActivity.class);
                startActivityForResult(intent, TO_CREATE);
            }
        });

        listView = (ListView) findViewById(R.id.listView);

        adapter = new GoodsAdapter(GoodsList.this, goodsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listDialog.setIntParam(position);
                listDialog.setStrParam(goodsList.get(position).getGoodsID());
                if(goodsList.get(position).getZT() == 1){
                    listDialog.getList().set(3, "下架");
                } else {
                    listDialog.getList().set(3, "上架");
                }
                DialogUtils.displayDialog(listDialog);
            }
        });

        List<String> strList = new ArrayList<String>();
        strList.add("编辑商品");
        strList.add("编辑商品库存");
        strList.add("删除商品");
        strList.add("");
        if ("select".equals(inMode))
            strList.add("选择商品");
        listDialog = new ListDialog(GoodsList.this, strList, new ListDialog.DialogListener() {
            @Override
            public void onClick(int index) {
                switch (index) {
                    case 0: {//编辑
                        listDialog.dismiss();
                        toEdit(listDialog.getIntParam());
                    }
                    break;
                    case 1: {//编辑商品库存
                        listDialog.dismiss();
                        StaticDialog sd = new StaticDialog();
                        sd.init_dialog(editDialog);
                        editDialog.setStrParam(listDialog.getStrParam());
                        editDialog.clearEdit();
                    }
                    break;
                    case 2: {//删除商品
                        HashMap<String, String> param = new HashMap<String, String>();
                        param.put("GoodsID", listDialog.getStrParam());
                        param.put("Token", CommonTools.getToken(GoodsList.this));
                        MainActivity.getRequest(2, sHandler, "/GoodsA/GoodsDel", param);
                        listDialog.dismiss();
                    }
                    break;
                    case 3:{
                        HashMap<String, Object> param = new HashMap<String, Object>();
                        param.put("Token", CommonTools.getToken(GoodsList.this));
                        param.put("GoodsID", listDialog.getStrParam());
                        if(goodsList.get(listDialog.getIntParam()).getZT() == 1){//去下架
                            MainActivity.postRequest(6, sHandler, "/GoodsA/GoodsDown", param);
                        } else {//去上架
                            MainActivity.postRequest(7, sHandler, "/GoodsA/GoodsUp", param);
                        }
                        listDialog.dismiss();
                    }
                    break;
                    case 4: {//选择
                        listDialog.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("goodsA", goodsList.get(listDialog.getIntParam()));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    break;
                }
            }
        });

        editDialog = new EditDialog(this, R.style.MyDialogStyle,
                "请输入商品库存", InputType.TYPE_NUMBER_FLAG_DECIMAL,
                new EditDialog.DialogListener() {
                    @Override
                    public void onclick(View view) {
                        switch (view.getId()) {
                            case R.id.cannel: {
                                editDialog.dismiss();
                            }
                            break;
                            case R.id.ok: {
                                String num = editDialog.returnString();
                                if (StringUtils.isEmpty(num)) {
                                    MyToast.showToast(GoodsList.this, "输入不正确！", 2);
                                } else {
                                    editGoodsStore(editDialog.getStrParam(), num);
                                }
                                editDialog.dismiss();
                            }
                            break;
                        }
                    }
                });
    }

    private void getList() {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("ClassID", "0");
        param.put("Token", CommonTools.getToken(GoodsList.this));
        param.put("curPage", "" + curPage);
        MainActivity.getRequest(1, sHandler, "/GoodsA/GoodsListBy", param);
    }

    private void editGoodsStore(String goodsId, String num) {//参数：Token，GoodsID,Quantity(整型)
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(GoodsList.this));
        param.put("GoodsID", goodsId);
        param.put("Quantity", num);
        MainActivity.postRequest(3, sHandler, "/GoodsA/GoodsEditQuantity", param);
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {//获取商品列表
                goodsData = null;
                try {
                    goodsData = new Gson().fromJson(msg.obj.toString(),
                            GoodsListData.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (goodsData != null) {
                    if (curPage == 1)
                        goodsList.clear();
                    if (goodsData.getGoods() != null && goodsData.getGoods().size() > 0) {
                        goodsList.addAll(goodsData.getGoods());
                        curPage++;
                    }
                    adapter.notifyDataSetChanged();
                } else if (curPage == 1) {
                    goodsList.clear();
                    adapter.notifyDataSetChanged();
                }
            } else if (msg.what == 2) {//删除商品
                if (msg.arg1 > 0) {
                    curPage = 1;
                    getList();
                    MyToast.showToast(GoodsList.this, "删除成功！", 2);
                } else {
                    MyToast.showToast(GoodsList.this, msg.obj.toString(), 2);
                }
            } else if (msg.what == 3) {//编辑商品库存
                if (msg.arg1 > 0) {
                    curPage = 1;
                    getList();
                    MyToast.showToast(GoodsList.this, "修改成功！", 2);
                } else {
                    MyToast.showToast(GoodsList.this, msg.obj.toString(), 2);
                }
            } else if (msg.what == 6){//商品下架
                if (msg.arg1 > 0) {
                    curPage = 1;
                    getList();
                    MyToast.showToast(GoodsList.this, "商品已下架！", 2);
                } else {
                    MyToast.showToast(GoodsList.this, msg.obj.toString(), 2);
                }
            } else if (msg.what == 7){//商品上架
                if (msg.arg1 > 0) {
                    curPage = 1;
                    getList();
                    MyToast.showToast(GoodsList.this, "商品已上架！", 2);
                } else {
                    MyToast.showToast(GoodsList.this, msg.obj.toString(), 2);
                }
            }
        }
    };

    private void toEdit(int pos) {
        Intent intent = new Intent(GoodsList.this, CreateGoods.class);
        intent.putExtra("goodsA", goodsList.get(pos));
        intent.putExtra("classic", CommonTools.getClassicFromId(GoodsList.this, goodsList.get(pos).getClassXXXID()));
        intent.putExtra("model", "edit");
        startActivityForResult(intent, TO_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TO_CREATE && resultCode == RESULT_OK) {
            curPage = 1;
            getList();
        } else if (requestCode == TO_EDIT && resultCode == RESULT_OK) {
            curPage = 1;
            getList();
        }
    }
}
