package by.grsu.dlantukh.currency.web.dto;

import java.sql.Timestamp;

public class TransactionDto {
	 @Override
	public String toString() {
		return "TransactionDto [id=" + id + ", clientId=" + clientId + ", clientFirstName=" + clientFirstName
				+ ", currencyCodeFrom=" + currencyCodeFrom + ", currencyCodeTo=" + currencyCodeTo + ", amount=" + amount
				+ ", created=" + created + ", result=" + result + "]";
	}

	    private Integer id;
		
		private Integer clientId;
		private String clientFirstName;
		
		private String currencyCodeFrom;
		
		private String currencyCodeTo;
		
		private Float amount;
		
		private Timestamp created;
		
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

		public String getClientFirstName() {
			return clientFirstName;
		}

		public void setClientFirstName(String clientFirstName) {
			this.clientFirstName = clientFirstName;
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

		public Timestamp getCreated() {
			return created;
		}

		public void setCreated(Timestamp created) {
			this.created = created;
		}

		public Float getResult() {
			return result;
		}

		public void setResult(Float result) {
			this.result = result;
		}



}
