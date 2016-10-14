package com.aotuo.vegetable.hx;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.entity.AreaData;
import com.aotuo.vegetable.entity.ClassicData;
import com.aotuo.vegetable.entity.UserEntity;
import com.aotuo.vegetable.sqlite.AreaDBDao;
import com.aotuo.vegetable.sqlite.ClassifyDBDao;
import com.aotuo.vegetable.util.CommonTools;
import com.aotuo.vegetable.util.FinalContent;
import com.aotuo.vegetable.util.StringUtils;
import com.comm.tools.a.BsModel;
import com.comm.tools.a.DefMessage;
import com.comm.tools.a.HttpRequest;
import com.comm.tools.a.RequestGetModel;
import com.comm.tools.a.RequestPostImgModel;
import com.comm.tools.a.RequestPostModel;
import com.comm.tools.a.RequestPutModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

public class HXService extends Service /*implements EMEventListener*/ {
    private final static String TAG = "HXService";
    private static String username = "";
    private static String key = "";
    private static String pwd = "beson123456";
    private static boolean isLoginServer = false;
    private static String toChatUsername;
    private static int unReadMsg = 0;
    private static UserEntity user;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public HXService getService() {
            // Return this instance of LocalService so clients can call public
            new FinalContent();
            return HXService.this;
        }
    }

    //检查服务器数据更新
    public boolean isNewCity() {
        String oldVer = CommonTools.getOldAreaVer(this);
        String ver = CommonTools.getAreaVer(this);
        if (StringUtils.isEmpty(oldVer) && StringUtils.isEmpty(ver)) {
            HashMap<String, Object> param = new HashMap<String, Object>();
            postRequest(13, updateHandler, "/GoodsA/Areas", param);
            return true;
        } else if (ver != null && !ver.equals(oldVer)) {
            HashMap<String, Object> param = new HashMap<String, Object>();
            postRequest(13, updateHandler, "/GoodsA/Areas", param);
            return true;
        }
        return false;
    }

    public boolean isNewClassity() {
        String oldVer = CommonTools.getOldClassVer(this);
        String ver = CommonTools.getClassVer(this);
        if (StringUtils.isEmpty(oldVer) && StringUtils.isEmpty(ver)) {
            HashMap<String, Object> param = new HashMap<String, Object>();
            postRequest(17, updateHandler, "/GoodsA/AllClass", param);
            return true;
        } else if (ver != null && !ver.equals(oldVer)) {
            HashMap<String, Object> param = new HashMap<String, Object>();
            postRequest(17, updateHandler, "/GoodsA/AllClass", param);
            return true;
        }
        //更新城市数据
        isNewCity();
        return false;
    }

    public void getUserInfo() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Token", CommonTools.getToken(this));
        MainActivity.postRequest(19, updateHandler, "/UserA/UserDetail", param);
    }

    public static UserEntity getUser() {
        if(user == null)
            return new UserEntity();
        return user;
    }

    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 13) {//更新城市
                if (msg.arg1 > 0) {
                    AreaData areaData = null;
                    try {
                        areaData = new Gson().fromJson(msg.obj.toString(), AreaData.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (areaData != null) {
                        if (areaData.getProvince().size() > 0) {
                            AreaDBDao dao;
                            dao = new AreaDBDao(HXService.this);
                            dao.deletetable();
                            dao.addAreaAll(areaData);
                            CommonTools.setOldAreaVer(HXService.this, areaData.getVer());
                        }
                    }
                }
            } else if (msg.what == 17) {//更新分类
                if (msg.arg1 > 0) {
                    ClassicData classicData = null;
                    try {
                        classicData = new Gson().fromJson(msg.obj.toString(), ClassicData.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (classicData != null) {
                        if (classicData.getClassX().size() > 0) {
                            ClassifyDBDao dao;
                            dao = new ClassifyDBDao(HXService.this);
                            dao.deletetable();
                            dao.addClassifyAll(classicData);
                            CommonTools.setOldClassVer(HXService.this, classicData.getVer());
                        }
                    }
                }
                //更新城市数据
                isNewCity();
            } else if (msg.what == 19) {
                if (msg.arg1 > 0) {
                    user = null;
                    try {
                        user = new Gson().fromJson(msg.obj.toString(), UserEntity.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if(user != null){

                    }
                }
                isNewClassity();
            }
        }
    };

    // 访问服务器
    private DefMessage message;
    private HashMap<BsModel, Handler> interBack = new HashMap<BsModel, Handler>();

    public void getRequest(int what, Handler sHandler, String interString,
                           HashMap<String, String> param) {
        RequestGetModel model = new RequestGetModel(interString, param);
        model.setWhat(what);
        interBack.put(model, sHandler);
        HttpRequest.getRequest(model, mHandler);
    }

    public void postRequest(int what, Handler sHandler, String interString,
                            HashMap<String, Object> param) {
        RequestPostModel model = new RequestPostModel(interString, param);
        model.setWhat(what);
        interBack.put(model, sHandler);
        HttpRequest.postRequest(model, mHandler);
    }

    public void postImgRequest(int what, Handler sHandler, String interString,
                               HashMap<String, Object> param) {
        RequestPostImgModel model = new RequestPostImgModel(interString, param);
        model.setWhat(what);
        interBack.put(model, sHandler);
        HttpRequest.postImgRequest(model, mHandler);
    }

    public void putRequest(int what, Handler sHandler, String interString,
                           HashMap<String, String> param) {
        RequestPutModel model = new RequestPutModel(interString, param);
        model.setWhat(what);
        interBack.put(model, sHandler);
        HttpRequest.putRequest(model, mHandler);
    }

    private void parseModel(BsModel model) {
        if (model instanceof RequestGetModel) {
            RequestGetModel getModel = (RequestGetModel) model;
            message = (DefMessage) model.getResult();
            Message msg = new Message();
            msg.what = getModel.getWhat();
            if (message != null) {
                if (message.isSuc())
                    msg.arg1 = 1;
                else
                    msg.arg1 = -1;

                msg.obj = message.getData();
                if (!message.isSuc())
                    msg.obj = message.getMsg();
                if (msg.obj == null)
                    msg.obj = "";
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            } else {
                msg.arg1 = -1;
                msg.obj = "无网络连接！";
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            }
        } else if (model instanceof RequestPostModel) {
            RequestPostModel postModel = (RequestPostModel) model;
            message = (DefMessage) model.getResult();
            Message msg = new Message();
            msg.what = postModel.getWhat();
            if (message != null) {
                if (message.isSuc())
                    msg.arg1 = 1;
                else
                    msg.arg1 = -1;
                msg.obj = message.getData();
                if (!message.isSuc())
                    msg.obj = message.getMsg();
                if (msg.obj == null)
                    msg.obj = "";
                Log.i(TAG, msg.obj.toString());
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            } else {
                msg.arg1 = -1;
                msg.obj = "无网络连接！";
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            }
        } else if (model instanceof RequestPostImgModel) {
            RequestPostImgModel postModel = (RequestPostImgModel) model;
            message = (DefMessage) model.getResult();
            Message msg = new Message();
            msg.what = postModel.getWhat();
            if (message != null) {
                if (message.isSuc())
                    msg.arg1 = 1;
                else
                    msg.arg1 = -1;
                msg.obj = message.getData();
                if (!message.isSuc())
                    msg.obj = message.getMsg();
                if (msg.obj == null)
                    msg.obj = "";
                Log.i(TAG, msg.obj.toString());
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            } else {
                msg.arg1 = -1;
                msg.obj = "无网络连接！";
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            }
        } else if (model instanceof RequestPutModel) {
            RequestPutModel putModel = (RequestPutModel) model;
            message = (DefMessage) model.getResult();
            Message msg = new Message();
            msg.what = putModel.getWhat();
            if (message != null) {
                if (message.isSuc())
                    msg.arg1 = 1;
                else
                    msg.arg1 = -1;
                msg.obj = message.getData();
                if (!message.isSuc())
                    msg.obj = message.getMsg();
                if (msg.obj == null)
                    msg.obj = "";
                Log.i(TAG, msg.obj.toString());
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            } else {
                msg.arg1 = -1;
                msg.obj = "无网络连接！";
                interBack.get(model).sendMessage(msg);
                interBack.remove(model);
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handleCallBack(msg);
        }
    };

    private void handleCallBack(Message msg) {
        BsModel model = null;
        if (msg.obj != null) {
            model = (BsModel) msg.obj;
        }
        switch (msg.what) {
            case 0:
                parseModel(model);
                break;
            case 1:
                parseModel(model);
                break;
            default:
                break;
        }
    }

    ;
}
