package lvxingdaka.com.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import lvxingdaka.com.app.Consts;
import lvxingdaka.com.utils.volley.RespCallback;

/**
 * Created by KingYang on 16/3/26.
 * E-Mail: admin@kingyang.cn
 */
public class LiveModel {
    private RequestQueue mQueue;

    public LiveModel(RequestQueue mQueue) {
        this.mQueue = mQueue;
    }

    public void getLives(RespCallback<String> callback) {
        StringRequest request = new StringRequest(Request.Method.GET, String.format(Consts.URL.VIDEO_BY_CHANNEL, 100), callback, callback);
        mQueue.add(request);
    }
}
