package com.truly.ic.models;

public class SimpleSOModel {

		private String agency;
		private Integer id;
		private String model;
		private String orderDate;
		private String orderNo;
		private Integer qty;
		private String salerName;
		private String sysNo;
		private String productName;

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getAgency() {
			return agency;
		}

		public void setAgency(String agency) {
			this.agency = agency;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getSysNo() {
			return sysNo;
		}

		public void setSysNo(String sysNo) {
			this.sysNo = sysNo;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getSalerName() {
			return salerName;
		}

		public void setSalerName(String salerName) {
			this.salerName = salerName;
		}

		public String getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}

		public Integer getQty() {
			return qty;
		}

		public void setQty(Integer qty) {
			this.qty = qty;
		}

}
