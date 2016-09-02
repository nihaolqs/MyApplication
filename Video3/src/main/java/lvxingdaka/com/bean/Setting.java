package lvxingdaka.com.bean;

/**
 * Created by KingYang on 2016/4/24.
 * E-Mail: admin@kingyang.cn
 */
public class Setting {
    private int icon;
    private String title;
    private String value;
    private boolean blank;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isBlank() {
        return blank;
    }

    public void setBlank(boolean blank) {
        this.blank = blank;
    }
}
