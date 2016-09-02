package lvxingdaka.com.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.switfpass.pay.MainApplication;
import com.switfpass.pay.activity.PayPlugin;
import com.switfpass.pay.bean.RequestMsg;
import com.switfpass.pay.service.GetPrepayIdResult;
import com.switfpass.pay.utils.MD5;
import com.switfpass.pay.utils.SignUtils;
import com.switfpass.pay.utils.Util;
import com.switfpass.pay.utils.XmlUtils;

import lvxingdaka.com.R;
import lvxingdaka.com.app.Consts;
import lvxingdaka.com.utils.AppUtil;
import lvxingdaka.com.utils.SPUtil;
import lvxingdaka.com.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PayActivityOld extends AppCompatActivity implements View.OnClickListener {
    private TextView okBtn;
    private String name;
    private int money;
    private RadioButton wxBtn, qqBtn;
    private int vip;// 会员类型

    public static void createInstance(Context context, int pay) {
        Intent intent = new Intent(context, PayActivityOld.class);
        intent.putExtra("pay", pay);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        setFinishOnTouchOutside(false);// 点击外部不关闭
        int pay = getIntent().getIntExtra("pay", 1);
        TextView nameView = (TextView) findViewById(R.id.name);
        TextView moneyView = (TextView) findViewById(R.id.money);
        if (pay == 1) {
            vip = 1;
            name = "月费会员";
            money = 38;
        } else if (pay == 2) {
            vip = 2;
            name = "永久会员";
            money = 68;
        } else {
            vip = 2;
            name = "特价永久会员";
            money = 29;
        }
        nameView.setText(name);
        moneyView.setText(money + "元");
        okBtn = (TextView) findViewById(R.id.okBtn);
        okBtn.setOnClickListener(this);
        wxBtn = (RadioButton) findViewById(R.id.wxBtn);
        qqBtn = (RadioButton) findViewById(R.id.qqBtn);
        wxBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    qqBtn.setChecked(false);
            }
        });
        qqBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    wxBtn.setChecked(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == okBtn) {
            String type = wxBtn.isChecked() ? MainApplication.PAY_WX_WAP : MainApplication.PAY_QQ_WAP;
            new DoPay(this, type).execute();
        }
    }

    private class DoPay extends AsyncTask<Void, Void, Map<String, String>> {
        private Activity activity;
        private String type;//支付方式
        private Map<String, String> params = new HashMap<>();

        public DoPay(Activity activity, String type) {
            this.activity = activity;
            this.type = type;
        }

        @Override
        protected Map<String, String> doInBackground(Void... params) {
            byte[] buf = Util.httpPost(Consts.WFT.CREATE_ORDER, getEntity());
            if (buf != null) {
                String response = new String(buf);
                GetPrepayIdResult result = new GetPrepayIdResult();
                result.parseFrom(response);
                return XmlUtils.parse(response);
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (result == null) {
                return;
            }

            if (result.get("status").equals("0")) {
                RequestMsg msg = new RequestMsg();
                msg.setMoney(Integer.valueOf(params.get("total_fee")));
                msg.setTokenId(result.get("token_id"));
                msg.setOutTradeNo(params.get("out_trade_no"));
                msg.setTradeType(type);
                PayPlugin.unifiedH5Pay(activity, msg);
            } else {
                ToastUtil.show(activity, "创建订单失败," + result.get("message"));
            }
        }

        private String getEntity() {
            String channel = AppUtil.getMetaData(activity, Consts.APP.CHANNEL_NAME);
            params.put("body", name); // 商品名称
            params.put("service", "unified.trade.pay"); // 支付类型
            params.put("version", "1.0"); // 版本
            params.put("mch_id", Consts.WFT.MCH_ID); // 威富通商户号
            params.put("notify_url", Consts.URL.NOTIFY_WFT); // 后台通知url
            params.put("nonce_str", new Random().nextInt(999999) + ""); // 随机数
            params.put("out_trade_no", channel + "-" + System.currentTimeMillis()); //订单号
            params.put("mch_create_ip", "127.0.0.1"); // 机器ip地址
            params.put("total_fee", String.valueOf(money * 100)); // 总金额
            params.put("op_shop_id", channel);
            // 手Q反扫这个设备号必须要传1ff9fe53f66189a6a3f91796beae39fe
            params.put("device_info", SPUtil.getString(PayActivityOld.this, Consts.SP.UID));
            params.put("limit_credit_pay", "0");// 1限制使用信用卡,0不限制
            params.put("sign", createSign(params, Consts.WFT.SIGN_KEY)); // sign签名
            return XmlUtils.toXml(params);
        }

        private String createSign(Map<String, String> params, String signKey) {
            StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
            SignUtils.buildPayParams(buf, params, false);
            buf.append("&key=").append(signKey);
            String preStr = buf.toString();
            String sign;
            // 获得签名验证结果
            try {
                sign = MD5.md5s(preStr).toUpperCase();
            } catch (Exception e) {
                sign = MD5.md5s(preStr).toUpperCase();
            }
            return sign;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String result = data.getExtras().getString("resultCode");
        if ("SUCCESS".equalsIgnoreCase(result)) {
            // 支付成功
            SPUtil.putInt(this, Consts.SP.VIP, vip);
            ToastUtil.show(this, "开通成功");
            finish();
        } else {
            ToastUtil.show(this, "取消支付");
        }
    }
}
