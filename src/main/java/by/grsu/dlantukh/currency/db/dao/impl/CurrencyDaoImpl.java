package by.grsu.dlantukh.currency.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dlantukh.currency.db.dao.AbstractDao;
import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.web.dto.SortDto;
import by.grsu.dlantukh.currency.web.dto.TableStateDto;

public class CurrencyDaoImpl extends AbstractDao implements IDao<String, Currency> {

	// single instance of this class to be used by the all consumers
	public static final CurrencyDaoImpl INSTANCE = new CurrencyDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private CurrencyDaoImpl() {
		super();
	}

	@Override
	public void insert(Currency entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into currency(code, name) values(?,?)");
			pstmt.setString(1, entity.getCode());
			pstmt.setString(2, entity.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't insert currency entity", e);
		}
	}

	@Override
	public void update(Currency entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update currency set name=? where code=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getCode());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update currency entity", e);
		}
	}

	@Override
	public void delete(String id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from currency where code=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete currency entity", e);
		}

	}

	@Override
	public Currency getById(String id) {
		Currency entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from currency where code=?");
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get currency entity by code", e);
		}

		return entity;
	}

	@Override
	public List<Currency> getAll() {
		List<Currency> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from currency");
			while (rs.next()) {
				Currency entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select currency entities", e);
		}

		return entitiesList;
	}

	private Currency rowToEntity(ResultSet rs) throws SQLException {
		Currency entity = new Currency();
		entity.setCode(rs.getString("code"));
		entity.setName(rs.getString("name"));
		return entity;
	}

	@Override
	public List<Currency> find(TableStateDto tableStateDto) {
		List<Currency> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from currency");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Currencyes using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Currency entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Currency entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from currency");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get currency count", e);
		}
	}

}