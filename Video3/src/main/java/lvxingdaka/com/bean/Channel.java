package lvxingdaka.com.bean;

/**
 * Created by KingYang on 16/4/15.
 * E-Mail: admin@kingyang.cn
 */
public class Channel {
    private int id;// 频道ID
    private String title;// 标题
    private String face;// 封面
    private int update;// 今日更新数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
}
