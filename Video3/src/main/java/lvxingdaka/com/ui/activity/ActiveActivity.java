package lvxingdaka.com.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import lvxingdaka.com.R;
import lvxingdaka.com.app.Consts;
import lvxingdaka.com.presenter.ActivePresenter;
import lvxingdaka.com.ui.view.IActiveView;
import lvxingdaka.com.utils.SPUtil;
import lvxingdaka.com.utils.ToastUtil;

public class ActiveActivity extends BaseActivity implements View.OnClickListener, IActiveView {
    private TextView activeBtn;
    private EditText codeEdt;
    private ProgressDialog dialog;
    private ActivePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        setUpCommonBackTooblBar(R.id.toolbar, "自助激活");
        codeEdt = (EditText) findViewById(R.id.codeEdt);
        //codeEdt.setText(SPUtil.getString(this, Consts.SP.UID));
        activeBtn = (TextView) findViewById(R.id.activeBtn);
        activeBtn.setOnClickListener(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在激活");
        presenter = new ActivePresenter(this, queue);
    }

    @Override
    public void onClick(View v) {
        if (v == activeBtn) {
            String code = codeEdt.getText().toString();
            if (TextUtils.isEmpty(code)) {
                ToastUtil.show(this, "请输入用户ID/订单号");
                return;
            }
            presenter.activeVip(code);
        }
    }

    @Override
    public void onActiving() {
        dialog.show();
    }

    @Override
    public void showResult(String vip) {
        dialog.dismiss();
        if ("1".equals(vip)) {
            SPUtil.putInt(this, Consts.SP.VIP, Integer.valueOf(vip));
            ToastUtil.show(this, "成功激活月费会员");
            finish();
        } else if ("2".equals(vip)) {
            SPUtil.putInt(this, Consts.SP.VIP, Integer.valueOf(vip));
            ToastUtil.show(this, "成功激活永久会员");
            finish();
        } else {
            ToastUtil.show(this, "该账号未支付");
        }
    }

    @Override
    public void showError(String msg) {
        dialog.dismiss();
        ToastUtil.show(this, msg);
    }
}
