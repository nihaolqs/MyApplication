package lvxingdaka.com.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lvxingdaka.com.R;
import lvxingdaka.com.ui.activity.MainActivity;

/**
 * Created by KingYang on 16/3/26.
 * E-Mail: admin@kingyang.cn
 */
public class RefreshableFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    protected MainActivity activity;
    protected SwipeRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_refreshandload, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_orange_light);
        refreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        onFragmentCreate();
    }

    @Override
    public void onRefresh() {
    }

    public void onFragmentCreate() {
    }

}
