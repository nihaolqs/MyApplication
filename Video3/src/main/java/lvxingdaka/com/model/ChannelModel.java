package lvxingdaka.com.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import lvxingdaka.com.app.Consts;
import lvxingdaka.com.utils.volley.RespCallback;

/**
 * Created by KingYang on 16/4/15.
 * E-Mail: admin@kingyang.cn
 */
public class ChannelModel {
    private RequestQueue queue;

    public ChannelModel(RequestQueue queue) {
        this.queue = queue;
    }

    public void getList(RespCallback<String> callback) {
        StringRequest request = new StringRequest(Request.Method.GET, Consts.URL.CHANNEL_LIST, callback, callback);
        queue.add(request);
    }

}
