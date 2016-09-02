package lvxingdaka.com.ui.view;

import lvxingdaka.com.bean.Video;

import java.util.List;

/**
 * Created by KingYang on 16/3/26.
 * E-Mail: admin@kingyang.cn
 */
public interface ILiveView {

    void showLives(List<Video> lives);

    void showError(String msg);
}
