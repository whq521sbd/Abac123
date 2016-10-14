package com.aotuo.vegetable.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aotuo.vegetable.R;
import com.aotuo.vegetable.adapter.GridImgAdapter;
import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.dialog.OkCancelDialog;
import com.aotuo.vegetable.entity.ClassifyMessage;
import com.aotuo.vegetable.entity.GoodsA;
import com.aotuo.vegetable.entity.GoodsAddData;
import com.aotuo.vegetable.entity.UriHttpEntity;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.MyGridView;
import com.aotuo.vegetable.util.MyToast;
import com.aotuo.vegetable.util.StaticDialog;
import com.aotuo.vegetable.view.TitleView;
import com.aotuo.vegetable.view.crop.CropActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 牛XX on 2016/8/24.
 */
public class CreateGoods extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {
    private static final int TO_GET_AREA = 26;
    private RelativeLayout modImg, areall;
    private MyGridView gridview;
    private GridImgAdapter adapter;
    private int curImgId = 0;
    private Uri imageUri;
    private File file;
    private List<UriHttpEntity> imgs = new ArrayList<UriHttpEntity>();
    private EditText name, weight, introduction, price;
    private CheckBox showAll;
    private TextView area;
    private Button toCreate;
    private Uri uri;
    private TitleView title;
    private String classXXXID;
    private String className;
    private String model = "";
    private GoodsA goodsA;
    private ClassifyMessage classicMsg;
    private String areaName, areaNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_goods_create);
        model = getIntent().getStringExtra("model");
        if ("edit".equals(model)) {
            goodsA = (GoodsA) getIntent().getSerializableExtra("goodsA");
            classicMsg = (ClassifyMessage) getIntent().getSerializableExtra("classic");
        } else {
            classXXXID = getIntent().getStringExtra("ClassXXXID");
            className = getIntent().getStringExtra("ClassName");
        }
        initUI();
    }

    private void initUI() {
        areall = (RelativeLayout) findViewById(R.id.areall);
        areall.setOnClickListener(this);
        area = (TextView) findViewById(R.id.area);
        showAll = (CheckBox) findViewById(R.id.showAll);
        title = new TitleView();
        if (classicMsg != null)
            title.initView(CreateGoods.this, classicMsg.getGc_name());
        else
            title.initView(CreateGoods.this, className);
        title.setLeftClick(new TitleView.ITitleBarLeft() {
            @Override
            public void onLeftClick() {
                if (toBack()) ;
                finish();
            }
        });
        modImg = (RelativeLayout) findViewById(R.id.modImg);
        modImg.setOnClickListener(this);
        //     img = (ImageView) findViewById(R.id.img);
        name = (EditText) findViewById(R.id.name);
        weight = (EditText) findViewById(R.id.weight);
        introduction = (EditText) findViewById(R.id.introduction);
        price = (EditText) findViewById(R.id.price);
        toCreate = (Button) findViewById(R.id.toCreate);
        toCreate.setOnClickListener(this);

        if ("edit".equals(model)) {
            //         FinalBitmap.create(this).display(img, FinalContent.FinalUrl + goodsA.getPic());
            name.setText(goodsA.getGoodsName());
            weight.setText(goodsA.getQuantity());
            price.setText(goodsA.getPrice());
            areaName = CommonTools.getAddress(this, goodsA.getAreaNum());
            areaNum = goodsA.getAreaNum();
            area.setText(areaName);
            introduction.setText(goodsA.getIntroduction());
            if ("1".equals(goodsA.getAllMarket()))
                showAll.setChecked(true);
            else
                showAll.setChecked(false);
            toCreate.setText("保存商品");
        } else {
            toCreate.setText("生成商品");
        }
        weight.setFilters(new InputFilter[]{lengthfilter});
        price.setFilters(new InputFilter[]{lengthfilter});

        if (CommonTools.isSeeAll(this))
            showAll.setVisibility(View.VISIBLE);
        else
            showAll.setVisibility(View.GONE);

        if (goodsA != null) {
            String[] arr = goodsA.getPic().split(",");
            for (int i = 0; i < arr.length; i++) {
                String s = arr[i];
                UriHttpEntity ute = new UriHttpEntity();
                ute.setUrl(s);
                ute.setIndex(i + 1);
                imgs.add(ute);
            }
        } else {
            imgs.add(new UriHttpEntity());
        }
        AdjImgs();
        adapter = new GridImgAdapter(this, imgs);
        adapter.setRow(3);
        gridview = (MyGridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterview, View view,
                                    int i, long l) {
                // TODO Auto-generated method stub
                curImgId = i;
                Intent getIcon = new Intent(CreateGoods.this,
                        GetPicActivity.class);
                startActivityForResult(getIcon, GetPicActivity.GET_USER_PHOTO);
            }
        });
        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterview,
                                           View view, int i, long l) {
                // TODO Auto-generated method stub
                if (imgs.get(i) != null) {
                    curImgId = i;
                    initdialog();
                    StaticDialog sd = new StaticDialog();
                    sd.init_dialog(confirmdialog);
                }
                return true;
            }
        });
        file = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + "refund.jpg");
        imageUri = Uri.fromFile(file);
    }

    private InputFilter lengthfilter = new InputFilter() {
        private int decimalDigits = 2;

        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            // 删除等特殊字符，直接返回
            if ("".equals(source.toString())) {
                return null;
            }
            String dValue = dest.toString();
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                int diff = dotValue.length() + 1 - decimalDigits;
                if (diff > 0) {
                    return source.subSequence(start, end - diff);
                }
            }
            return null;
        }

        public void setDecimalDigits(int decimalDigits) {
            this.decimalDigits = decimalDigits;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toCreate: {

                HashMap<String, Object> param = new HashMap<String, Object>();
                if ("edit".equals(model)) {
                    if (!isValid2()) {
                        return;
                    }

                    param.put("Token", CommonTools.getToken(CreateGoods.this));
                    param.put("ClassXXXID", goodsA.getClassXXXID());
                    param.put("GoodsName", name.getText().toString());
                    //if (uri != null)
                    //    param.put("Pic", new File(uri.getPath()));
                    sendPicEdit(param);
                    param.put("Price", price.getText().toString());
                    param.put("Quantity", weight.getText().toString());
                    param.put("GoodsID", goodsA.getGoodsID());
                    param.put("Introduction", introduction.getText().toString());
                    param.put("AllMarket", showAll.isChecked() ? "1" : "0");
                    param.put("AreaNum", areaNum);

                    MainActivity.postImgRequest(1, sHandler, "/GoodsA/GoodsEdit", param);
                } else {
                    if (!isValid()) {
                        return;
                    }
                    param.put("Token", CommonTools.getToken(CreateGoods.this));
                    param.put("ClassXXXID", classXXXID);
                    param.put("GoodsName", name.getText().toString());
                    //param.put("Pic", new File(uri.getPath()));
                    sendPic(param);
                    param.put("Price", price.getText().toString());
                    param.put("Quantity", weight.getText().toString());
                    param.put("Introduction", introduction.getText().toString());
                    param.put("AllMarket", showAll.isChecked() ? "1" : "0");
                    param.put("AreaNum", areaNum);

                    MainActivity.postImgRequest(1, sHandler, "/GoodsA/GoodsAdd", param);
                }

            }
            break;
            case R.id.modImg: {
                if (!toBack())
                    return;
                Intent getIcon = new Intent(CreateGoods.this,
                        GetPicActivity.class);
                startActivityForResult(getIcon, GetPicActivity.GET_USER_PHOTO);
            }
            break;
            case R.id.areall: {
                Intent intent = new Intent(CreateGoods.this, AreaActivity.class);
                startActivityForResult(intent, TO_GET_AREA);
            }
            break;
        }
    }

    //pic2del
    private void sendPicEdit(HashMap<String, Object> param) {
        List<UriHttpEntity> imgsTemp = new ArrayList<UriHttpEntity>();
        ArrayList<String> aa = new ArrayList<String>();
        aa.add("x");
        aa.add("x");
        aa.add("x");
        aa.add("x");
        for (int i = 0; i < imgs.size(); i++) {
            UriHttpEntity ute = imgs.get(i);
            if (ute.getIndex() != 0 && ute.getUri() == null)
                aa.add(ute.getIndex(), "a");

            if (ute.getUri() != null) {
                if (ute.getIndex() != 0) {// 修改
                    param.put("pic" + ute.getIndex(), new File(ute.getUri().getPath()));
                } else {
                    imgsTemp.add(ute);
                }
            } else if (ute.getIndex() != 0 && ute.getUrl() == null) { // 删除
                File file = isExitEmptFile();
                if (file == null) {
                    createEmpytFile();
                    file = isExitEmptFile();
                }
                param.put("pic" + ute.getIndex() + "del", file);
            }
        }
        for (int i = 0; i < imgsTemp.size(); i++) {
            for (int k = 1; k < aa.size(); k++) {
                if ("x".equals(aa.get(k))) {
                    aa.set(k, "a");
                    param.put("pic" + k, new File(imgsTemp.get(i).getUri().getPath()));
                    break;
                }
            }
        }

    }

    private void sendPic(HashMap<String, Object> param) {
        for (int i = 0; i < imgs.size(); i++) {
            UriHttpEntity ute = imgs.get(i);
            if (ute.getUri() != null)
                param.put("pic" + (i + 1), new File(ute.getUri().getPath()));
        }
    }

    private boolean isValid() {
        if (imgs == null || imgs.size() == 0 || imgs.get(0).getUri() == null) {
            MyToast.showToast(CreateGoods.this, "去选择商品图片！", 2);
            return false;
        }
        if (TextUtils.isEmpty(name.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品名称！", 2);
            return false;
        }
        if (TextUtils.isEmpty(price.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品价格！", 2);
            return false;
        }
        if (TextUtils.isEmpty(weight.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品重量！", 2);
            return false;
        }
        if (TextUtils.isEmpty(introduction.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品介绍！", 2);
            return false;
        }
        if (TextUtils.isEmpty(areaNum)) {
            MyToast.showToast(CreateGoods.this, "请选择地区！", 2);
            return false;
        }
        return true;
    }

    private boolean isValid2() {
        if (imgs == null || imgs.size() == 0 ||
                (
                   ((imgs.size() == 1) && (imgs.get(0).getUri() == null) && imgs.get(0).getUrl() == null)
                || ((imgs.size() == 2) && ((imgs.get(0).getUri() == null) && imgs.get(0).getUrl() == null)
                                && (imgs.get(1).getUri() == null) && imgs.get(1).getUrl() == null))
                || ((imgs.size() == 3) && ((imgs.get(0).getUri() == null) && imgs.get(0).getUrl() == null)
                                && ((imgs.get(1).getUri() == null) && imgs.get(1).getUrl() == null)
                                && ((imgs.get(2).getUri() == null) && imgs.get(2).getUrl() == null))
                ) {
            MyToast.showToast(CreateGoods.this, "去选择商品图片！", 2);
            return false;
        }
        if (TextUtils.isEmpty(name.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品名称！", 2);
            return false;
        }
        if (TextUtils.isEmpty(price.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品价格！", 2);
            return false;
        }
        if (TextUtils.isEmpty(weight.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品重量！", 2);
            return false;
        }
        if (TextUtils.isEmpty(introduction.getText().toString())) {
            MyToast.showToast(CreateGoods.this, "请输入商品介绍！", 2);
            return false;
        }
        if (TextUtils.isEmpty(areaNum)) {
            MyToast.showToast(CreateGoods.this, "请选择地区！", 2);
            return false;
        }

        return true;
    }

    private Handler sHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) { //create goods
                if (msg.arg1 > 0) {
                    GoodsAddData gad = null;
                    try {
                        gad = new Gson().fromJson(msg.obj.toString(), GoodsAddData.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (gad != null) {
                        if ("edit".equals(model))
                            MyToast.showToast(CreateGoods.this, "修改成功！", 2);
                        else
                            MyToast.showToast(CreateGoods.this, "创建成功！", 2);
                        Intent intent = new Intent();
                        intent.putExtra("GoodsID", gad.getGoodsID());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    MyToast.showToast(CreateGoods.this, msg.obj.toString(), 2);
                }
            } else if (msg.what == 2) {// 传送图片
                if (msg.arg1 > 0) {

                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GetPicActivity.GET_USER_PHOTO: {
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
//                        uri = data
//                                .getParcelableExtra(CropActivity.CROP_IMAGE_URI);
//                        img.setImageURI(uri);

                        Uri uri = data
                                .getParcelableExtra(CropActivity.CROP_IMAGE_URI);
                        if (curImgId < imgs.size()) {
                            imgs.get(curImgId).setUri(uri);
                        } else {
                            UriHttpEntity ute = new UriHttpEntity();
                            ute.setUri(uri);
                            imgs.add(ute);
                        }
                        AdjImgs();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            break;
            case TO_GET_AREA: {
                if (data != null) {
                    areaName = data.getStringExtra("areaName");
                    areaNum = data.getStringExtra("areaNum");
                    area.setText(areaName);
                }
            }
            break;
            default:
                break;
        }
    }

    private OkCancelDialog confirmdialog;

    private void initdialog() {
        confirmdialog = new OkCancelDialog(this, R.style.MyDialogStyle,
                "确定删除图片？", new OkCancelDialog.DialogListener() {

            @Override
            public void onclick(View v) {
                switch (v.getId()) {
                    case R.id.cannel_clear:
                        confirmdialog.dismiss();
                        break;
                    case R.id.sure_clear:
                        delImgs(curImgId);
                        confirmdialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void AdjImgs() {
        List<UriHttpEntity> luri = new ArrayList<UriHttpEntity>();
        for (UriHttpEntity ute : imgs) {
            if (ute != null) {
                if (ute.getUri() != null || ute.getUrl() != null || ute.getIndex() != 0)
                    luri.add(ute);
            }
        }
        imgs.clear();
        imgs.addAll(luri);
        if (imgs.size() < 3)
            imgs.add(new UriHttpEntity());
    }

    private void delImgs(int pos) {
        if (pos < imgs.size()) {
            if (imgs.get(pos).getUri() == null) {
                imgs.get(pos).setUrl(null);
            } else {
                imgs.get(pos).setUri(null);
            }
        }
        AdjImgs();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.modImg) {
//            img.setImageResource(R.drawable.defaultimg);
            uri = null;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (toBack())
            super.onBackPressed();
    }

    private boolean toBack() {

        return true;
    }

    private File isExitEmptFile() {
        try {
            File mediaStorageDir = this.getCacheDir();
            File mediaFile = null;
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + "arrow" + ".png");
            if (mediaFile.exists()) {
                return mediaFile;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createEmpytFile() {
        Drawable d = this.getResources().getDrawable(R.drawable.arrow);
        Bitmap bmp = ((BitmapDrawable) d).getBitmap();

        FileOutputStream fop;
        try {
            File mediaStorageDir = this.getCacheDir();
            File mediaFile = null;
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + "arrow" + ".png");
            fop = new FileOutputStream(mediaFile.getPath());
            //实例化FileOutputStream，参数是生成路径
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fop);

            fop.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
