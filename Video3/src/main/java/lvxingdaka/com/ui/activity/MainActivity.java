package lvxingdaka.com.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import lvxingdaka.com.R;
import lvxingdaka.com.app.Consts;
import lvxingdaka.com.ui.adapter.FragmentAdapter;
import lvxingdaka.com.ui.fragment.ChannelFragment;
import lvxingdaka.com.ui.fragment.HomeFragment;
import lvxingdaka.com.ui.fragment.UserFragment;
import lvxingdaka.com.utils.SPUtil;
import lvxingdaka.com.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private TextView tabHome, tabChannel, tabStar, tabUser;
    private Toolbar toolbar;
    private long lastBack;
    private AlertDialog pay2Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = setToolBarTitle(R.id.toolbar, getTitle());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ChannelFragment());
        fragments.add(new UserFragment());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);

        tabHome = (TextView) findViewById(R.id.tabHome);
        tabChannel = (TextView) findViewById(R.id.tabChannel);
        tabStar = (TextView) findViewById(R.id.tabStar);
        tabUser = (TextView) findViewById(R.id.tabUser);
        tabHome.setOnClickListener(this);
        tabChannel.setOnClickListener(this);
        tabStar.setOnClickListener(this);
        tabUser.setOnClickListener(this);
        //
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay2, null);
        pay2Dialog = new AlertDialog.Builder(this).setView(view).create();
        pay2Dialog.setCanceledOnTouchOutside(false);
        final TextView pay2Btn = (TextView) view.findViewById(R.id.pay2Btn);
        pay2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay2Dialog.dismiss();
                PayActivity.createInstance(MainActivity.this, 3);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == tabHome) {
            viewPager.setCurrentItem(0);
            toolbar.setTitle(R.string.app_name);
        } else if (v == tabChannel) {
            viewPager.setCurrentItem(1);
            toolbar.setTitle(R.string.channel);
        } else if (v == tabStar) {
            viewPager.setCurrentItem(2);
            toolbar.setTitle(R.string.star);
        } else if (v == tabUser) {
            viewPager.setCurrentItem(3);
            toolbar.setTitle(R.string.user);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 设置DrawableTop
        Drawable home = getResources().getDrawable(position == 0 ? R.mipmap.tab_home_focus : R.mipmap.tab_home_normal);
        home.setBounds(0, 0, home.getMinimumWidth(), home.getMinimumHeight());
        tabHome.setCompoundDrawables(null, home, null, null);
        tabHome.setTextColor(position == 0 ? getResources().getColor(R.color.tab_focus) : Color.WHITE);
        Drawable channel = getResources().getDrawable(position == 1 ? R.mipmap.tab_channel_focus : R.mipmap.tab_channel_normal);
        channel.setBounds(0, 0, channel.getMinimumWidth(), channel.getMinimumHeight());
        tabChannel.setCompoundDrawables(null, channel, null, null);
        tabChannel.setTextColor(position == 1 ? getResources().getColor(R.color.tab_focus) : Color.WHITE);
        Drawable star = getResources().getDrawable(position == 2 ? R.mipmap.tab_star_focus : R.mipmap.tab_star_normal);
        star.setBounds(0, 0, star.getMinimumWidth(), star.getMinimumHeight());
        tabStar.setCompoundDrawables(null, star, null, null);
        tabStar.setTextColor(position == 2 ? getResources().getColor(R.color.tab_focus) : Color.WHITE);
        Drawable user = getResources().getDrawable(position == 3 ? R.mipmap.tab_user_focus : R.mipmap.tab_user_normal);
        user.setBounds(0, 0, user.getMinimumWidth(), user.getMinimumHeight());
        tabUser.setCompoundDrawables(null, user, null, null);
        tabUser.setTextColor(position == 3 ? getResources().getColor(R.color.tab_focus) : Color.WHITE);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void showPay1Dialog() {
        pay1Dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastBack < 1000) {
            if (SPUtil.getInt(this, Consts.SP.VIP) > 0) {
                super.onBackPressed();
            } else {// 退出时没有付费则弹出特价永久会员
                pay2Dialog.show();
            }
        } else {
            ToastUtil.show(this, "再按一次退出应用");
        }
        lastBack = System.currentTimeMillis();
    }
}
