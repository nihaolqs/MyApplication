package lvxingdaka.com.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import lvxingdaka.com.R;
import lvxingdaka.com.bean.Video;
import lvxingdaka.com.ui.activity.BaseActivity;
import lvxingdaka.com.ui.recyclerview.VideoViewHolder;

/**
 * Created by KingYang on 16/4/15.
 * E-Mail: admin@kingyang.cn
 */
public class VideoHeaderAdapter extends BaseRecyclerAdapter<Video, VideoViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 2;
    private BaseActivity activity;
    private View header;

    public VideoHeaderAdapter(BaseActivity activity, View header) {
        super(activity);
        if (header == null) {
            throw new IllegalArgumentException("Header view can not be null.");
        }
        this.activity = activity;
        this.header = header;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new VideoViewHolder(header, listener);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        if (!isHeader(position)) {
            Video video = getItem(position);
            ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(holder.image, R.mipmap.video_loading, R.mipmap.video_loading);
            activity.getImageLoader().get(video.getFace(), imageListener);
            if (video.getFlag() == 1) {
                holder.flag.setImageResource(R.mipmap.video_free);
            }else{
                holder.flag.setImageResource(0);
            }
            holder.title.setText(video.getTitle());
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
    public Video getItem(int position) {
        return super.getItem(position - 1);
    }

}

