package lvxingdaka.com.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import lvxingdaka.com.bean.Channel;
import lvxingdaka.com.presenter.ChannelPresenter;
import lvxingdaka.com.ui.activity.ListActivity;
import lvxingdaka.com.ui.adapter.BaseRecyclerAdapter;
import lvxingdaka.com.ui.adapter.ChannelAdapter;
import lvxingdaka.com.ui.view.IChannelView;
import lvxingdaka.com.utils.ToastUtil;

import java.util.List;

public class ChannelFragment extends RefreshableFragment implements IChannelView, BaseRecyclerAdapter.OnItemClickListener {
    private ChannelAdapter adapter;
    private ChannelPresenter mPresenter;

    @Override
    public void onFragmentCreate() {
        adapter = new ChannelAdapter(activity);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        mPresenter = new ChannelPresenter(this, activity.getQueue());
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                mPresenter.loadList();
            }
        });
    }

    @Override
    public void onRefresh() {
        mPresenter.loadList();

    }

    @Override
    public void showList(List<Channel> channels) {
        refreshLayout.setRefreshing(false);
        adapter.refreshData(channels);
    }

    @Override
    public void showError(String msg) {
        refreshLayout.setRefreshing(false);
        ToastUtil.show(activity, msg);
    }

    @Override
    public void onItemClick(View v, int position) {
        startActivity(ListActivity.createIntent(activity, adapter.getItem(position).getId(), adapter.getItem(position).getTitle()));
    }
}
