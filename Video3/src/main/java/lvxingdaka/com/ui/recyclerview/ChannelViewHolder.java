package lvxingdaka.com.ui.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.video1.play105.R;
import lvxingdaka.com.ui.adapter.BaseRecyclerAdapter;

/**
 * Created by KingYang on 2016/4/29.
 * E-Mail: admin@kingyang.cn
 */
public class ChannelViewHolder extends BaseViewHolder {
    public ImageView face;
    public TextView title;
    public TextView update;

    public ChannelViewHolder(View itemView, final BaseRecyclerAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        face = (ImageView) itemView.findViewById(R.id.face);
        title = (TextView) itemView.findViewById(R.id.title);
        update = (TextView) itemView.findViewById(R.id.update);
    }
}