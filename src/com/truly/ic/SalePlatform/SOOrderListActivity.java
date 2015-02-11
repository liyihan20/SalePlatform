package com.truly.ic.SalePlatform;

import java.util.List;
import java.util.TreeMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.truly.ic.models.SimpleSOModel;
import com.truly.ic.util.MyUtils;
import com.truly.ic.util.SoapService;

public class SOOrderListActivity extends ListActivityBase {

	public final static String ORDERID="SOOrderListActivity.order_id";
	
	private String fromDate, toDate, orderNo;	
	private Handler mHandler;
	private String[] segments = new String[] { "orderNo", "orderDate", "salerName", "sysNo", "agency", "model","productName", "qty" };
	private int[] toViewFields = new int[] { R.id.order_no_item, R.id.order_date_item, R.id.order_saler_item, R.id.order_sysNo_item, R.id.order_agency_item, R.id.order_model_item, R.id.order_productName_item, R.id.order_qty_item };
	private List<SimpleSOModel> list;
	private final Integer MAXLINES = 30;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show();
		
		final SalePlatformApplication app=(SalePlatformApplication)getApplication();
		Log.v("userid",app.getUserid().toString());
		Log.v("accountset",app.getAccountset());
		
		fromDate = getIntent().getStringExtra(MainActivity.FROMDATE);
		toDate = getIntent().getStringExtra(MainActivity.TODATE);
		orderNo = getIntent().getStringExtra(MainActivity.ORDERNO);

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				list = JSON.parseArray(msg.obj.toString(), SimpleSOModel.class);
				if (MAXLINES.equals(list.size())) {
					Toast.makeText(SOOrderListActivity.this, "自动加载符合条件且最新的前30条记录", Toast.LENGTH_LONG).show();
					;
				}
				if (!list.isEmpty()) {
					SimpleAdapter mSimpleAdapter = new SimpleAdapter(SOOrderListActivity.this, MyUtils.ConvertToMapList(list, segments), R.layout.item_simple_order, segments, toViewFields);
					setListAdapter(mSimpleAdapter);
				} else {
					Toast.makeText(SOOrderListActivity.this, "找不到符合条件的订单", Toast.LENGTH_LONG).show();
					SOOrderListActivity.this.finish();
				}
				super.handleMessage(msg);
			}
		};

		new GetDataThread().start();
	}

	private class GetDataThread extends Thread {

		@Override
		public void run() {
			SoapService soap = new SoapService(accountset,userid);
			TreeMap<String, String> filter = new TreeMap<String, String>();
			filter.put("billNo", orderNo);
			filter.put("fromDate", fromDate);
			filter.put("toDate", toDate);
			try {
				String result = soap.getSoapStringResult("GetOrderList", filter);
				Message ms = mHandler.obtainMessage();
				ms.obj = result;
				mHandler.sendMessage(ms);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent=new Intent(this,SOOrderDetailActivity.class);
		intent.putExtra(ORDERID, list.get(position).getId().toString());
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
}
