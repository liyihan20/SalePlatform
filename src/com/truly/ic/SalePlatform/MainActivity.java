package com.truly.ic.SalePlatform;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.truly.ic.util.MyUtils;

import android.R.integer;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends ActivityBase {

	private static final String TAG = "MainActivity";
	private Integer fromYear, fromMonth, fromDay;
	private Integer toYear, toMonth, toDay;
	public final static String FROMDATE="MainActivity.fromDate";
	public final static String TODATE="MainActivity.toDate";
	public final static String ORDERNO="MainActivity.orderNo";
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		// final BootstrapButton mButton = (BootstrapButton)
		// findViewById(R.id.but_from_date_main);
		final EditText fromDateText = (BootstrapEditText) findViewById(R.id.text_from_date_main);
		final EditText toDateText = (BootstrapEditText) findViewById(R.id.text_to_date_main);
		final EditText billNoText = (BootstrapEditText) findViewById(R.id.text_bill_no_main);
		final BootstrapButton SearchBut = (BootstrapButton) findViewById(R.id.but_search_main_main);

				
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		toYear = fromYear = cal.get(Calendar.YEAR);
		toMonth = fromMonth = cal.get(Calendar.MONTH);
		toDay = fromDay = cal.get(Calendar.DAY_OF_MONTH);

		fromDateText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						fromDateText.setText(new StringBuilder().append(year).append("-").append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-").append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth));
						fromYear = year;
						fromMonth = monthOfYear;
						fromDay = dayOfMonth;
					}
				}, fromYear, fromMonth, fromDay);
				dpd.show();
			}
		});

		toDateText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						toDateText.setText(new StringBuilder().append(year).append("-").append((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1)).append("-").append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth));
						toYear = year;
						toMonth = monthOfYear;
						toDay = dayOfMonth;
					}
				}, toYear, toMonth, toDay);
				dpd.show();
			}
		});

		SearchBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String fromDate = fromDateText.getText().toString();
				String toDate = toDateText.getText().toString();
				String billNo = billNoText.getText().toString();

				if (null == fromDate || "".equals(fromDate)) {
					Toast.makeText(MainActivity.this, getString(R.string.fromDateNotEmpty), Toast.LENGTH_LONG).show();
					return;
				}
				if (null == toDate || "".equals(toDate)) {
					Toast.makeText(MainActivity.this, getString(R.string.toDateNotEmpty), Toast.LENGTH_LONG).show();
					return;
				}
				if (toYear * 10000 + toMonth * 100 + toDay < fromYear * 10000 + fromMonth * 100 + fromDay) {
					Toast.makeText(MainActivity.this, getString(R.string.dateZoneError), Toast.LENGTH_LONG).show();
					return;
				}
				
				Intent intent=new Intent(MainActivity.this,SOOrderListActivity.class);
				intent.putExtra(FROMDATE, fromDate);
				intent.putExtra(TODATE, toDate);
				intent.putExtra(ORDERNO, billNo);
				
				startActivity(intent);
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
		}
		return super.onOptionsItemSelected(item);
	}
}
