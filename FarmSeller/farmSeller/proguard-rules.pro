# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

#-libraryjars /libs/easemobchat_2.2.6.jar
#-dontwarn easemobchat.**
#-keep class com.easemob.**{*;}
#-keep class movell.sasl.client.**{*;}
#-keep class conami.javaBells.**{*;}
#-keep class com.umeng.**{*;}
#-keep class com.baidu.**{*;}
#-keep class com.tencent.**{*;}
#-keep class com.xiaomi.**{*;}

-dontwarn com.xonami.javaBells.**
-keep class com.xonami.javaBells.** {
*;
}
-dontwarn com.novell.sasl.client.**
-keep class com.novell.sasl.client.** {
*;
}
-dontwarn com.baidu.**
-keep class com.baidu.** {
*;
}
-dontwarn com.baidu.mapapi.map.**
-keep class com.baidu.mapapi.map.** {
*;
}
-dontwarn com.baidu.mapapi.**
-keep class com.baidu.mapapi.** {
*;
}
-dontwarn com.tencent.stat.**
-keep class com.tencent.stat.** {
*;
}
-dontwarn com.tencent.**
-keep class com.tencent.** {
*;
}
-dontwarn com.sina.**
-keep class com.sina.** {
*;
}
-dontwarn com.umeng.analytics.**
-keep class com.umeng.analytics.** {
*;
}
-dontwarn com.umeng.**
-keep class com.umeng.** {
*;
}
-dontwarn com.google.android.gms.**
-keep class com.google.android.gms.** {
*;
}
-dontwarn org.android.**
-keep class org.android.** {
*;
}
-dontwarn com.hp.hpl.sparta.**
-keep class com.hp.hpl.sparta.** {
*;
}
-dontwarn net.sourceforge.pinyin4j.**
-keep class net.sourceforge.pinyin4j.** {
*;
}

-keepattributes EnclosingMetho

-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}

#如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
#gson
#-libraryjars libs/gson-2.2.2.jar
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }