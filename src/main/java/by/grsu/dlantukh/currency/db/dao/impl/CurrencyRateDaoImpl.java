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
import by.grsu.dlantukh.currency.db.model.CurrencyRate;
import by.grsu.dlantukh.currency.db.model.Transaction;
import by.grsu.dlantukh.currency.web.dto.SortDto;
import by.grsu.dlantukh.currency.web.dto.TableStateDto;

//FIXME
public class CurrencyRateDaoImpl extends AbstractDao implements IDao<Integer, CurrencyRate> {

	// single instance of this class to be used by the all consumers
	public static final CurrencyRateDaoImpl INSTANCE = new CurrencyRateDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private CurrencyRateDaoImpl() {
		super();
	}

	public void insert(CurrencyRate entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"insert into currency_rate(currency_from_code, currency_to_code, value_purchase, value_pokypka) values(?,?,?,?)");
			pstmt.setString(1, entity.getCurrencyFromCode());
			pstmt.setString(2, entity.getCurrencyToCode());
			pstmt.setFloat(3, entity.getValuePurchase());
			pstmt.setFloat(4, entity.getValuePokypka());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("can't insert currency_rate entity", e);
		}
	}

	public void update(CurrencyRate entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement(
					"update currency_rate set currency_from_code=?, currency_to_code=?, value_purchase=?, value_pokypka=?");
			pstmt.setString(1, entity.getCurrencyFromCode());
			pstmt.setString(2, entity.getCurrencyToCode());
			pstmt.setFloat(3, entity.getValuePurchase());
			pstmt.setFloat(4, entity.getValuePokypka());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update currency_rate entity", e);
		}
	}

	public void delete(String cFrom, String cTo) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("delete from currency_rate where currency_from_code=? and currency_to_code=?");
			pstmt.setString(1, cFrom);
			pstmt.setString(2, cTo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete currency_rate entity", e);
		}

	}

	public CurrencyRate getById(String cFrom, String cTo) {
		CurrencyRate entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("select * from currency_rate where currency_from_code=? and currency_to_code=?");
			pstmt.setString(1, cFrom);
			pstmt.setString(2, cTo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get currency_rate entity by code", e);
		}

		return entity;
	}

	public List<CurrencyRate> getAll() {
		List<CurrencyRate> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from currency_rate");
			while (rs.next()) {
				CurrencyRate entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select currency_rate entities", e);
		}

		return entitiesList;
	}

	private CurrencyRate rowToEntity(ResultSet rs) throws SQLException {
		CurrencyRate entity = new CurrencyRate();
		entity.setCurrencyFromCode(rs.getString("currency_from_code"));
		entity.setCurrencyToCode(rs.getString("currency_to_code"));
		entity.setValuePurchase(rs.getFloat("value_purchase"));
		entity.setValuePokypka(rs.getFloat("value_pokypka"));
		return entity;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public CurrencyRate getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrencyRate> find(TableStateDto tableStateDto) {
		List<CurrencyRate> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from currency_rate");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching CurrencyRate using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				CurrencyRate entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select CurrencyRate entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from currency_rate");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get currency_rate count", e);
		}
	}

}