package lvxingdaka.com.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import lvxingdaka.com.app.Consts;
import lvxingdaka.com.utils.volley.RespCallback;

/**
 * Created by yangking on 16/3/25.
 */
public class CommentModel {
    private RequestQueue queue;

    public CommentModel(RequestQueue mQueue) {
        this.queue = mQueue;
    }

    public void getComments(int vid, RespCallback<String> callback) {
        StringRequest request = new StringRequest(Request.Method.GET, String.format(Consts.URL.COMMENT_BY_VID, vid), callback, callback);
        queue.add(request);
    }
}
