package io.github.changjiashuai.androidwidgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewDemoActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private WebView mWebView;
    private boolean isAnimStart = false;
    private int currentProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);

        // 显示up向上导航
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWebView = (WebView) findViewById(R.id.webView);
//        if (Build.VERSION.SDK_INT >= 19) {
//            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }
        // 拦截url
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setAlpha(1.0f);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;   // 在当前webview内部打开url
            }
        });

        // 获取网页加载进度
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                currentProgress = mProgressBar.getProgress();
                if (newProgress >= 100 && !isAnimStart) {
                    // 防止调用多次动画
                    isAnimStart = true;
                    mProgressBar.setProgress(newProgress);
                    // 开启属性动画让进度条平滑消失
                    startDismissAnimation(mProgressBar.getProgress());
                } else {
                    // 开启属性动画让进度条平滑递增
                    startProgressAnimation(newProgress);
                }
            }
        });

        // 支持js
        mWebView.getSettings().setJavaScriptEnabled(true);

        // 加载url
        String url = "https://www.baidu.com";
        mWebView.loadUrl(url);
    }

    /**
     * progressBar递增动画
     */
    private void startProgressAnimation(int newProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(mProgressBar, "progress", currentProgress, newProgress);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * progressBar消失动画
     */
    private void startDismissAnimation(final int progress) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mProgressBar, "alpha", 1.0f, 0.0f);
        anim.setDuration(1500);  // 动画时长
        anim.setInterpolator(new DecelerateInterpolator());     // 减速
        // 关键, 添加动画进度监听器
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = valueAnimator.getAnimatedFraction();      // 0.0f ~ 1.0f
                int offset = 100 - progress;
                mProgressBar.setProgress((int) (progress + offset * fraction));
            }
        });

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
                mProgressBar.setProgress(0);
                mProgressBar.setVisibility(View.GONE);
                isAnimStart = false;
            }
        });
        anim.start();
    }

    /**
     * 监听up键
     * id = android.R.id.home
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 监听back键
     * 在WebView中回退导航
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {  // 返回键的KEYCODE
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;  // 拦截
            } else {
                return super.onKeyDown(keyCode, event);   //  放行
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
