package lvxingdaka.com.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.umeng.analytics.MobclickAgent;
import lvxingdaka.com.R;
import lvxingdaka.com.app.App;

/**
 * Created by KingYang on 16/4/14.
 * E-Mail: admin@kingyang.cn
 */
public class BaseActivity extends AppCompatActivity {
    protected ImageLoader imageLoader;// 图片异步加载
    protected RequestQueue queue;// 请求队列
    protected AlertDialog pay1Dialog;

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App app = (App) getApplication();
        queue = app.getQueue();
        imageLoader = app.getImageLoader();

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay, null);
        pay1Dialog = new AlertDialog.Builder(this).setView(view).create();
        pay1Dialog.setCanceledOnTouchOutside(false);
        TextView vip1Btn = (TextView) view.findViewById(R.id.vip1Btn);
        vip1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay1Dialog.dismiss();
                PayActivity.createInstance(BaseActivity.this, 1);
            }
        });
        TextView vip2Btn = (TextView) view.findViewById(R.id.vip2Btn);
        vip2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay1Dialog.dismiss();
                PayActivity.createInstance(BaseActivity.this, 2);
            }
        });
    }

    protected Toolbar setToolBarTitle(int toolbarId, CharSequence title) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setTitle(title);
        return toolbar;
    }

    protected Toolbar setUpCommonBackTooblBar(int toolbarId, CharSequence title) {
        Toolbar toolbar = setToolBarTitle(toolbarId, title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        return toolbar;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
