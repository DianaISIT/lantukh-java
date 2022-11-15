package by.grsu.dlantukh.currency.db.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.model.Currency;

public class CurrencyDaoTest extends AbstractTest {
	private static final IDao<String, Currency> dao = CurrencyDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Currency entity = new Currency();
		entity.setCode("EUR");
		entity.setName("ЕВРО");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getCode());
	}

	@Test
	public void testUpdate() {
		Currency entity = new Currency();
		entity.setCode("EUR");
		entity.setName("ЕВРО");
		dao.insert(entity);

		
		String newName = "РУБЛИ";
		entity.setName(newName);
		dao.update(entity);

		Currency updatedEntity = dao.getById(entity.getCode());
		
		Assertions.assertEquals( newName, updatedEntity.getName());
		Assertions.assertNotNull(updatedEntity);
	}

	@Test
	public void testDelete() {
		Currency entity = new Currency();
		entity.setCode("RUB");
		entity.setName("РУБЛИ");
		dao.insert(entity);

		dao.delete(entity.getCode());

		Assertions.assertNull(dao.getById(entity.getCode()));
	}

	@Test
	public void testGetById() {   ////
		Currency entity = new Currency();
		entity.setCode("RUB");
		entity.setName("РУБЛИ");
		dao.insert(entity);

		Currency selectedEntity = dao.getById(entity.getCode());

		Assertions.assertEquals(entity.getCode(), selectedEntity.getCode());
		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Currency entity = new Currency();
			entity.setCode("RUB" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setName("IVANOV");
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}