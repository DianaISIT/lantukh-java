package by.grsu.dlantukh.currency.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.db.model.CurrencyRate;

public class CurrencyRateDaoTest extends AbstractTest {
	private static final CurrencyRateDaoImpl currencyRateDao = CurrencyRateDaoImpl.INSTANCE;
	private static final IDao<String, Currency> currencyDao = CurrencyDaoImpl.INSTANCE;

	@Test
	public void testInsert() {

		// insert currency

		CurrencyRate entity = new CurrencyRate();
		entity.setCurrencyFromCode(saveCurrency("USD").getCode());
		entity.setCurrencyToCode(saveCurrency("EUR").getCode());
		entity.setValuePurchase(1.5f);
		entity.setValuePokypka(5.6f);

		currencyRateDao.insert(entity);
		
		Assertions.assertNotNull(entity.getCurrencyFromCode(),entity.getCurrencyToCode());
		
	}

	@Test
	public void testUpdate() {
		CurrencyRate entity = new CurrencyRate();
		entity.setCurrencyFromCode(saveCurrency("USD").getCode());
		entity.setCurrencyToCode(saveCurrency("EUR").getCode());
		entity.setValuePurchase(1.5f);
		entity.setValuePokypka(5.6f);
		currencyRateDao.insert(entity);

		Currency newCurrency = saveCurrency("RUB");
		entity.setCurrencyFromCode(newCurrency.getCode());

		Currency newCurrency1 = saveCurrency("PLN");
		entity.setCurrencyToCode(newCurrency1.getCode());
		
		entity.setValuePurchase(5.9f);
		entity.setValuePokypka(9.7f);
		
		currencyRateDao.insert(entity);

		CurrencyRate updatedEntity = currencyRateDao.getById(entity.getCurrencyFromCode(),entity.getCurrencyToCode());

		Assertions.assertEquals(newCurrency.getCode(), updatedEntity.getCurrencyFromCode());
		Assertions.assertEquals(newCurrency1.getCode(), updatedEntity.getCurrencyToCode());
		Assertions.assertEquals(5.9f, updatedEntity.getValuePurchase());
		Assertions.assertEquals(9.7f, updatedEntity.getValuePokypka());
	}

	@Test
	public void testDelete() {
		CurrencyRate entity = new CurrencyRate();
		entity.setCurrencyFromCode(saveCurrency("USD").getCode());
		entity.setCurrencyToCode(saveCurrency("EUR").getCode());
		entity.setValuePurchase(1.5f);
		entity.setValuePokypka(5.6f);
		currencyRateDao.insert(entity);
		
		currencyRateDao.delete(entity.getCurrencyFromCode(),entity.getCurrencyToCode());

		Assertions.assertNull(currencyRateDao.getById(null, null));
	}

	@Test
	public void testGetById() { ////
		CurrencyRate entity = new CurrencyRate();
		Currency cFrom = saveCurrency("USD");
		Currency cTo = saveCurrency("BYN");
		entity.setCurrencyFromCode(cFrom.getCode());
		entity.setCurrencyToCode(cTo.getCode());
		entity.setValuePurchase(1.5f);
		entity.setValuePokypka(5.6f);
		currencyRateDao.insert(entity);

		CurrencyRate selectedEntity = currencyRateDao.getById(entity.getCurrencyFromCode(),entity.getCurrencyToCode());

		Assertions.assertEquals(entity.getCurrencyFromCode(), selectedEntity.getCurrencyFromCode());
		Assertions.assertEquals(entity.getCurrencyToCode(), selectedEntity.getCurrencyToCode());
		Assertions.assertEquals(entity.getValuePurchase(), selectedEntity.getValuePurchase());
		Assertions.assertEquals(entity.getValuePokypka(), selectedEntity.getValuePokypka());
		
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			CurrencyRate entity = new CurrencyRate();
			entity.setCurrencyFromCode(saveCurrency("EUR"+i).getCode()); // generate some random meaningless name as it
																			// is just a test (the data can be unreal)
			entity.setCurrencyToCode(saveCurrency("USD"+i).getCode());
			entity.setValuePurchase(1.5f + i);
			entity.setValuePokypka(5.6f + i);
			currencyRateDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, currencyRateDao.getAll().size());
	}

	private Currency saveCurrency(String currency) {
		Currency entity = new Currency();
		entity.setCode(currency);
		entity.setName(currency + "_name");
		currencyDao.insert(entity);
		return entity;
	}
}