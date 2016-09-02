package lvxingdaka.com.ui.view;

import lvxingdaka.com.bean.Comment;
import lvxingdaka.com.bean.Video;

import java.util.List;

/**
 * Created by KingYang on 16/3/25.
 * E-Mail: admin@kingyang.cn
 */
public interface IVideoView {
    void showVideoAndComments(Video videos, List<Comment> comments);
    void showError(String msg);
}
