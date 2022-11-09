package by.grsu.dlantukh.currency;

import java.sql.Timestamp;
import java.util.Date;

import by.grsu.dlantukh.currency.db.model.Client;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.db.model.CurrencyRate;
import by.grsu.dlantukh.currency.db.model.Transaction;

public class Main{
	
	public static void main(String [] args) {
		
		Currency currency = new Currency();
		
		currency.setCode(1);
		currency.setName("EUR");;
		System.out.println (currency.toString());
		
		
		////////////////
		
		Transaction transaction = new Transaction();
		
		transaction.setId(1);
		transaction.setClientId(1);
		transaction.setCurrencyCodeFrom("EUR");
		transaction.setCurrencyCodeTo("USD");
		transaction.setAmount(5000.0f);
		transaction.setDate(new Timestamp(new Date().getTime()));
		transaction.setResult(155f);
		System.out.println (transaction.toString());
		
		/////////
		
		Client client = new Client();
		
		client.setId(1);
		client.setFirstName("Ivan");
		client.setLastName("Ivanov");
		client.setPatronymic("Ivanovich");
		client.setPassport("KH54678765");
		System.out.println (client.toString());
		
		//////////
		
		CurrencyRate currencyrate = new CurrencyRate();
		
		currencyrate.setCurrencyFromCode("EUR");
		currencyrate.setCurrencyToCode("USD");
		currencyrate.setValuePurchase(0.9950f);
		currencyrate.setValuePokypka(0.9760f);
		System.out.println (currencyrate.toString());
		
	}
}