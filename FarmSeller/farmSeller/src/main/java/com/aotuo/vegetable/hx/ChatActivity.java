package com.aotuo.vegetable.hx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.adapter.BuyerAdaper;
import com.aotuo.vegetable.adapter.SellerAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.entity.LogInfoEntity;
import com.aotuo.vegetable.entity.OrderTempData;
import com.aotuo.vegetable.sqlite.NewsDBDao;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.ReLoginReceiver;
import com.aotuo.vegetable.util.StringUtils;
import com.aotuo.vegetable.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends BaseActivity {
    private final static int TO_DELAY = 39;
    private TitleView titleView;
    private Button sendButton = null, button1, button2;
    private EditText contentEditText = null;
    private ListView chatListView = null;
    private List<MessageData> chatList = new ArrayList<MessageData>();
    private RelativeLayout buyer, seller;
    private ChatAdapter chatAdapter = null;
    private TextView bDistance, bFax, bFreight, sDistance, sFax;
    private EditText sFreight;
    private boolean jianghua;
    private String meUser = "";
    private String toUser;
    private String toUserName;
    //private String myUser = "3701000001";
    private boolean isRunning;
    private NewsDBDao dao;
    private MessageData sendData;
    private int[] delayArr = {
            3, 3, 3, 3, 3,//15s
            3, 3, 3, 3, 3,//30s
            3, 3, 3, 3, 3,//45s
            3, 3, 3, 3, 3,//60s
            5, 5, 5, 5, 5,//75s
            20//
    };
    private int index = 0;
    private BuyerAdaper buyerAdaper;
    private SellerAdapter sellerAdapter;
    private LogInfoEntity lie;
    private String curStatus;
   // private TextView tv_querycar;
    private String otherBooth;
    private String otherHeadImg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.msg_main);
        toUser = getIntent().getStringExtra("RecID");
        toUserName = getIntent().getStringExtra("RecName");
        curStatus = getIntent().getStringExtra("curStatus");
        otherBooth = getIntent().getStringExtra("otherBooth");
        otherHeadImg = getIntent().getStringExtra("otherHeadImg");
        dao = new NewsDBDao(this);
        meUser = CommonTools.getUserSn();

        titleView = new TitleView();
        titleView.initView(this, toUserName);
        contentEditText = (EditText) this.findViewById(R.id.et_content);
        sendButton = (Button) this.findViewById(R.id.btn_send);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        chatListView = (ListView) this.findViewById(R.id.listview);
        buyer = (RelativeLayout) findViewById(R.id.buyer);
        seller = (RelativeLayout) findViewById(R.id.seller);




        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!jianghua) {
                    // TODO Auto-generated method stub
                    button2.setVisibility(View.VISIBLE);
                } else {
                    button2.setVisibility(View.INVISIBLE);
                }
            }
        });

        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        getSqliteData();

        chatAdapter = new ChatAdapter(this, chatList);
        chatListView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!contentEditText.getText().toString().equals("")) {
                    //发送消息
                    sendData(contentEditText.getText().toString());
                } else {
                    Toast.makeText(ChatActivity.this, "Content is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        isRunning = true;
        if (CommonTools.isBuy(this) || "buy".equals(curStatus)) {
            seller.setVisibility(View.GONE);
            buyerAdaper = new BuyerAdaper(this, buyer, sHandler);
            buyerAdaper.setToUser(toUser);
        } else if (CommonTools.isSell(this)) {
            buyer.setVisibility(View.GONE);
            sellerAdapter = new SellerAdapter(this, seller, sHandler);
        } else {
            buyer.setVisibility(View.GONE);
            seller.setVisibility(View.GONE);
        }
        getDistance();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
        delayHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = true;
        refreshDelay();
        if(sellerAdapter != null)
            sellerAdapter.setLock(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isRunning = false;
        delayHandler.removeCallbacksAndMessages(null);
        finish();
    }

    /**
     * 参数：Token,Content,UserNum
     */
    private void sendData(String content) {
        if (StringUtils.isEmpty(content))
            return;

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(ChatActivity.this));
        param.put("Content", content);
        param.put("UserNum", toUser);
        sendData = new MessageData();
        sendData.setContent(content);
        sendData.setSendID(meUser);
        sendData.setRecID(toUser);
        sendData.setSendName("addfff");
        long sec = System.currentTimeMillis() / 1000;
        sendData.setTime(CommonTools.currentDateAndTime("" + sec));
        sendData.setIsSend("true");
        MainActivity.postRequest(1, sHandler, "/UserA/SendTalkRec", param);
    }

    /**
     * 参数：Token,Content,UserNum
     */
    private void recvData() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(ChatActivity.this));
        MainActivity.postRequest(2, sHandler, "/UserA/NewTalkRec", param);
    }

    /**
     * 参数：Token,TalkRecID
     */
    private void removeData(String id) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(ChatActivity.this));
        param.put("TalkRecID", id);
        MainActivity.postRequest(4, sHandler, "/UserA/DelTalkRec", param);
    }

    /**
     * 获取买家距离
     */
    private void getDistance() {
        String s = toUser;
        if (s.length() == 0) {
            return;
        }

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        param.put("BuyerNum", s);
        MainActivity.postRequest(5, sHandler, "/GoodsA/LogInfo", param);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SellerAdapter.S_GET_CLASSIC && resultCode == RESULT_OK) {
            GoodsA ga = (GoodsA) data.getSerializableExtra("goodsA");
            sellerAdapter.setData(ga);
        } else if (requestCode == SellerAdapter.B_GET_CLASSIC && resultCode == RESULT_OK) {
            GoodsA ga = (GoodsA) data.getSerializableExtra("goodsA");
            buyerAdaper.setData(ga);
            //发送消息
            String str = "$&buy=af,title=" + ga.getGoodsName()
                    + ",classXXXID=" + ga.getClassXXXID()
                    + ",goodsID=" + ga.getGoodsID()
                    + ",price=" + ga.getPrice()
                    + ",pic=" + ga.getPic();
            sendData(str);
        } else if(requestCode == SellerAdapter.B_BUY_OK && resultCode == RESULT_OK){
            if(buyerAdaper != null)
                buyerAdaper.clearData();
        }
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {//发送 返回: new { Suc = true, Msg = "", Data = 1 }
                    if (msg.arg1 > 0) {
                        if (!StringUtils.isEmpty(msg.obj.toString())) {
                            if (!isOrderData(sendData.getContent(), msg.obj.toString())) {
                                sendData.setID(msg.obj.toString());
                                dao.addSendNews(toUser, sendData);
                                chatList.add(sendData);
                                chatListView.setSelection(chatList.size() - 1);
                                contentEditText.setText("");
                            }
                        }
                        refreshDelay();
                    } else {
                        MyToast.showToast(ChatActivity.this, msg.obj.toString(), 2);
                    }
                }
                break;
                case 2: {//接收 new List<TalkDetailA>()
                    // {ID="",SendID="",RecID="",Content="",Time="",SendName=""}
                    if (msg.arg1 > 0) {
                        List<MessageData> tList = null;
                        try {
                            tList = new Gson().fromJson(msg.obj.toString(),
                                    new TypeToken<List<MessageData>>() {
                                    }.getType());
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (tList != null && tList.size() > 0) {
                            for(MessageData md: tList){
                                if(!isOrderData(md.getContent(), md.getID())){
                                    dao.addSendNews(toUser, md);
                                    chatList.add(chatList.size(), md);
                                }
                            }
                            //dao.addNews(tList);
                            //chatList.addAll(chatList.size(), tList);
                            chatListView.setSelection(chatList.size() - 1);
                            contentEditText.setText("");

                            refreshDelay();
                        }
                    } else {
                        if ("token已失效".equals(msg.obj.toString())) {
                            Intent close = new Intent();
                            close.setAction(ReLoginReceiver.RE_LOGIN);
                            ChatActivity.this.sendBroadcast(close);

                            finish();
                        }
                        //MyToast.showToast(ChatActivity.this, msg.obj.toString(), 2);
                    }
                }
                break;
                case 3: {//生成订单
                    if(sellerAdapter != null)
                        sellerAdapter.setLock(false);
                    if (msg.arg1 > 0) {
                        // new { TransID = "201302010201" } });
                        OrderTempData otd = null;
                        //orderText.setText("订单号：");
                        try {
                            otd = new Gson().fromJson(msg.obj.toString(), OrderTempData.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        if (otd != null) {
                            sellerAdapter.setOrderSn(otd.getTransID());
                            //发送消息
                            GoodsA goodsA = sellerAdapter.getMyGoodsA();
                            String LogDis = "0";
                            String LogRate = "0";
                            if (lie != null){
                                LogDis = "" + lie.getLogDis();
                                LogRate = "" + lie.getLogRate();
                            }
                            String str = "$&order=" + otd.getTransID()
                                    + ",T=" + goodsA.getGoodsName()//title
                                    + ",P=" + sellerAdapter.getPrice()//price
                                    + ",W=" + sellerAdapter.getWeight()//weight
                                    + ",T=" + sellerAdapter.getTotal()//total
                                    + ",pic=" + goodsA.getPic()
                                    + ",LD=" + LogDis
                                    + ",LR=" + LogRate
                                    + ",fre=" + sellerAdapter.getFreight();

                            sendData(str);
                            MyToast.showToast(ChatActivity.this, "生成订单成功！", 3);
                        } else {
                            MyToast.showToast(ChatActivity.this, "生成订单失败！", 3);
                        }
                    } else {
                        MyToast.showToast(ChatActivity.this, msg.obj.toString(), 3);
                    }
                }
                break;
                case 4:{//删除消息
                    if(msg.arg1 > 0){

                    }
                }
                break;
                case 5:{//返回:new { LogDis="",LogRate=""} });
                    lie = null;
                    try {
                        lie = new Gson().fromJson(msg.obj.toString(), LogInfoEntity.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (lie != null) {
                        if(sellerAdapter != null){
                            sellerAdapter.setLie(lie);
                        }
                    }
                }
                break;
                case 6:{
                    if(msg.arg1 > 0){
                        if(buyerAdaper != null)
                            buyerAdaper.clearData();
                        MyToast.showToast(ChatActivity.this, "成功加入购物车！", 3);
                    } else {
                        MyToast.showToast(ChatActivity.this, msg.obj.toString(), 3);
                    }
                }
                break;
            }
        }
    };

    private boolean isOrderData(String str, String id) {
        if (!StringUtils.isEmpty(str) && str.startsWith("$&order=")) {
            if (buyerAdaper != null) {
                buyerAdaper.setData(str);
            }
            removeData(id);
            return true;
        } else if(!StringUtils.isEmpty(str) && str.startsWith("$&buy=af")) {
            if(sellerAdapter != null){
                sellerAdapter.setData(str);
            }
            removeData(id);
            return true;
        } else {
            return false;
        }
    }

    private Handler delayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TO_DELAY: {
                    delayHandler.removeCallbacksAndMessages(null);
                    if (isRunning) {
                        if (index >= 0 && index < delayArr.length) {
                            delayHandler.sendEmptyMessageDelayed(TO_DELAY, delayArr[index++] * 1000);
                        } else {
                            delayHandler.sendEmptyMessageDelayed(TO_DELAY, delayArr[delayArr.length - 1] * 1000);
                        }
                        recvData();
                    }
                }
                break;
            }
        }
    };

    private void refreshDelay() {
        index = 0;
        delayHandler.removeCallbacksAndMessages(null);
        delayHandler.sendEmptyMessage(TO_DELAY);
    }

    private void getSqliteData() {
        List<MessageData> lmd = dao.getCurrNews(toUser);
        if (lmd == null || lmd.size() == 0)
            chatList.clear();
        else
            chatList.addAll(lmd);
        chatListView.setSelection(chatList.size() - 1);
        contentEditText.setText("");
    }

    private class ChatAdapter extends BaseAdapter {
        private Context context = null;
        private List<MessageData> chatList = null;
        private LayoutInflater inflater = null;
        private int COME_MSG = 0;
        private int TO_MSG = 1;

        public ChatAdapter(Context context, List<MessageData> chatList) {
            this.context = context;
            this.chatList = chatList;
            inflater = LayoutInflater.from(this.context);
        }


        @Override
        public int getCount() {
            return chatList.size();
        }

        @Override
        public Object getItem(int position) {
            return chatList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            // 区别两种view的类型，标注两个不同的变量来分别表示各自的类型
            MessageData entity = chatList.get(position);
            if ("true".equals(entity.getIsSend())) {
                return TO_MSG;
            } else {
                return COME_MSG;
            }
        }

        @Override
        public int getViewTypeCount() {
            // 这个方法默认返回1，如果希望listview的item都是一样的就返回1，我们这里有两种风格，返回2
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatHolder chatHolder = null;
            if (convertView == null) {
                chatHolder = new ChatHolder();
                if ("true".equals(chatList.get(position).getIsSend())) {
                    convertView = inflater.inflate(R.layout.chat_to_item, null);
                } else {
                    convertView = inflater.inflate(R.layout.chat_from_item, null);
                }
                chatHolder.timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
                chatHolder.contentTextView = (TextView) convertView.findViewById(R.id.tv_content);
                chatHolder.userImageView = (ImageView) convertView.findViewById(R.id.iv_user_image);
                convertView.setTag(chatHolder);
            } else {
                chatHolder = (ChatHolder) convertView.getTag();
            }
            chatHolder.timeTextView.setText(chatList.get(position).getTime());
            chatHolder.contentTextView.setText(chatList.get(position).getContent());
            if ("true".equals(chatList.get(position).getIsSend())) {
                String myPic = CommonTools.getUserPic();
                if(!StringUtils.isEmpty(myPic)){
                    FinalBitmap.create(inflater.getContext()).display(
                            chatHolder.userImageView, FinalContent.FinalUrl + myPic);
                } else {
                    chatHolder.userImageView.setImageResource(R.drawable.mypic);
                }
            } else {
                if(!StringUtils.isEmpty(otherHeadImg)){
                    FinalBitmap.create(inflater.getContext()).display(
                            chatHolder.userImageView, FinalContent.FinalUrl + otherHeadImg);
                } else {
                    chatHolder.userImageView.setImageResource(R.drawable.mypic);
                }
            }
            return convertView;
        }

        private class ChatHolder {
            private TextView timeTextView;
            private ImageView userImageView;
            private TextView contentTextView;
        }

    }
}