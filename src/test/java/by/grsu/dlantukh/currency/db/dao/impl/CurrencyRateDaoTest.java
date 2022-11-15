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
		entity.setCurrencyFromCode(saveCurrency("code").getCode());
		entity.setCurrencyToCode(saveCurrency("code").getCode());
		entity.setValuePurchase(1.5f);
		entity.setValuePokypka(5.6f);

		currencyRateDao.insert(entity);

		Assertions.assertNotNull(currencyRateDao.getById(null, null));
	}

	@Test
	public void testUpdate() {
		CurrencyRate entity = new CurrencyRate();
		entity.setCurrencyFromCode(saveCurrency("code").getCode());
		entity.setCurrencyToCode(saveCurrency("code").getCode());
		entity.setValuePurchase(1.5f);
		entity.setValuePokypka(5.6f);
		currencyRateDao.insert(entity);

		Float newValuePurchase = 1.7f;
		entity.setValuePurchase(newValuePurchase);
		currencyRateDao.insert(entity);

		Float newValuePokypka = 1.8f;
		entity.setValuePokypka(newValuePokypka);
		currencyRateDao.insert(entity);

		CurrencyRate updatedEntity = currencyRateDao.getById(null, null);

		Assertions.assertEquals(newValuePurchase, updatedEntity.getValuePurchase());
		Assertions.assertEquals(newValuePokypka, updatedEntity.getValuePokypka());
	}

	@Test
	public void testDelete() {
		CurrencyRate entity = new CurrencyRate();
		entity.setCurrencyFromCode(saveCurrency("code").getCode());
		entity.setCurrencyToCode(saveCurrency("code").getCode());
		entity.setValuePurchase(1.5f);
		entity.setValuePokypka(5.6f);
		currencyRateDao.insert(entity);

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

		CurrencyRate selectedEntity = currencyRateDao.getById(null, null);

		Assertions.assertEquals(entity.getValuePurchase(), selectedEntity.getValuePurchase());
		Assertions.assertEquals(entity.getValuePokypka(), selectedEntity.getValuePokypka());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			CurrencyRate entity = new CurrencyRate();
			entity.setCurrencyFromCode(saveCurrency("code+i").getCode()); // generate some random meaningless name as it
																			// is just a test (the data can be unreal)
			entity.setCurrencyToCode(saveCurrency("code+i").getCode());
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