package lvxingdaka.com.bean;

/**
 * Created by KingYang on 16/3/25.
 * E-Mail: admin@kingyang.cn
 */
public class Comment {
    private String username;
    private String replytime;
    private String content;
    private String avatar;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplytime() {
        return replytime;
    }

    public void setReplytime(String replytime) {
        this.replytime = replytime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
