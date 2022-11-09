package by.grsu.dlantukh.currency.db.model;

public class CurrencyRate {
	
	
	private String currencyFromCode;
	
	private String currencyToCode;
	
	private Float valuePurchase;
	
	private Float valuePokypka;

	public String getCurrencyFromCode() {
		return currencyFromCode;
	}

	public void setCurrencyFromCode(String currencyFromCode) {
		this.currencyFromCode = currencyFromCode;
	}

	public String getCurrencyToCode() {
		return currencyToCode;
	}

	public void setCurrencyToCode(String currencyToCode) {
		this.currencyToCode = currencyToCode;
	}

	public Float getValuePurchase() {
		return valuePurchase;
	}

	public void setValuePurchase(Float valuePurchase) {
		this.valuePurchase = valuePurchase;
	}

	public Float getValuePokypka() {
		return valuePokypka;
	}

	public void setValuePokypka(Float valuePokypka) {
		this.valuePokypka = valuePokypka;
	}

	@Override
	public String toString() {
		return "CurrencyRate [currencyFromCode=" + currencyFromCode + ", currencyToCode=" + currencyToCode
				+ ", valuePurchase=" + valuePurchase + ", valuePokypka=" + valuePokypka + ", getCurrencyFromCode()="
				+ getCurrencyFromCode() + ", getCurrencyToCode()=" + getCurrencyToCode() + ", getValuePurchase()="
				+ getValuePurchase() + ", getValuePokypka()=" + getValuePokypka() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}