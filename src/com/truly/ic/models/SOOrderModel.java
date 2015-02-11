package com.truly.ic.models;

import java.io.Serializable;

public class SOOrderModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3668403974066487421L;
	private String id;
	private String order_date;
	private String sys_no;
	private String order_no;
	private String contract_no;
	private String buy_unit_name;
	private String oversea_client_name;
	private String trade_type_name;
	private String trade_rule_name;
	private String clearing_way_name;
	private String sale_way_name;
	private String currency_name;
	private String exchange_rate;
	private String order_type_name;
	private String product_type_name;
	private String product_use;
	private String proc_dep_name;
	private String department_name;
	private String project_group_name;
	private String charger_name;
	private String user_name;

	private String entry_id;
	private String product_no;
	private String product_name;
	private String product_model;
	private String qty;
	private String unit_name;
	private String unit_price;
	private String deal_price;
	private String cost;
	private String aux_tax_price;
	private String tax_rate;
	private String fee_rate;
	private String commission_rate;
	private String MU;
	private String commission;
	private String quote_no;
	private String delivery_date;
	private String discount_rate;
	private String comment;
	private String project_name;

	private String clerk_name;
	private String clerk2_name;
	private String percent1;
	private String percent2;
	private String delivery_place;
	private String description;
	private String salePs;
	private String backpaper_confirm_name;
	private String produce_way_name;
	private String print_truly_name;
	private String client_logo_name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getSys_no() {
		return sys_no;
	}
	public void setSys_no(String sys_no) {
		this.sys_no = sys_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getBuy_unit_name() {
		return buy_unit_name;
	}
	public void setBuy_unit_name(String buy_unit_name) {
		this.buy_unit_name = buy_unit_name;
	}
	public String getOversea_client_name() {
		return oversea_client_name;
	}
	public void setOversea_client_name(String oversea_client_name) {
		this.oversea_client_name = oversea_client_name;
	}
	public String getTrade_type_name() {
		return trade_type_name;
	}
	public void setTrade_type_name(String trade_type_name) {
		this.trade_type_name = trade_type_name;
	}
	public String getTrade_rule_name() {
		return trade_rule_name;
	}
	public void setTrade_rule_name(String trade_rule_name) {
		this.trade_rule_name = trade_rule_name;
	}
	public String getClearing_way_name() {
		return clearing_way_name;
	}
	public void setClearing_way_name(String clearing_way_name) {
		this.clearing_way_name = clearing_way_name;
	}
	public String getSale_way_name() {
		return sale_way_name;
	}
	public void setSale_way_name(String sale_way_name) {
		this.sale_way_name = sale_way_name;
	}
	public String getCurrency_name() {
		return currency_name;
	}
	public void setCurrency_name(String currency_name) {
		this.currency_name = currency_name;
	}
	public String getExchange_rate() {
		return exchange_rate;
	}
	public void setExchange_rate(String exchange_rate) {
		this.exchange_rate = exchange_rate;
	}
	public String getOrder_type_name() {
		return order_type_name;
	}
	public void setOrder_type_name(String order_type_name) {
		this.order_type_name = order_type_name;
	}
	public String getProduct_type_name() {
		return product_type_name;
	}
	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}
	public String getProduct_use() {
		return product_use;
	}
	public void setProduct_use(String product_use) {
		this.product_use = product_use;
	}
	
	public String getProc_dep_name() {
		return proc_dep_name;
	}
	public void setProc_dep_name(String proc_dep_name) {
		this.proc_dep_name = proc_dep_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getProject_group_name() {
		return project_group_name;
	}
	public void setProject_group_name(String project_group_name) {
		this.project_group_name = project_group_name;
	}
	public String getCharger_name() {
		return charger_name;
	}
	public void setCharger_name(String charger_name) {
		this.charger_name = charger_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_model() {
		return product_model;
	}
	public void setProduct_model(String product_model) {
		this.product_model = product_model;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getDeal_price() {
		return deal_price;
	}
	public void setDeal_price(String deal_price) {
		this.deal_price = deal_price;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getAux_tax_price() {
		return aux_tax_price;
	}
	public void setAux_tax_price(String aux_tax_price) {
		this.aux_tax_price = aux_tax_price;
	}
	public String getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(String tax_rate) {
		this.tax_rate = tax_rate;
	}
	public String getFee_rate() {
		return fee_rate;
	}
	public void setFee_rate(String fee_rate) {
		this.fee_rate = fee_rate;
	}
	public String getCommission_rate() {
		return commission_rate;
	}
	public void setCommission_rate(String commission_rate) {
		this.commission_rate = commission_rate;
	}
	public String getMU() {
		return MU;
	}
	public void setMU(String mU) {
		MU = mU;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	
	public String getQuote_no() {
		return quote_no;
	}
	public void setQuote_no(String quote_no) {
		this.quote_no = quote_no;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(String discount_rate) {
		this.discount_rate = discount_rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getClerk_name() {
		return clerk_name;
	}
	public void setClerk_name(String clerk_name) {
		this.clerk_name = clerk_name;
	}
	public String getClerk2_name() {
		return clerk2_name;
	}
	public void setClerk2_name(String clerk2_name) {
		this.clerk2_name = clerk2_name;
	}
	public String getPercent1() {
		return percent1;
	}
	public void setPercent1(String percent1) {
		this.percent1 = percent1;
	}
	public String getPercent2() {
		return percent2;
	}
	public void setPercent2(String percent2) {
		this.percent2 = percent2;
	}
	public String getDelivery_place() {
		return delivery_place;
	}
	public void setDelivery_place(String delivery_place) {
		this.delivery_place = delivery_place;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSalePs() {
		return salePs;
	}
	public void setSalePs(String salePs) {
		this.salePs = salePs;
	}
	
	public String getBackpaper_confirm_name() {
		return backpaper_confirm_name;
	}
	public void setBackpaper_confirm_name(String backpaper_confirm_name) {
		this.backpaper_confirm_name = backpaper_confirm_name;
	}
	public String getProduce_way_name() {
		return produce_way_name;
	}
	public void setProduce_way_name(String produce_way_name) {
		this.produce_way_name = produce_way_name;
	}
	public String getPrint_truly_name() {
		return print_truly_name;
	}
	public void setPrint_truly_name(String print_truly_name) {
		this.print_truly_name = print_truly_name;
	}
	public String getClient_logo_name() {
		return client_logo_name;
	}
	public void setClient_logo_name(String client_logo_name) {
		this.client_logo_name = client_logo_name;
	}

}
