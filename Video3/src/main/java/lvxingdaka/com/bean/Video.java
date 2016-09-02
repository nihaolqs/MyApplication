package lvxingdaka.com.bean;

/**
 * Created by KingYang on 16/3/10.
 * E-Mail: admin@kingyang.cn
 */
public class Video {
    private int id;// 视频ID
    private int cid;// 频道ID
    private String url;// 资源地址
    private String face;// 封面图片地址
    private String title;// 标题
    private String tags;// 标签
    private byte flag;// 类型标识
    private int like;// 喜欢数
    private int share;// 分享数
    private int comment;// 评论数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
}
