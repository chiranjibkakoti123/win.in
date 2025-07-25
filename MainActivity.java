/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ClipData
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.webkit.DownloadListener
 *  android.webkit.ValueCallback
 *  android.webkit.WebChromeClient
 *  android.webkit.WebChromeClient$FileChooserParams
 *  android.webkit.WebSettings
 *  android.webkit.WebSettings$LayoutAlgorithm
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.ProgressBar
 *  com.za.win0091.MainActivity-IA
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 */
package com.za.win0091;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.za.win0091.MainActivity-IA;
import com.za.win0091.R;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public class MainActivity
extends Activity {
    private static final int FILE_CHOOSER_RESULT_CODE = 10000;
    WebView mWebView;
    ProgressBar progressBar1;
    private ValueCallback<Uri> uploadMessage;
    public ValueCallback<Uri[]> uploadMessageAboveL;
    private final String webPath;

    public MainActivity() {
        this.webPath = "https://www.eduavst.com";
    }

    private void initView() {
        this.mWebView = (WebView)this.findViewById(R.id.wv_web);
        this.progressBar1 = (ProgressBar)this.findViewById(R.id.progressBar1);
        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.supportMultipleWindows();
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setCacheMode(2);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        this.mWebView.requestFocusFromTouch();
        this.mWebView.setDownloadListener((DownloadListener)new MyWebViewDownLoadListener(this, null));
        this.mWebView.setWebChromeClient(new WebChromeClient(this){
            final MainActivity this$0;
            {
                this.this$0 = mainActivity;
            }

            public void onProgressChanged(WebView webView, int n) {
                if (n == 100) {
                    this.this$0.progressBar1.setVisibility(8);
                } else {
                    this.this$0.progressBar1.setVisibility(0);
                    this.this$0.progressBar1.setProgress(n);
                }
            }

            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                this.this$0.uploadMessageAboveL = valueCallback;
                this.this$0.openImageChooserActivity();
                return true;
            }
        });
        this.mWebView.loadUrl("https://www.eduavst.com");
        this.mWebView.setWebViewClient(new WebViewClient(this){
            final MainActivity this$0;
            {
                this.this$0 = mainActivity;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String string2) {
                if (!string2.contains((CharSequence)"https://www.eduavst.com")) {
                    webView = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
                    this.this$0.startActivity((Intent)webView);
                } else {
                    webView.loadUrl(string2);
                }
                return true;
            }
        });
    }

    private void onActivityResultAboveL(int n, int n2, Intent uriArray) {
        if (n == 10000 && this.uploadMessageAboveL != null) {
            if (n2 == -1 && uriArray != null) {
                String string2 = uriArray.getDataString();
                ClipData clipData = uriArray.getClipData();
                if (clipData != null) {
                    Uri[] uriArray2 = new Uri[clipData.getItemCount()];
                    n = 0;
                    while (true) {
                        uriArray = uriArray2;
                        if (n < clipData.getItemCount()) {
                            uriArray2[n] = clipData.getItemAt(n).getUri();
                            ++n;
                            continue;
                        }
                        break;
                    }
                } else {
                    uriArray = null;
                }
                if (string2 != null) {
                    uriArray = new Uri[]{Uri.parse((String)string2)};
                }
            } else {
                uriArray = null;
            }
            this.uploadMessageAboveL.onReceiveValue((Object)uriArray);
            this.uploadMessageAboveL = null;
        }
    }

    private void openImageChooserActivity() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/jpeg", "image/jpg", "image/png"});
        this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)"Image Chooser"), 10000);
    }

    protected void onActivityResult(int n, int n2, Intent valueCallback) {
        super.onActivityResult(n, n2, (Intent)valueCallback);
        if (n == 10000) {
            if (this.uploadMessage == null && this.uploadMessageAboveL == null) {
                return;
            }
            Uri uri = valueCallback != null && n2 == -1 ? valueCallback.getData() : null;
            if (this.uploadMessageAboveL != null) {
                this.onActivityResultAboveL(n, n2, (Intent)valueCallback);
            } else {
                valueCallback = this.uploadMessage;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)uri);
                    this.uploadMessage = null;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main);
        this.initView();
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (n == 4 && this.mWebView.canGoBack()) {
            if (this.mWebView.getUrl().contains((CharSequence)"https://www.eduavst.com")) {
                this.finish();
            } else {
                this.mWebView.goBack();
            }
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }

    private class MyWebViewDownLoadListener
    implements DownloadListener {
        final MainActivity this$0;

        private MyWebViewDownLoadListener(MainActivity mainActivity) {
            this.this$0 = mainActivity;
        }

        /* synthetic */ MyWebViewDownLoadListener(MainActivity mainActivity, MainActivity-IA mainActivity-IA) {
            this(mainActivity);
        }

        public void onDownloadStart(String string2, String string3, String string4, String string5, long l) {
            if (string2.contains((CharSequence)"action=download")) {
                string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)string2));
                this.this$0.startActivity((Intent)string2);
            }
        }
    }
}

