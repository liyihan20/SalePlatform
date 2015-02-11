package com.truly.ic.SalePlatform;

import com.truly.ic.models.SOOrderModel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SOOrderDetailMoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soorder_detail_more);
		
		SOOrderModel so=(SOOrderModel)getIntent().getSerializableExtra(SOOrderDetailActivity.ORDERDETAIL);
		
		((TextView) findViewById(R.id.orderDetailMore_product_no)).setText(so.getProduct_no());
		((TextView) findViewById(R.id.orderDetailMore_product_name)).setText(so.getProduct_name());
		((TextView) findViewById(R.id.orderDetailMore_product_model)).setText(so.getProduct_model());
		((TextView) findViewById(R.id.orderDetailMore_product_qty)).setText(so.getQty());
		((TextView) findViewById(R.id.orderDetailMore_product_unit)).setText(so.getUnit_name());
		((TextView) findViewById(R.id.orderDetailMore_product_cost)).setText(so.getCost());
		((TextView) findViewById(R.id.orderDetailMore_product_unit_price)).setText(so.getUnit_price());
		((TextView) findViewById(R.id.orderDetailMore_product_deal_price)).setText(so.getDeal_price());
		((TextView) findViewById(R.id.orderDetailMore_product_aux_price)).setText(so.getAux_tax_price());
		((TextView) findViewById(R.id.orderDetailMore_product_tax_rate)).setText(so.getTax_rate());
		((TextView) findViewById(R.id.orderDetailMore_product_fee_rate)).setText(so.getFee_rate());
		((TextView) findViewById(R.id.orderDetailMore_product_MU)).setText(so.getMU());
		((TextView) findViewById(R.id.orderDetailMore_product_commission_rate)).setText(so.getCommission_rate());
		((TextView) findViewById(R.id.orderDetailMore_product_commission)).setText(so.getCommission());
		((TextView) findViewById(R.id.orderDetailMore_product_discount_rate)).setText(so.getDiscount_rate());
		((TextView) findViewById(R.id.orderDetailMore_product_quote_no)).setText(so.getQuote_no());
		((TextView) findViewById(R.id.orderDetailMore_product_project_name)).setText(so.getProject_name());
		((TextView) findViewById(R.id.orderDetailMore_product_delivery_date)).setText(so.getDelivery_date());
		((TextView) findViewById(R.id.orderDetailMore_product_comment)).setText(so.getComment());
		
	}
}
