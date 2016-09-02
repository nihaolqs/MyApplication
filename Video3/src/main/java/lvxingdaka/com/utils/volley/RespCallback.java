package lvxingdaka.com.utils.volley;

import com.android.volley.Response;

/**
 * Created by yangking on 16/3/24.
 */
public interface RespCallback<T> extends Response.Listener<T>, Response.ErrorListener {

}