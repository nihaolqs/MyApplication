package lvxingdaka.com.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import lvxingdaka.com.ui.adapter.BaseRecyclerAdapter;

/**
 * Created by KingYang on 2016/4/29.
 * E-Mail: admin@kingyang.cn
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView, final BaseRecyclerAdapter.OnItemClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, getPosition());
                }
            }
        });
    }


}
