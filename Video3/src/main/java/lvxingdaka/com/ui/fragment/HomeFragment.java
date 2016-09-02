package lvxingdaka.com.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import lvxingdaka.com.R;
import lvxingdaka.com.bean.Video;
import lvxingdaka.com.presenter.HomePresenter;
import lvxingdaka.com.ui.activity.VideoActivity;
import lvxingdaka.com.ui.adapter.BaseRecyclerAdapter;
import lvxingdaka.com.ui.adapter.VideoHeaderAdapter;
import lvxingdaka.com.ui.view.IHomeView;
import lvxingdaka.com.ui.widget.BannerView;
import lvxingdaka.com.utils.ToastUtil;

import java.util.List;


public class HomeFragment extends RefreshableFragment implements BannerView.BannerViewListener, IHomeView, BaseRecyclerAdapter.OnItemClickListener {
    private BannerView bannerView;
    private VideoHeaderAdapter adapter;
    private HomePresenter mPresenter;

    @Override
    public void onFragmentCreate() {
        bannerView = new BannerView(activity);
        adapter = new VideoHeaderAdapter(activity, bannerView);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        final GridLayoutManager manager = new GridLayoutManager(activity, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isHeader(position) ? manager.getSpanCount() : 1;
            }
        });
        recyclerView.setLayoutManager(manager);
        mPresenter = new HomePresenter(this, activity.getQueue());
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                mPresenter.loadHomeData();
            }
        });
    }

    @Override
    public void showHomeData(List<Video> banner, List<Video> videos) {
        refreshLayout.setRefreshing(false);
        bannerView.setData(banner, this);
        adapter.refreshData(videos);
    }

    @Override
    public void showError(String msg) {
        refreshLayout.setRefreshing(false);
        ToastUtil.show(activity, msg);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadHomeData();
    }


    @Override
    public void onBannerClick(int position, Video video) {
        startActivity(VideoActivity.createIntent(activity, video.getId()));
    }

    @Override
    public void displayImage(ImageView imageView, String url) {
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.mipmap.video_loading, R.mipmap.video_loading);
        activity.getImageLoader().get(url, imageListener);
    }

    @Override
    public void onItemClick(View v, int position) {
        startActivity(VideoActivity.createIntent(activity, adapter.getItem(position).getId()));
    }
}
