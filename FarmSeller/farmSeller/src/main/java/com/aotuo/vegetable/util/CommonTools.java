package com.aotuo.vegetable.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.aotuo.vegetable.act.MainActivity;
import com.aotuo.vegetable.entity.AreaMessage;
import com.aotuo.vegetable.entity.ClassifyMessage;
import com.aotuo.vegetable.sqlite.AreaDBDao;
import com.aotuo.vegetable.sqlite.ClassifyDBDao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @version V_3.0.0
 * @date 2015年03月23日
 * @description 应用程序的公共工具类
 */
public class CommonTools {
    static MediaPlayer mpalyer;
    static MediaRecorder mr;
    static ProgressDialog progressDialog;

    public static ClassifyMessage getClassicFromId(Context context, String classxxxId) {
        ClassifyDBDao dao;
        dao = new ClassifyDBDao(context);
        return dao.getClassify(classxxxId);
    }

    /**
     * 获取登录token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("key", null);
    }

    /**
     * 保存登录token
     *
     * @param context
     * @param token
     * @param classVer
     */
    public static void setToken(Context context, String token, String classVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("key", token);
        editor.putString("classVer", classVer);
        editor.commit();
    }

    /**
     * 保存登录token
     *
     * @param context
     * @param token
     */
    public static void setToken(Context context, String token) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("key", token);
        editor.commit();
    }

    /**
     * 获取用户名
     *
     * @param context
     * @return
     */
    public static String getUserName(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("username", null);
    }

    /**
     * 设置需要要交易密码
     *
     * @param context
     * @param b
     */
    public static void setCheckTPWD(Context context, boolean b) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putBoolean("CheckTPWD", b);
        editor.commit();
    }

    /**
     * 获取是否需要交易密码
     *
     * @param context
     * @return
     */
    public static boolean getCheckTPWD(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getBoolean("CheckTPWD", true);
    }

    /**
     * 获取设备信息
     *
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return tm.getDeviceId() + "-" + tm.getDeviceSoftwareVersion();
    }

    /**
     * 获取分类版本
     *
     * @param context
     * @return
     */
    public static String getClassVer(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("classVer", null);
    }

    /**
     * 获取下载过分类版本
     *
     * @param context
     * @return
     */
    public static String getOldClassVer(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("oldClassVer", null);
    }

    /**
     * 保存下载过的分类版本
     *
     * @param context
     * @param classVer
     */
    public static void setOldClassVer(Context context, String classVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("oldClassVer", classVer);
        editor.commit();
    }

    /**
     * 获取用户类型
     * 卖方 11,12,13,14
     * 买方 21，22
     *
     * @param context
     * @return
     */
    public static String getUserType(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("usertype", null);
    }

    /**
     * 保存用户类型
     *
     * @param context
     * @param usertype
     */
    public static void setUserType(Context context, String usertype) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("usertype", usertype);
        editor.commit();
    }

    /**
     * 获取城市版本
     *
     * @param context
     * @return
     */
    public static String getAreaVer(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("AreaVer", null);
    }

    /**
     * 保存城市版本
     *
     * @param context
     * @param areaVer
     */
    public static void setAreaVer(Context context, String areaVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("AreaVer", areaVer);
        editor.commit();
    }

    /**
     * 获取下载过城市版本
     *
     * @param context
     * @return
     */
    public static String getOldAreaVer(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("OldAreaVer", null);
    }

    /**
     * 保存下载过城市版本
     *
     * @param context
     * @param areaVer
     */
    public static void setOldAreaVer(Context context, String areaVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("OldAreaVer", areaVer);
        editor.commit();
    }

    /**
     * 获取短信额度
     *
     * @param context
     * @return
     */
    public static boolean getSmsSw(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getBoolean("smsSw", true);
    }

    /**
     * 保存短信额度
     *
     * @param context
     * @param areaVer
     */
    public static void setSmsSw(Context context, int areaVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        if (areaVer == 1)
            editor.putBoolean("smsSw", true);
        else
            editor.putBoolean("smsSw", false);
        editor.commit();
    }

    /**
     * 获取短信额度
     *
     * @param context
     * @return
     */
    public static float getSmsLimit(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getFloat("limit", 500);
    }

    /**
     * 保存短信额度
     *
     * @param context
     * @param areaVer
     */
    public static void setSmsLimit(Context context, float areaVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putFloat("limit", areaVer);
        editor.commit();
    }

    /**
     * 获取所属市场名
     *
     * @param context
     * @return
     */
    public static String getMarketName(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("marketName", null);
    }

    /**
     * 获取所属市场编号
     *
     * @param context
     * @return
     */
    public static String getMarketNum(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("marketNum", null);
    }

    /**
     * 保存所属市场
     *
     * @param context
     * @param marketName
     * @param marketNum
     */
    public static void setMarket(Context context, String marketName, String marketNum) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("marketName", marketName);
        editor.putString("marketNum", marketNum);
        editor.commit();
    }


    /**
     * //买方
     * 单位食堂买本地=21,(默认)
     * 摊位买外地市场=22
     *
     * @param context
     * @return
     */
    public static boolean isBuy(Context context) {
        String userType = CommonTools.getUserType(context);
        if (StringUtils.isEmpty(userType))
            return false;
        if (userType.contains("21") || userType.contains("22"))
            return true;
        return false;
    }

    public static boolean isShowAll(Context context) {
        String userType = CommonTools.getUserType(context);
        if (StringUtils.isEmpty(userType))
            return false;
        if (userType.contains("22"))
            return true;
        return false;
    }

    /**
     * //卖方
     * 摊位卖本地单位用户=11,(默认)
     * 摊位看外地市场 = 12,
     * 摊位卖外地市场 =13,
     * 摊位发布到全网=14,
     *
     * @param context
     * @return
     */
    public static boolean isSell(Context context) {
        String userType = CommonTools.getUserType(context);
        if (StringUtils.isEmpty(userType))
            return false;
        if (userType.contains("11") || userType.contains("12")
                || userType.contains("13") || userType.contains("14"))
            return true;
        return false;
    }

    public static boolean isSeeAll(Context context) {
        String userType = CommonTools.getUserType(context);
        if (StringUtils.isEmpty(userType))
            return false;
        if (userType.contains("14"))
            return true;
        return false;
    }

    /**
     * 新闻
     *
     * @param context
     * @return
     */
    public static String getNewsVer(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("newsVer", null);
    }

    /**
     * 新闻
     *
     * @param context
     * @param newVer
     */
    public static void setNewsVer(Context context, String newVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("newsVer", newVer);
        editor.commit();
    }

    /**
     * 消息
     *
     * @param context
     * @return
     */
    public static String getMessVer(Context context) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        return shared_key.getString("messVer", null);
    }

    /**
     * 消息
     *
     * @param context
     * @param newVer
     */
    public static void setMessVer(Context context, String newVer) {
        SharedPreferences shared_key;
        shared_key = context.getSharedPreferences("shared_key", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_key.edit();
        editor.putString("messVer", newVer);
        editor.commit();
    }

    public static String getUserSn() {
        if (MainActivity.getUser() != null)
            return MainActivity.getUser().getNum();
        else
            return "";
    }

    public static String getUserPic() {
        if (MainActivity.getUser() != null)
            return MainActivity.getUser().getHeadImg();
        else
            return "";
    }

    public static String getAddress(Context context, String areaNum) {
        if (StringUtils.isEmpty(areaNum))
            return "";
        AreaDBDao dao = new AreaDBDao(context);
        AreaMessage amArea = dao.getArea(areaNum);
        AreaMessage amCity = null;
        if (amArea != null)
            amCity = dao.getArea(amArea.getGc_parent_id());
        AreaMessage amPro = null;
        if (amCity != null)
            amPro = dao.getArea(amCity.getGc_parent_id());

        StringBuilder sb = new StringBuilder();
        if (amPro != null)
            sb.append(amPro.getGc_name()).append(" ");
        if (amCity != null)
            sb.append(amCity.getGc_name()).append(" ");
        if (amArea != null)
            sb.append(amArea.getGc_name()).append(" ");

        return sb.toString();
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void showToast(Context context, String content) {
        if (context != null) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }

    public static void showProgressDialog(Context context, String title,
                                          String msg) {
        if (progressDialog != null && progressDialog.isShowing() == true) {
            return;
        }
        progressDialog = ProgressDialog.show(context, title, msg, true, true);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public static void showProgressDialogLogin(Context context, String title,
                                               String msg) {
        if (progressDialog != null && progressDialog.isShowing() == true) {
            return;
        }
        progressDialog = ProgressDialog.show(context, title, msg, true, true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    /**
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    // 时间格式
    public static String getCurrentTime() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    // 时间格式
    public static String getMsgCurrentTime() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    // 时间格式
    public static String currentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    // 时间格式
    public static Date currentDate2() {
        Date date = new Date();
        return date;
    }

    // 时间格式
    public static String currentDateAndTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    // 时间格式
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    // 时间格式
    public static String getDate(String date) {
        long time = Long.parseLong(date) * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(time);
        String currentDate = sdf.format(date1);
        return currentDate;
    }

    // 时间格式
    public static String currentDateAndTime(String longtime) {
        long time = Long.parseLong(longtime) * 1000;
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    // 时间格式
    public static String formatyMdHm(String longtime) {
        long time = Long.parseLong(longtime) * 1000;
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    // 时间格式
    public static String currentDate1(String longtime) {
        long time = Long.parseLong(longtime) * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        String currentDate = sdf.format(date);
        return currentDate;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param time
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static String getFirstDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return sdf.format(c.getTime());
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static String getLastDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return sdf.format(c.getTime());
    }

    public static String currentTime() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String str0 = String.valueOf(hour);
        String str = String.valueOf(minute);
        String str1 = String.valueOf(second);
        if (hour < 10) {
            str0 = "0" + hour;
        }
        if (minute < 10) {
            str = "0" + minute;
        }
        if (second < 10) {
            str1 = "0" + second;
        }
        return str0 + ":" + str + ":" + str1;
    }

    @SuppressLint("SimpleDateFormat")
    public static int compare_date(String DATE1, String DATE2) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 采用simpleDateFormat处理日期(yyyy-mm-dd
        // hh:mm:ss.0)
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {// 比较long型的毫秒数
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static long compare_int(String a, String b) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Long c;
        long d = 0;
        try {
            c = sf.parse(b).getTime() - sf.parse(a).getTime();
            d = c / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                // do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        source += " "; // 在传入的source后面加上一个空字符。返回的时候trim掉就OK了
        if (!containsEmoji(source)) {
            return source;// 如果不包含，直接返回
        }
        // 到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;// 如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    private static final double EARTH_RADIUS = 6378137.0;

    // 返回单位是米
    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static void hideSoftkey(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftkey(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
    }

    public static boolean isShowSoftkey(Activity context) {
        if (context.getWindow().getAttributes().softInputMode
                == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
            return true;
        }
        return false;
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}