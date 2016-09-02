package lvxingdaka.com.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.video1.play105.R;
import lvxingdaka.com.bean.Comment;
import lvxingdaka.com.ui.activity.BaseActivity;
import lvxingdaka.com.ui.recyclerview.CommentViewHolder;

/**
 * Created by KingYang on 16/3/25.
 * E-Mail: admin@kingyang.cn
 */
public class CommentHeaderAdapter extends BaseRecyclerAdapter<Comment, CommentViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 2;
    private View header;

    public CommentHeaderAdapter(BaseActivity activity, View header) {
        super(activity);
        if (header == null) {
            throw new IllegalArgumentException("Header view can not be null.");
        }
        this.header = header;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new CommentViewHolder(header, listener);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        if (!isHeader(position)) {
            Comment comment = getItem(position);
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.avatar, R.mipmap.ic_avatar_default, R.mipmap.ic_avatar_default);
            activity.getImageLoader().get(comment.getAvatar(), listener);
            holder.username.setText(comment.getUsername());
            holder.replytime.setText(comment.getReplytime());
            holder.content.setText(comment.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public Comment getItem(int position) {
        return super.getItem(position - 1);
    }
}
