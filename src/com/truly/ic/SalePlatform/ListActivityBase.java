package com.truly.ic.SalePlatform;

import android.app.ListActivity;
import android.os.Bundle;

public class ListActivityBase extends ListActivity {

	public String accountset;
	public String userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SalePlatformApplication app = (SalePlatformApplication) getApplication();
		accountset = app.getAccountset();
		userid = app.getUserid();
		super.onCreate(savedInstanceState);
	}
}
