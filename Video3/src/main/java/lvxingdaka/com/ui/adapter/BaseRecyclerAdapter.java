package lvxingdaka.com.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import lvxingdaka.com.ui.activity.BaseActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by KingYang on 16/4/15.
 * E-Mail: admin@kingyang.cn
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> list = new LinkedList<>();
    protected BaseActivity activity;
    protected OnItemClickListener listener;

    public BaseRecyclerAdapter(BaseActivity activity) {
        this.activity = activity;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refreshData(List<T> data) {
        if (!data.isEmpty()) {
            list.clear();
            list.addAll(data);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

}
