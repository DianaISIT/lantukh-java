package by.grsu.dlantukh.currency.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.dlantukh.currency.db.dao.AbstractDao;
import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.dao.impl.ClientDaoImpl;
import by.grsu.dlantukh.currency.db.dao.impl.CurrencyDaoImpl;
import by.grsu.dlantukh.currency.db.dao.impl.CurrencyRateDaoImpl;
import by.grsu.dlantukh.currency.db.dao.impl.TransactionDaoImpl;
import by.grsu.dlantukh.currency.db.model.Client;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.db.model.CurrencyRate;
import by.grsu.dlantukh.currency.db.model.Transaction;

public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, Client> clientDao = ClientDaoImpl.INSTANCE;
	private static final IDao<String, Currency> currencyDao = CurrencyDaoImpl.INSTANCE;
	private static final IDao<Integer, CurrencyRate> currencyRateDao = CurrencyRateDaoImpl.INSTANCE;
	private static final IDao<Integer, Transaction> transactionDao = TransactionDaoImpl.INSTANCE;

	private static final String DB_NAME = "production-db";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {
		Client clientEntity = new Client();
		clientEntity.setFirstName("IVAN");
		clientEntity.setLastName("IVANOV");
		clientEntity.setPatronymic("IVANOVICH");
		clientEntity.setPassport("KH67874374");
		clientDao.insert(clientEntity);
		System.out.println("created: " + clientEntity);

		Currency currencyEntity = new Currency();
		currencyEntity.setCode("EUR");
		currencyEntity.setName("ЕВРО");
		currencyDao.insert(currencyEntity);
		System.out.println("created: " + currencyEntity);

		CurrencyRate currencyRateEntity = new CurrencyRate();
		currencyRateEntity.setCurrencyFromCode(currencyEntity.getCode());
		currencyRateEntity.setCurrencyToCode(currencyEntity.getCode());
		currencyRateEntity.setValuePurchase(1.5f);
		currencyRateEntity.setValuePokypka(5.6f);
		currencyRateDao.insert(currencyRateEntity);
		System.out.println("created: " + currencyRateEntity);

		Transaction transactionEntity = new Transaction();
		transactionEntity.setClientId(clientEntity.getId());
		transactionEntity.setCurrencyCodeFrom(currencyEntity.getCode());
		transactionEntity.setCurrencyCodeTo(currencyEntity.getCode());
		transactionEntity.setAmount(456f);
		transactionEntity.setCreated(getCurrentTime());
		transactionEntity.setResult(678f);
		transactionDao.insert(transactionEntity);
		System.out.println("created: " + transactionEntity);
		System.out.println("initial data created");
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}
