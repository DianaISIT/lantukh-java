package by.grsu.dlantukh.currency.db.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.model.Client;

public class ClientDaoTest extends AbstractTest {
	private static final IDao<Integer, Client> dao = ClientDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Client entity = new Client();
		entity.setFirstName("IVAN");
		entity.setLastName("IVANOV");
		entity.setPatronymic("IVANOVICH");
		entity.setPassport("KH67874374");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Client entity = new Client();
		entity.setFirstName("IVAN");
		entity.setLastName("IVANOV");
		entity.setPatronymic("IVANOVICH");
		entity.setPassport("KH67874374");
		dao.insert(entity);

		String newName = "Petia";
		entity.setFirstName(newName);
		entity.setLastName("IVANOV");
		entity.setPatronymic("IVANOVICH");
		entity.setPassport("KH67874374");
		dao.update(entity);

		Client updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newName, updatedEntity.getName());
		Assertions.assertNotEquals(updatedEntity.getUpdated(), updatedEntity.getCreated());
	}

	@Test
	public void testDelete() {
		Brand entity = new Brand();
		entity.setName("VW");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Brand entity = new Brand();
		entity.setName("VW");
		entity.setCreated(getCurrentTime());
		entity.setUpdated(getCurrentTime());
		dao.insert(entity);

		Brand selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getCreated(), selectedEntity.getCreated());
		Assertions.assertEquals(entity.getUpdated(), selectedEntity.getUpdated());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Brand entity = new Brand();
			entity.setName("VW" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setCreated(getCurrentTime());
			entity.setUpdated(getCurrentTime());
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}