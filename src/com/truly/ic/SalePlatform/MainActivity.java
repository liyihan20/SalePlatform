package com.truly.ic.SalePlatform;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.truly.ic.util.MyUtils;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

	private ActionBar actionBar;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		BootstrapButton mButton = (BootstrapButton)findViewById(R.id.button_main_AES);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText mEditText = (BootstrapEditText)findViewById(R.id.text_main_AES);
				EditText mResult=(BootstrapEditText)findViewById(R.id.result_main_AES);
				try {
					String result=MyUtils.AES.encrypt(mEditText.getText().toString());
					mResult.setText(result);
				} catch (Exception e) {					
					e.printStackTrace();
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.exit_app) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
			return true;
		}else if(id==R.id.login){
			startActivity(new Intent(this,LoginActivity.class));
			
		}
		return super.onOptionsItemSelected(item);
	}
}
