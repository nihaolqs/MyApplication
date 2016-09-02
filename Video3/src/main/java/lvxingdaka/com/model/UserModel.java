package lvxingdaka.com.model;

import android.content.Context;

import lvxingdaka.com.app.Consts;
import lvxingdaka.com.bean.Setting;
import lvxingdaka.com.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KingYang on 16/3/26.
 * E-Mail: admin@kingyang.cn
 */
public class UserModel {
    public List<Setting> getUserInfo(Context context) {
        List<Setting> settings = new ArrayList<>();
        Setting account = new Setting();
        account.setBlank(true);
        account.setIcon(android.R.drawable.ic_menu_mylocation);
        account.setTitle("用户账号");
        String uid = SPUtil.getString(context, Consts.SP.UID);
        account.setValue(uid);
        Setting vipSet = new Setting();
        vipSet.setIcon(android.R.drawable.ic_menu_mylocation);
        vipSet.setTitle("会员服务");
        int vip = SPUtil.getInt(context, Consts.SP.VIP);
        String vipName;
        if (vip == 1) {
            vipName = "月费会员";
        } else if (vip == 2) {
            vipName = "永久会员";
        } else if (vip == 3) {
            vipName = "体验会员";
        } else {
            vipName = "未开通";
        }
        vipSet.setValue(vipName);
        Setting code = new Setting();
        code.setIcon(android.R.drawable.ic_menu_info_details);
        code.setTitle("自助激活");
        Setting contact = new Setting();
        contact.setBlank(true);
        contact.setIcon(android.R.drawable.ic_menu_call);
        contact.setTitle("联系客服");
        Setting protocol = new Setting();
        protocol.setIcon(android.R.drawable.ic_menu_agenda);
        protocol.setTitle("用户协议");
        Setting update = new Setting();
        update.setIcon(android.R.drawable.ic_menu_rotate);
        update.setTitle("检查更新");
        settings.add(account);
        settings.add(vipSet);
        settings.add(code);
        settings.add(contact);
        settings.add(protocol);
        settings.add(update);
        return settings;
    }
}
