package com.truly.ic.SalePlatform;

import java.util.List;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.truly.ic.models.SOOrderModel;
import com.truly.ic.util.MyUtils;
import com.truly.ic.util.SoapService;

import android.R.integer;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SOOrderDetailActivity extends ActivityBase {

	public final static String ORDERDETAIL = "SOOrderDetailActivity.orderDetail";
	private String orderId;
	private Handler mHandler;
	private List<SOOrderModel> sos;
	private SOOrderModel so;
	private String[] productSegs = new String[] { "product_name", "product_model", "qty", "deal_price", "cost", "delivery_date" };

	private int[] toViews = new int[] { R.id.product_name, R.id.product_model, R.id.product_qty, R.id.product_deal_price, R.id.product_cost, R.id.product_delivery_date, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soorder_detail);

		orderId = getIntent().getStringExtra(SOOrderListActivity.ORDERID);
		// orderId = "25565";

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				sos = JSON.parseArray(msg.obj.toString(), SOOrderModel.class);
				so = sos.get(0);
				setFields();
				super.handleMessage(msg);
			}

		};

		new GetDataThread().start();
	}

	private class GetDataThread extends Thread {

		@Override
		public void run() {
			SoapService soap = new SoapService(accountset, userid);
			// SoapService soap = new SoapService("op", "1");
			TreeMap<String, String> filter = new TreeMap<String, String>();
			filter.put("orderId", orderId);
			try {
				String result = soap.getSoapStringResult("GetOrderDetail", filter);
				Message ms = mHandler.obtainMessage();
				ms.obj = result;
				mHandler.sendMessage(ms);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void setFields() {
		// 隐藏进度条，显示列表
		ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.orderDetail_progressbar);
		ScrollView mScrollView = (ScrollView) findViewById(R.id.orderDetails_scrollView);
		mProgressBar.setVisibility(View.GONE);
		mScrollView.setVisibility(View.VISIBLE);

		// 设置导航条在顶端
		TextView tvOrderNo = ((TextView) findViewById(R.id.orderDetail_order_no));
		tvOrderNo.setText(so.getOrder_no());
		tvOrderNo.setFocusable(true);
		tvOrderNo.setFocusableInTouchMode(true);
		tvOrderNo.requestFocus();

		// 设置每个字段的值
		((TextView) findViewById(R.id.orderDetail_order_no)).setText(so.getOrder_no());
		((TextView) findViewById(R.id.orderDetail_sys_no)).setText(so.getSys_no());
		((TextView) findViewById(R.id.orderDetail_order_date)).setText(so.getOrder_date());
		((TextView) findViewById(R.id.orderDetail_contract_no)).setText(so.getContract_no());
		((TextView) findViewById(R.id.orderDetail_buy_unit_name)).setText(so.getBuy_unit_name());
		((TextView) findViewById(R.id.orderDetail_oversea_client_name)).setText(so.getOversea_client_name());
		((TextView) findViewById(R.id.orderDetail_trade_type_name)).setText(so.getTrade_type_name());
		((TextView) findViewById(R.id.orderDetail_trade_rule_name)).setText(so.getTrade_rule_name());
		((TextView) findViewById(R.id.orderDetail_clearing_way_name)).setText(so.getClearing_way_name());
		((TextView) findViewById(R.id.orderDetail_sale_way_name)).setText(so.getSale_way_name());
		((TextView) findViewById(R.id.orderDetail_currency_exchange)).setText(so.getCurrency_name() + " / " + so.getExchange_rate());
		((TextView) findViewById(R.id.orderDetail_order_type_name)).setText(so.getOrder_type_name());
		((TextView) findViewById(R.id.orderDetail_product_type_name)).setText(so.getProduct_type_name());
		((TextView) findViewById(R.id.orderDetail_product_use)).setText(so.getProduct_use());
		((TextView) findViewById(R.id.orderDetail_proc_dep_name)).setText(so.getProc_dep_name());
		((TextView) findViewById(R.id.orderDetail_project_group_name)).setText(so.getProject_group_name());

		((TextView) findViewById(R.id.orderDetail_delivery_place)).setText(so.getDelivery_place());
		((TextView) findViewById(R.id.orderDetail_user_name)).setText(so.getUser_name());
		((TextView) findViewById(R.id.orderDetail_department_name)).setText(so.getDepartment_name());
		((TextView) findViewById(R.id.orderDetail_charger_name)).setText(so.getCharger_name());
		((TextView) findViewById(R.id.orderDetail_clerk_name)).setText(so.getClerk_name() + " / " + so.getPercent1());
		((TextView) findViewById(R.id.orderDetail_clerk2_name)).setText(so.getClerk2_name() + " / " + so.getPercent2());
		((TextView) findViewById(R.id.orderDetail_back_paper_confirm_name)).setText(so.getBackpaper_confirm_name());
		((TextView) findViewById(R.id.orderDetail_product_way_name)).setText(so.getProduce_way_name());
		((TextView) findViewById(R.id.orderDetail_print_truly_name)).setText(so.getPrint_truly_name());
		((TextView) findViewById(R.id.orderDetail_client_logo_name)).setText(so.getClient_logo_name());
		((TextView) findViewById(R.id.orderDetail_sale_ps)).setText(so.getSalePs());
		((TextView) findViewById(R.id.orderDetail_description)).setText(so.getDescription());
		

		//设置表体
		ListView lv = (ListView) findViewById(R.id.orderDetail_list_view);
		lv.setAdapter(new SimpleAdapter(this, MyUtils.ConvertToMapList(sos, productSegs), R.layout.item_detail_order, productSegs, toViews));
		//表体高度自适应
		MyUtils.setListViewHeightBasedOnChildren(lv);

		//注册表体item点击事件
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SOOrderDetailActivity.this, SOOrderDetailMoreActivity.class);
				intent.putExtra(ORDERDETAIL, sos.get(position));
				startActivity(intent);

			}
		});

	}

}
