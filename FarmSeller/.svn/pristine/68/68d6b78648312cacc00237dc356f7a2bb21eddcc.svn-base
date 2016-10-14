package com.aotuo.vegetable.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.custom.nostra13.universalimageloader.core.ImageLoader;
import com.custom.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Stack;

public class FarmApp extends Application {
    public static Context applicationContext;
    public Stack<Activity> activityStack;
    public static FarmApp instance;
    public static String key;

    public FarmApp() {
    }

    public static FarmApp getInstance() {
        if (null == instance) {
            synchronized (FarmApp.class) {
                if (null == instance) {
                    instance = new FarmApp();
                }
            }
        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        ImageLoader.getInstance().init(
                ImageLoaderConfiguration.createDefault(FarmApp.this));

    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    private static DisplayMetrics dm = new DisplayMetrics();

    public static DisplayMetrics getDisplayMetrics() {
        return dm;
    }

    public void exitApplication() {
        System.out.println("exitApplication");
        System.exit(0);
    }
}
