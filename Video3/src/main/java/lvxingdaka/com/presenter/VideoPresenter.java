package lvxingdaka.com.presenter;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lvxingdaka.com.bean.Comment;
import lvxingdaka.com.bean.Video;
import lvxingdaka.com.model.VideoModel;
import lvxingdaka.com.ui.view.IVideoView;
import lvxingdaka.com.utils.volley.RespCallback;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by KingYang on 16/3/25.
 * E-Mail: admin@kingyang.cn
 */
public class VideoPresenter {
    private IVideoView view;
    private VideoModel videoModel;
    private Gson gson;

    public VideoPresenter(IVideoView view, RequestQueue queue) {
        this.videoModel = new VideoModel(queue);
        this.view = view;
        gson = new Gson();
    }

    public void loadVideoAndComments(int vid) {
        videoModel.getVideoInfo(vid, new RespCallback<String>() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                view.showError("连接服务器失败");
            }

            @Override
            public void onResponse(String s) {

//                Log.e("Json数据",s);  //打印Json 数据返回
                try {
                    JsonParser parser = new JsonParser();
                    JsonObject json = parser.parse(s).getAsJsonObject();
                    Type type = new TypeToken<List<Comment>>() {
                    }.getType();
                    Video video = gson.fromJson(json.get("video"), Video.class);
                    List<Comment> comments = gson.fromJson(json.get("comments"), type);
                    view.showVideoAndComments(video, comments);
                } catch (Exception e) {
                    view.showError("解析数据出错");
                }
            }
        });
    }

}
