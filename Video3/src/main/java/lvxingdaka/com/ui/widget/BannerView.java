package lvxingdaka.com.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import lvxingdaka.com.R;
import lvxingdaka.com.bean.Video;
import lvxingdaka.com.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

public class BannerView extends FrameLayout implements View.OnClickListener, OnPageChangeListener {
    private int mCount;
    private Context mContext;
    private int currentItem;
    private List<Video> mData = new ArrayList<>();
    private ViewPager viewPager;
    private LinearLayout dotContainer;
    private List<ImageView> images = new ArrayList<>();
    private List<ImageView> dots = new ArrayList<>();
    private BannerViewListener mListener;
    private Handler handler = new Handler();
    private long delayTime = 4000;// 轮播间隔时间
    private Runnable autoPlay = new Runnable() {
        @Override
        public void run() {
            currentItem = currentItem % (mCount + 1) + 1;
            if (currentItem == 1) {
                viewPager.setCurrentItem(currentItem, false);
                handler.post(autoPlay);
            } else {
                viewPager.setCurrentItem(currentItem);
                handler.postDelayed(autoPlay, delayTime);
            }
        }
    };


    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        // 默认高度180dp
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(context, 180)));
        // 图片ViewPager
        viewPager = new ViewPager(mContext);
        LayoutParams layoutParams1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(viewPager, layoutParams1);
        // 指示器容器
        dotContainer = new LinearLayout(mContext);
        int padding = DisplayUtil.dip2px(getContext(), 6);
        dotContainer.setPadding(padding, padding, padding, padding);
        LayoutParams layoutParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams2.gravity = Gravity.BOTTOM;
        dotContainer.setGravity(Gravity.CENTER);
        dotContainer.setBackgroundColor(Color.parseColor("#40000000"));
        addView(dotContainer, layoutParams2);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context) {
        this(context, null);
    }

    public void setData(List<Video> data, BannerViewListener listener) {
        mData = data;
        mListener = listener;
        initView();
        showTime();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mCount = mData.size();
        // 清除指示器
        dotContainer.removeAllViews();
        dots.clear();
        // 装载指示器
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams5.rightMargin = DisplayUtil.dip2px(getContext(), 6);
        for (int i = 0; i < mCount; i++) {
            ImageView dotView = new ImageView(mContext);
            dotView.setImageResource(R.drawable.indicator_normal);
            dotContainer.addView(dotView, layoutParams5);
            dots.add(dotView);
        }
        // 清除图片
        images.clear();
        // 装载count+2张图片
        for (int i = 0; i <= mCount + 1; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ScaleType.FIT_XY);
            Video video;
            if (i == 0) {// 第一个位置装载最后一个图片
                video = mData.get(mCount - 1);
            } else if (i == mCount + 1) {// 最后一个位置装载第一张图片
                video = mData.get(0);
            } else {
                video = mData.get(i - 1);
                imageView.setOnClickListener(this);// OnItemClick
            }
            // 显示图片
            if (mListener != null) {
                mListener.displayImage(imageView, video.getFace());
            }
            images.add(imageView);
        }
    }

    private void showTime() {
        viewPager.setAdapter(new ViewAdapter());
        viewPager.addOnPageChangeListener(this);
        if (mCount > 0) {// 数据列表不为空
            viewPager.setCurrentItem(1);
            showView(1);
            currentItem = 1;
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    handler.postDelayed(autoPlay, delayTime);
                }
            }.sendEmptyMessage(0);
        }
    }


    /**
     * 显示指示器
     *
     * @param position 位置
     */
    private void showView(int position) {
        for (int i = 0; i < dots.size(); i++) {
            int resId = (i == position - 1) ? R.drawable.indicator_focused : R.drawable.indicator_normal;
            dots.get(i).setImageResource(resId);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        int position = viewPager.getCurrentItem();
        switch (state) {
            case 1:// 正在滑动,只有手动操作才会走此逻辑
                handler.removeCallbacks(autoPlay);
                handler.postDelayed(autoPlay, delayTime);
                break;
            case 2:// 滑动完毕
                if (position == 0) {
                    showView(mCount);
                } else if (position == mCount + 1) {
                    showView(1);
                } else {
                    showView(position);
                }
                break;
            case 0:
                if (position == 0) {// 第一个位置(0)直接跳转到倒数第2个位置
                    viewPager.setCurrentItem(mCount, false);
                } else if (position == mCount + 1) {// 最后一个位置直接跳转到第2个位置
                    viewPager.setCurrentItem(1, false);
                }
                currentItem = viewPager.getCurrentItem();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            int position = viewPager.getCurrentItem() - 1;
            mListener.onBannerClick(position, mData.get(position));
        }
    }

    private class ViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public ImageView instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
            return images.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }

    }

    public interface BannerViewListener {
        void onBannerClick(int position, Video video);

        void displayImage(ImageView imageView, String url);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 取消定时器
        handler.removeCallbacks(autoPlay);
        super.onDetachedFromWindow();
    }
}
