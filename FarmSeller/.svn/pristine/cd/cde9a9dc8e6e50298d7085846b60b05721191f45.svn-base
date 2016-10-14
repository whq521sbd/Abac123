package com.aotuo.vegetable.act;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.tsz.afinal.annotation.view.ViewInject;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aotuo.vegetable.base.BaseActivity;
import com.aotuo.vegetable.dialog.Userphoto_dialog;
import com.aotuo.vegetable.util.DialogUtils;
import com.aotuo.vegetable.R;

public class AddNewsActivity extends BaseActivity implements OnClickListener {
    @ViewInject(id = R.id.add_news_back, click = "onClick")
    private ImageView mBack;
    @ViewInject(id = R.id.news_add, click = "onClick")
    private TextView mAdd;
    @ViewInject(id = R.id.add_news_title)
    private EditText mTitle;
    @ViewInject(id = R.id.add_news_content)
    private EditText mContent;
    @ViewInject(id = R.id.add_news_image)
    private ImageView mImage;
    @ViewInject(id = R.id.add_image_button, click = "onClick")
    private ImageView mAddImage;
    Userphoto_dialog userphoto_dialog;
    private Uri imageUri;
    private static File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        initInjectedView(this);
        initUI();
    }

    private void initUI() {
        file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
        imageUri = Uri.fromFile(file);
        userphoto_dialog = new Userphoto_dialog(AddNewsActivity.this,
                R.style.MyDialogStyle, new Userphoto_dialog.DialogListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.paizhao:
                        /***
                         * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
                         * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                         */
                        Intent intent_paizhao = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        intent_paizhao.putExtra(MediaStore.EXTRA_OUTPUT,
                                imageUri);
                        startActivityForResult(intent_paizhao, 30);
                        userphoto_dialog.dismiss();
                        break;
                    /***
                     * 从相册中取图片
                     */
                    case R.id.image_book:
                        Intent intentFromGallery = new Intent();
                        intentFromGallery.setType("image/*"); // 设置文件类型
                        intentFromGallery
                                .setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intentFromGallery, 20);
                        userphoto_dialog.dismiss();
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_news_back:
                finish();
                break;
            case R.id.news_add:

                break;
            case R.id.add_image_button:
                DialogUtils.displayDialog(userphoto_dialog);
                break;
            default:
                break;
        }
    }

    /**
     * 设置头像 得到传回的数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 20:// 响应相册选取图片按钮
                if (data != null) {
                    startPhotoZoom(data.getData());// 裁剪图片
                }
                break;
            case 30:// 响应拍照选取图片按钮
                if (imageUri != null) {
                    startPhotoZoom(imageUri);// 裁剪图片
                }
                break;
            case 40:// 响应截取图片按钮
                if (data != null) {
                    getImageToView(imageUri);// 设置头像
                }
                break;
            default:
                break;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");// 发送裁剪信号
        // 设置传递文件的type和uri
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);// 是否将数据保留在Bitmap中返回
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// 相应的Bitmap数据
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);// 脸部验证
        startActivityForResult(intent, 40);
    }

    /**
     * 显示截图后的图片到控件
     *
     * @param picdata
     */
    private void getImageToView(Uri imageUri) {
        if (imageUri != null) {
            InputStream is = null;
            try {
                is = this.getContentResolver().openInputStream(imageUri);
                Bitmap bm = BitmapFactory.decodeStream(is);
                is.close();
                mImage.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
