package lvxingdaka.com.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.video1.play105.R;
import lvxingdaka.com.app.Consts;
import lvxingdaka.com.bean.Comment;
import lvxingdaka.com.bean.Video;
import lvxingdaka.com.presenter.VideoPresenter;
import lvxingdaka.com.ui.adapter.CommentHeaderAdapter;
import lvxingdaka.com.ui.view.IVideoView;
import com.video1.utils.SPUtil;
import lvxingdaka.com.utils.ToastUtil;
import lvxingdaka.com.utils.volley.RespCallback;

import java.util.List;

public class VideoActivity extends BaseActivity implements IVideoView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String VIDEO_ID = "id";
    private int vid;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private CommentHeaderAdapter adapter;
    private ImageView mFace;
    private ImageView mPlay;
    private TextView mLike, mShare, mComment;
    private VideoPresenter mPresenter;
    private Video video;

    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(VIDEO_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setUpCommonBackTooblBar(R.id.toolbar, getTitle());
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_orange_light);
        refreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL, R.drawable.divider_trans));
        View header = LayoutInflater.from(this).inflate(R.layout.video_header, recyclerView, false);
        mFace = (ImageView) header.findViewById(R.id.face);
        mPlay = (ImageView) header.findViewById(R.id.play);
        mLike = (TextView) header.findViewById(R.id.op_like);
        mShare = (TextView) header.findViewById(R.id.op_share);
        mComment = (TextView) header.findViewById(R.id.op_comment);
        adapter = new CommentHeaderAdapter(this, header);
        recyclerView.setAdapter(adapter);
        vid = getIntent().getIntExtra(VIDEO_ID, 0);
        mPresenter = new VideoPresenter(this, queue);
        mPresenter.loadVideoAndComments(vid);
    }


    @Override
    public void showVideoAndComments(Video video, List<Comment> comments) {
        refreshLayout.setRefreshing(false);
        this.video = video;
        setToolBarTitle(R.id.toolbar, video.getTitle());
        mFace.setOnClickListener(this);//  加载完成之后才能点击
        if (video.getCid() != 1)// 不是免费视频才显示播放图片
            mPlay.setVisibility(View.VISIBLE);
        mLike.setText(String.valueOf(video.getLike()));
        mShare.setText(String.valueOf(video.getShare()));
        mComment.setText(String.valueOf(video.getComment()));
        RespCallback<Bitmap> callback = new RespCallback<Bitmap>() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

            @Override
            public void onResponse(Bitmap bitmap) {
                mFace.setImageBitmap(bitmap);
            }
        };
        ImageRequest request = new ImageRequest(video.getFace(), callback, 800, 1280, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, callback);
        queue.add(request);
        adapter.refreshData(comments);
    }


    @Override
    public void onClick(View v) {
        if (v == mFace) {
            boolean isFree = video.getFlag() == 1;
            if (isFree || SPUtil.getInt(this, Consts.SP.VIP) > 0) {// 如果为免费视频或者已付费，即可观看
                startActivity(VideoPlayActivity.createIntent(this, video.getUrl(), false, isFree));
            } else {
                pay1Dialog.show();
            }
        }
    }

    @Override
    public void showError(String msg) {
        refreshLayout.setRefreshing(false);
        ToastUtil.show(this, msg);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadVideoAndComments(vid);
    }
}
