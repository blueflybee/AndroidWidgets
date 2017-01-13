package io.github.changjiashuai.androidwidgets;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

/**
 * 没看到效果
 */
public class WindowDemoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature();的取值
        // 1.DEFAULT_FEATURES：系统默认状态，一般不需要指定
        // 2.FEATURE_CONTEXT_MENU：启用ContextMenu，默认该项已启用，一般无需指定
        // 3.FEATURE_CUSTOM_TITLE：自定义标题。当需要自定义标题时必须指定。如：标题是一个按钮时
        // 4.FEATURE_INDETERMINATE_PROGRESS：不确定的进度
        // 5.FEATURE_LEFT_ICON：标题栏左侧的图标
        // 6.FEATURE_NO_TITLE：无标题
        // 7.FEATURE_OPTIONS_PANEL：启用“选项面板”功能，默认已启用。
        // 8.FEATURE_PROGRESS：进度指示器功能
        // 9.FEATURE_RIGHT_ICON:标题栏右侧的图标
        /** FEATURE_INDETERMINATE_PROGRESS：不确定的进度 */
         showFEATURE_INDETERMINATE_PROGRESS();
        /** FEATURE_CUSTOM_TITLE：自定义标题 */
        // showFEATURE_CUSTOM_TITLE();
        /** FEATURE_LEFT_ICON：标题栏左侧的图标 */
        // showFEATURE_LEFT_ICON();
        /** FEATURE_NO_TITLE：无标题 */
        // showFEATURE_NO_TITLE();
        /** FEATURE_PROGRESS:进度条 */
//        showFEATURE_PROGRESS();
    }

    private void showFEATURE_INDETERMINATE_PROGRESS() {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_window_demo);
        getWindow().setFeatureInt(Window.FEATURE_INDETERMINATE_PROGRESS,
                R.layout.progress);
        // 必须得加上否则显示不出效果 可以通过这个在以后设置显示或隐藏
        setProgressBarIndeterminateVisibility(true);
    }

    private void showFEATURE_CUSTOM_TITLE() {
        // 自定义标题,当需要自定义标题时必须指定
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_window_demo);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.customtitle);
    }

    private void showFEATURE_LEFT_ICON() {
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_window_demo);
        getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
                R.mipmap.ic_launcher);
    }

    private void showFEATURE_NO_TITLE() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_window_demo);
        // 加上这句设置为全屏 不加则只隐藏title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void showFEATURE_PROGRESS() {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setProgressBarVisibility(true);
        setContentView(R.layout.activity_window_demo);
        setTitle("");
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
                Window.PROGRESS_VISIBILITY_ON);
        // 通过线程来改变ProgressBar的值
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        Message m = new Message();
                        m.what = (i + 1) * 20;
                        WindowDemoActivity.this.myMessageHandler.sendMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    Handler myMessageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 设置标题栏中前景的一个进度条进度值
            setProgress(100 * msg.what);
            // 设置标题栏中后面的一个进度条进度值
            setSecondaryProgress(100 * msg.what + 10);
            super.handleMessage(msg);
        }
    };
}
