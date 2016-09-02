package lvxingdaka.com.ui.view;

import lvxingdaka.com.bean.Video;

import java.util.List;

/**
 * Created by KingYang on 2016/4/29.
 * E-Mail: admin@kingyang.cn
 */
public interface IListView {
    void showVideos(List<Video> videos);
    void showError(String msg);
}
