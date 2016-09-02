package lvxingdaka.com.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.video1.play105.R;
import lvxingdaka.com.bean.Video;
import lvxingdaka.com.ui.activity.BaseActivity;
import lvxingdaka.com.ui.recyclerview.VideoViewHolder;

/**
 * Created by KingYang on 16/3/15.
 * E-Mail: admin@kingyang.cn
 */
public class VideoAdapter extends BaseRecyclerAdapter<Video, VideoViewHolder> {

    public VideoAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = getItem(position);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.image, R.mipmap.video_loading, R.mipmap.video_loading);
        activity.getImageLoader().get(video.getFace(), listener);
        if (video.getFlag() == 1) {
            holder.flag.setImageResource(R.mipmap.video_free);
        }else{
            holder.flag.setImageResource(0);
        }
        holder.title.setText(video.getTitle());
    }
}

