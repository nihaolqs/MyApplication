package lvxingdaka.com.app;

/**
 * Created by KingYang on 16/4/14.
 * E-Mail: admin@kingyang.cn
 */
public class Consts {
    // APP常量
    public static class APP {
        public final static String CONTACT_QQ = "206191992";// 客户QQ
        public final static String CHANNEL_NAME = "UMENG_CHANNEL";
    }

    // 服务器API接口地址
    public static class URL {
        private final static String BASE_URL = "http://app.97aita.com/Api/";// 服务器API接口
        public final static String NOTIFY_WFT = BASE_URL + "Order/wft";
        public final static String VIDEO_HOME = BASE_URL + "Video/getHome";
        public final static String CHANNEL_LIST = BASE_URL + "Channel/getList";
        public final static String VIDEO_BY_CHANNEL = BASE_URL + "Video/getByChannel/cid/%d";
        public final static String VIDEO_BY_ID = BASE_URL + "Video/getInfo/id/%d";
        public final static String COMMENT_BY_VID = BASE_URL + "Comment/getByVid/vid/%d";
        public final static String ACTIVE_VIP = BASE_URL + "Order/active/code/%s";
        public final static String ORDER_LOG = BASE_URL + "Order/log";
    }

    // SharedPreferences
    public static class SP {
        public final static String VIP = "vip";
        public final static String UID = "uid";
        public final static String LOGED = "loged";
    }

    // 威富通支付
    public static class WFT {
        // 支付回调接口
        public final static String CREATE_ORDER = "https://paya.swiftpass.cn/pay/gateway";
        public final static String MCH_ID = "xxx";//威富通商户号
        public final static String SIGN_KEY = "xxx";//威富通密钥
    }

}
