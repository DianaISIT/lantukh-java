package by.grsu.dlantukh.currency.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.model.Client;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.db.model.CurrencyRate;
import by.grsu.dlantukh.currency.db.model.Transaction;

public class TransactionDaoTest extends AbstractTest {
	private static final IDao<Integer, Client> dao = ClientDaoImpl.INSTANCE;
	private static final CurrencyRateDaoImpl currencyRateDao = CurrencyRateDaoImpl.INSTANCE;
	private static final IDao<String, Currency> currencyDao = CurrencyDaoImpl.INSTANCE;
	private static final IDao<Integer, Transaction> transactionDao = TransactionDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Transaction entity = new Transaction();
		//entity.setClientId(saveClient(1).getId());
	//	entity.setCurrencyCodeFrom(saveCurrency("EUR").getString());
	//	entity.setCurrencyCodeFrom(saveCurrency("RUB").getString());
		entity.setAmount(456f);
		entity.setDate(getCurrentTime());
		entity.setResult(456f);
		transactionDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}


	@Test
	public void testUpdate() {
		Transaction entity = new Transaction();
		entity.setClientId(saveClient(1).getId());
		entity.setCurrencyCodeFrom(saveCurrency("EUR").getString());
		entity.setCurrencyCodeFrom(saveCurrency("RUB").getString());
		entity.setAmount(456f);
		entity.setDate(getCurrentTime());
		entity.setResult(456f);
		transactionDao.insert(entity);

		Client newClient = saveClient(2);
		entity.setClientId(newClient.getId());
		
		Currency newCurrency = saveCurrency("USD");
	    entity.setCurrencyCodeFrom(newCurrency.getString());
	    
	    Currency newCurrency = saveCurrency("EUR");
	    entity.setCurrencyCodeTo(newCurrency.getString());
	    
	    entity.setAmount(678f);
		
		entity.setDate(getCurrentTime());
		
		entity.setResult(567f);
		transactionDao.update(entity);

		Transaction updatedEntity = transactionDao.getById(entity.getId());
		Assertions.assertEquals(newClient.getId(), updatedEntity.getClientId());
		Assertions.assertEquals(newCurrency.getCode(), updatedEntity.getCurrencyCodeFrom());
		Assertions.assertEquals(newCurrency.getCode(), updatedEntity.getCurrencyCodeTo());
		Assertions.assertEquals(678f , updatedEntity.getAmount()); 
		Assertions.assertNotEquals(updatedEntity.getDate());
		Assertions.assertEquals(678f , updatedEntity.getResult()); 
	}

	@Test
	public void testDelete() {
		Transaction entity = new Transaction();
		entity.setClientId(saveClient(1).getId());
		entity.setCurrencyCodeFrom(saveCurrency("EUR").getString());
		entity.setCurrencyCodeFrom(saveCurrency("RUB").getString());
		entity.setAmount(456f);
		entity.setDate(getCurrentTime());
		entity.setResult(456f);
		transactionDao.insert(entity);


		transactionDao.delete(entity.getId());

		Assertions.assertNull(transactionDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Transaction entity = new Transaction();
		entity.setClientId(saveClient(1).getId());
		entity.setCurrencyCodeFrom(saveCurrency("EUR").getString());
		entity.setCurrencyCodeFrom(saveCurrency("RUB").getString());
		entity.setAmount(456f);
		entity.setDate(getCurrentTime());
		entity.setResult(456f);
		transactionDao.insert(entity);

		Transaction selectedEntity = transactionDao.getById(entity.getId());

		Assertions.assertEquals(entity.getClientId(), selectedEntity.getClientId());
		Assertions.assertEquals(entity.getCurrencyCodeFrom(), selectedEntity.getCurrencyCodeFrom());
		Assertions.assertEquals(entity.getCurrencyCodeTo(), selectedEntity.getCurrencyCodeTo());
		Assertions.assertEquals(entity.getAmount(), selectedEntity.getAmount());
		Assertions.assertEquals(entity.getDate(), selectedEntity.getDate());
		Assertions.assertEquals(entity.getResult(), selectedEntity.getResult());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Transaction entity = new Transaction();
			entity.setClientId(saveClient(1+i).getId());
			entity.setCurrencyCodeFrom(saveCurrency("EUR+"+i).getString());
			entity.setCurrencyCodeFrom(saveCurrency("RUB"+i).getString());
			entity.setAmount(456f+i);
			entity.setDate(getCurrentTime());
			entity.setResult(456f+i);
			transactionDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, transactionDao.getAll().size());
	}

	private Client saveClient() {
		Client entity = new Client();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		entity.setPatronymic("Ivanovich");
		entity.setPassport("KH678435678");
		dao.insert(entity);
		return entity;
	}

	private Currency saveCurrency() {
		Currency entity = new Currency();
		entity.setCode("EUR");
		entity.setName("EВРО");
		currencyDao.insert(entity);

		return entity;
	}
}