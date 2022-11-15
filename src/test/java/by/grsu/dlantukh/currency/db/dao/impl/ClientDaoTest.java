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

		String newFirstName = "Petia";
		entity.setFirstName(newFirstName);
		String newLastName = "FTYU";
		entity.setLastName(newLastName);
		String newPatronymic= "ALEKCEEVICH";
		entity.setPatronymic(newPatronymic);
		String newPassport= "KH56789767";
		entity.setPassport(newPassport);
		dao.update(entity);

		Client updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newFirstName, updatedEntity.getFirstName());
		Assertions.assertEquals( newLastName, updatedEntity.getLastName());
		Assertions.assertEquals( newPatronymic, updatedEntity.getPatronymic());
		Assertions.assertEquals( newPassport, updatedEntity.getPassport());
		Assertions.assertNotNull(updatedEntity);
	}

	@Test
	public void testDelete() {
		Client entity = new Client();
		entity.setFirstName("IVAN");
		entity.setLastName("IVANOV");
		entity.setPatronymic("IVANOVICH");
		entity.setPassport("KH67874374");
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Client entity = new Client();
		entity.setFirstName("IVAN");
		entity.setLastName("IVANOV");
		entity.setPatronymic("IVANOVICH");
		entity.setPassport("KH67874374");
		dao.insert(entity);

		Client selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getFirstName(), selectedEntity.getFirstName());
		Assertions.assertEquals(entity.getLastName(), selectedEntity.getLastName());
		Assertions.assertEquals(entity.getPatronymic(), selectedEntity.getPatronymic());
		Assertions.assertEquals(entity.getPassport(), selectedEntity.getPassport());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Client entity = new Client();
			entity.setFirstName("IVAN" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setLastName("IVANOV");
			entity.setPatronymic("IVANOVICH");
			entity.setPassport("KH67874374");
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}