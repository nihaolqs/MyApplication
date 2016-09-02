package lvxingdaka.com.utils;

import android.content.Context;

/**
 * Created by KingYang on 16/3/12.
 * E-Mail: admin@kingyang.cn
 */
public class DisplayUtil {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
