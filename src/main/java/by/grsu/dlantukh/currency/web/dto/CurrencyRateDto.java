package by.grsu.dlantukh.currency.web.dto;

public class CurrencyRateDto {

	private String currencyFromCode;

	private String currencyToCode;

	private String currencyName;

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

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
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

}
