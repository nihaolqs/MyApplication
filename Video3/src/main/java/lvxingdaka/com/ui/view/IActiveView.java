package lvxingdaka.com.ui.view;

/**
 * Created by KingYang on 2016/4/30.
 * E-Mail: admin@kingyang.cn
 */
public interface IActiveView {
    void onActiving();
    void showResult(String vip);
    void showError(String msg);
}
