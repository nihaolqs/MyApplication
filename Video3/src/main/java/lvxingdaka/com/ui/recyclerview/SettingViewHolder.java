package lvxingdaka.com.ui.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.video1.play105.R;
import lvxingdaka.com.ui.adapter.BaseRecyclerAdapter;
import lvxingdaka.com.utils.DisplayUtil;

/**
 * Created by KingYang on 2016/4/30.
 * E-Mail: admin@kingyang.cn
 */
public class SettingViewHolder extends BaseViewHolder {
    private View itemView;
    private Context context;
    public ImageView icon;
    public TextView title;
    public TextView value;

    public SettingViewHolder(View itemView, Context context, final BaseRecyclerAdapter.OnItemClickListener listener) {
        super(itemView, listener);
        this.itemView = itemView;
        this.context = context;
        icon = (ImageView) itemView.findViewById(R.id.icon);
        title = (TextView) itemView.findViewById(R.id.title);
        value = (TextView) itemView.findViewById(R.id.value);
    }

    public void setBlank() {
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(itemView.getLayoutParams());
        params.setMargins(0, DisplayUtil.dip2px(context, 20), 0, 0);
        itemView.setLayoutParams(params);
    }


}