package com.aotuo.vegetable.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 牛XX on 2016/9/18.
 */

public class ReLoginReceiver extends BroadcastReceiver {
    public static final String RE_LOGIN = "aotuo.relogin";
    private HashMap<Activity, Activity> mapAct = new HashMap<Activity, Activity>();

    public ReLoginReceiver() {
        super();
    }

    public void setActivity(Activity activity) {
        mapAct.put(activity, activity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (RE_LOGIN.equals(intent.getAction())) {
            Iterator<Map.Entry<Activity, Activity>> iterator = mapAct.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if(entry.getKey() != null){
                    Activity activity = (Activity) entry.getKey();
                    String actName = activity.getClass().getName();
                    if (!actName.contains("LoginActivity"))
                        if (!actName.contains("MainActivity"))
                            activity.finish();
                }
            }
            mapAct.clear();
        }
    }

    protected void finishActivity(Activity activity) {
    }

    protected String getRunningActivityName(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}
