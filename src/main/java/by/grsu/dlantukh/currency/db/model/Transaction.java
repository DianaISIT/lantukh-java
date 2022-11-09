package by.grsu.dlantukh.currency.db.model;

import java.sql.Timestamp;

public class Transaction {
	
	    private Integer id;
		
		private Integer clientId;
		
		private String currencyCodeFrom;
		
		private String currencyCodeTo;
		
		private Float amount;
		
		private Timestamp date;
		
		private Float result;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getClientId() {
			return clientId;
		}

		public void setClientId(Integer clientId) {
			this.clientId = clientId;
		}

		public String getCurrencyCodeFrom() {
			return currencyCodeFrom;
		}

		public void setCurrencyCodeFrom(String currencyCodeFrom) {
			this.currencyCodeFrom = currencyCodeFrom;
		}

		public String getCurrencyCodeTo() {
			return currencyCodeTo;
		}

		public void setCurrencyCodeTo(String currencyCodeTo) {
			this.currencyCodeTo = currencyCodeTo;
		}

		public Float getAmount() {
			return amount;
		}

		public void setAmount(Float amount) {
			this.amount = amount;
		}

		public Timestamp getDate() {
			return date;
		}

		public void setDate(Timestamp date) {
			this.date = date;
		}

		public Float getResult() {
			return result;
		}

		public void setResult(Float result) {
			this.result = result;
		}

		@Override
		public String toString() {
			return "Transaction [id=" + id + ", clientId=" + clientId + ", currencyCodeFrom=" + currencyCodeFrom
					+ ", currencyCodeTo=" + currencyCodeTo + ", amount=" + amount + ", date=" + date + ", result="
					+ result + ", getId()=" + getId() + ", getClientId()=" + getClientId() + ", getCurrencyCodeFrom()="
					+ getCurrencyCodeFrom() + ", getCurrencyCodeTo()=" + getCurrencyCodeTo() + ", getAmount()="
					+ getAmount() + ", getDate()=" + getDate() + ", getResult()=" + getResult() + ", getClass()="
					+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

}
