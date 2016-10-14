package com.aotuo.vegetable.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 在activity中由webview打开url
 * 
 * @author 牛XX
 * 
 */
public class WebViewOpenUrl {
	private static Activity mContext = null;
	private static WebViewOpenUrl instance = null;
	private Dialog myDialog;

	public static WebViewOpenUrl getInstence(Activity mcontext) {
		if (instance == null) {
			instance = new WebViewOpenUrl();
		}
		mContext = mcontext;
		return instance;
	}

	@SuppressLint("SetJavaScriptEnabled")
	public void openurl(final WebView mWebView) {
		myDialog = DialogUtil.createDialog(mContext, "");
		myDialog.setCancelable(true);
		// 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("tel:")) {
					Intent intent = new Intent(Intent.ACTION_DIAL, Uri
							.parse(url));
					mContext.startActivity(intent);
				} else if (url.startsWith("http:") || url.startsWith("https:")) {
					view.loadUrl(url);
				}
				// 返回值是true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器去打开
				return true;
			}
			public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
				if (url.contains("adpro.cn")) {
					return new WebResourceResponse(null, null, null);
				}
				return null;
			}
			// WebViewClient帮助WebView去处理一些页面控制和请求通知

			/**
			 * 在web加载完之后可以选择调用js的一些方法
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				// view.loadUrl("javascript:setTarget()");
			}

			// 开始加载web
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
		});
		// 启用支持JavaScript
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);// 支持js
		settings.setUseWideViewPort(true);// 调整图片大小适合webview大小显示
		settings.setLoadWithOverviewMode(true);// 自适应屏幕
		// WebView加载页面优先使用缓存加载
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

		mWebView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {

				// newProgress 1-100之间的整数
				if (newProgress == 100) {
					// 网页加载完毕，关闭ProgressDialog
					closeDialog();
				} else {
					// 网页正在加载,打开ProgressDialog
					openDialog();
				}
			}

			private void closeDialog() {
				if (myDialog != null && myDialog.isShowing()) {
					myDialog.dismiss();
				}
			}

			private void openDialog() {
				if (myDialog != null && !mContext.isFinishing()) {
					myDialog.show();
				}
			}
		});
	}

}
