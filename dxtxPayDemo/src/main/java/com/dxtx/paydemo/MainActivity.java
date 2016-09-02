package com.dxtx.paydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import dxtx.dj.pay.PayuPlugin;
import dxtx.dj.pay.enums.PayType;
import dxtx.dj.pay.iter.PayBack;
import dxtx.dj.pay.model.OrderModel;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


	}

	public void paywx(View view) {
		doPay(PayType.PAY_WX);
	}

	public void payzfb(View view) {
		doPay(PayType.PAY_ZFB);
	}

	private void doPay(PayType payType) {
		OrderModel model = getOrderModel(payType);
		PayuPlugin.getPayPlugin().pay(MainActivity.this, DxtxPay.APP_KEY, model, new PayBack() {

			@Override
			public void success() {
				// 标示支付成功
				Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void failure(int errorCode, String errorMsg) {
				if (-1 == errorCode) {
					// 用户行为取消以及相关动作（微信和支付宝反馈失败信息）
				} else {
					// 通讯以及其他异常，请查看errorCode以及对应errorMsg
				}
				Toast.makeText(MainActivity.this, "支付失败 " + errorCode + " " + errorMsg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private OrderModel getOrderModel(PayType payType) {
		String orderId = "" + System.currentTimeMillis() + (int) (Math.random() * 1000);// 用户自定义生成的订单号
		String address = DxtxPay.address_url;// 回调地址(服务器同步通知地址) 可空
		int goods_id = DxtxPay.goods_id;// 商品id
		double price = 1.00;// 价格 单位元 类型double 不得小于1元
		String privateinfo = "";// 商户自定义传递信息 可空
		String goods_name = "";// 商品名称 可空
		return new OrderModel(orderId, address, payType, goods_id, goods_name, price, privateinfo);
	}

}
