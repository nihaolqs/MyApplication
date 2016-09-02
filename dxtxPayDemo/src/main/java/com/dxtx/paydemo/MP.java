package com.dxtx.paydemo;

import android.app.Application;
import dxtx.dj.pay.PayuPlugin;

public class MP extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		PayuPlugin.getPayPlugin().init(this, DxtxPay.APP_KEY);
	}
}
