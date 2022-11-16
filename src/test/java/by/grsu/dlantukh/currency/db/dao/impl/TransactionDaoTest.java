package by.grsu.dlantukh.currency.db.dao.impl;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.model.Client;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.db.model.CurrencyRate;
import by.grsu.dlantukh.currency.db.model.Transaction;

public class TransactionDaoTest extends AbstractTest {
	private static final IDao<Integer, Client> dao = ClientDaoImpl.INSTANCE;
	private static final IDao<String, Currency> currencyDao = CurrencyDaoImpl.INSTANCE;
	private static final IDao<Integer, Transaction> transactionDao = TransactionDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Transaction entity = new Transaction();
		entity.setClientId(saveClient().getId());
		entity.setCurrencyCodeFrom(saveCurrency("EUR").getCode());
		entity.setCurrencyCodeTo(saveCurrency("RUB").getCode());
		entity.setAmount(456f);
		entity.setCreated(getCurrentTime());
		entity.setResult(678f);
		transactionDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}


	@Test
	public void testUpdate() {
		Transaction entity = new Transaction();
		entity.setClientId(saveClient().getId());
		entity.setCurrencyCodeFrom(saveCurrency("EUR").getCode());
		entity.setCurrencyCodeTo(saveCurrency("RUB").getCode());
		entity.setAmount(456f);
		Timestamp currentTime = getCurrentTime();
		entity.setCreated(currentTime);
		entity.setResult(678f);
		
		transactionDao.insert(entity);

		
		
		
		
		
		
		Client newClient = saveClient();
		Currency newCurrency = saveCurrency("USD");
	    Currency newCurrency1 = saveCurrency("PLN");
	    entity.setClientId(newClient.getId());
	    entity.setCurrencyCodeFrom(newCurrency.getCode());
	    entity.setCurrencyCodeTo(newCurrency1.getCode());
		entity.setAmount(345f);
		entity.setCreated(currentTime);
		entity.setResult(567f);
		transactionDao.update(entity);

		
		
		
		Transaction updatedEntity = transactionDao.getById(entity.getId());
		

		Assertions.assertEquals(newCurrency.getCode(), updatedEntity.getCurrencyCodeFrom());
		Assertions.assertEquals(newCurrency1.getCode(), updatedEntity.getCurrencyCodeTo());
		Assertions.assertEquals(345f , updatedEntity.getAmount()); 
		Assertions.assertEquals(currentTime, updatedEntity.getCreated());
		Assertions.assertEquals(567f , updatedEntity.getResult()); 
	}

	@Test
	public void testDelete() {
		Transaction entity = new Transaction();
		entity.setClientId(saveClient().getId());
		entity.setCurrencyCodeFrom(saveCurrency("EUR").getCode());
		entity.setCurrencyCodeTo(saveCurrency("RUB").getCode());
		entity.setAmount(456f);
		entity.setCreated(getCurrentTime());
		entity.setResult(678f);
		transactionDao.insert(entity);


		transactionDao.delete(entity.getId());

		Assertions.assertNull(transactionDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Transaction entity = new Transaction();
		entity.setClientId(saveClient().getId());
		entity.setCurrencyCodeFrom(saveCurrency("EUR").getCode());
		entity.setCurrencyCodeTo(saveCurrency("RUB").getCode());
		entity.setAmount(456f);
		entity.setCreated(getCurrentTime());
		entity.setResult(678f);
		transactionDao.insert(entity);

		Transaction selectedEntity = transactionDao.getById(entity.getId());

		Assertions.assertEquals(entity.getClientId(), selectedEntity.getClientId());
		Assertions.assertEquals(entity.getCurrencyCodeFrom(), selectedEntity.getCurrencyCodeFrom());
		Assertions.assertEquals(entity.getCurrencyCodeTo(), selectedEntity.getCurrencyCodeTo());
		Assertions.assertEquals(entity.getAmount(), selectedEntity.getAmount());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getResult(), selectedEntity.getResult());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Transaction entity = new Transaction();
			entity.setClientId(saveClient().getId());
			entity.setCurrencyCodeFrom(saveCurrency("EUR+"+i).getCode());
			entity.setCurrencyCodeTo(saveCurrency("RUB"+i).getCode());
			entity.setAmount(456f+i);
			entity.setCreated(getCurrentTime());
			entity.setResult(678f+i);
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

	private Currency saveCurrency(String currency) {
		Currency entity = new Currency();
		entity.setCode(currency);
		entity.setName(currency + "_name");
		currencyDao.insert(entity);
		return entity;

	}
}