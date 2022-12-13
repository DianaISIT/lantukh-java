package by.grsu.dlantukh.currency.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dlantukh.currency.db.dao.AbstractDao;
import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.model.Transaction;
import by.grsu.dlantukh.currency.web.dto.SortDto;
import by.grsu.dlantukh.currency.web.dto.TableStateDto;

public class TransactionDaoImpl extends AbstractDao implements IDao<Integer, Transaction> {
	public static final TransactionDaoImpl INSTANCE = new TransactionDaoImpl();

	private TransactionDaoImpl() {
		super();
	}

	@Override
	public void insert(Transaction entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into tranzaction(client_id, currency_code_from, currency_code_to, amount, created, result) values(?,?,?,?,?,?)");
			pstmt.setInt(1, entity.getClientId());
			pstmt.setString(2, entity.getCurrencyCodeFrom());
			pstmt.setString(3, entity.getCurrencyCodeTo());;
			pstmt.setFloat(4, entity.getAmount());
			pstmt.setTimestamp(5, entity.getCreated());
			pstmt.setFloat(6, entity.getResult());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "tranzaction"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert tranzaction entity", e);
		}

	}

	@Override
	public void update(Transaction entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update tranzaction set client_id=?, currency_code_from=?,  currency_code_to=?, amount=?, result=? where id=?");
			pstmt.setInt(1, entity.getClientId());
			pstmt.setString(2, entity.getCurrencyCodeFrom());
			pstmt.setString(3, entity.getCurrencyCodeTo());
			pstmt.setFloat(4, entity.getAmount());
			pstmt.setFloat(5, entity.getResult());
			pstmt.setInt(6, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update tranzaction entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from tranzaction where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete tranzaction entity", e);
		}
	}

	@Override
	public Transaction getById(Integer id) {
		Transaction entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from tranzaction where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get tranzaction entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Transaction> getAll() {
		List<Transaction> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from tranzaction");
			while (rs.next()) {
				Transaction entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select tranzaction entities", e);
		}

		return entitiesList;
	}

	private Transaction rowToEntity(ResultSet rs) throws SQLException {
		Transaction entity = new Transaction();
		entity.setId(rs.getInt("id"));
		entity.setClientId(rs.getInt("client_id"));
		entity.setCurrencyCodeFrom(rs.getString("currency_code_from"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field is NULL in DB
		entity.setCurrencyCodeTo(rs.getString("currency_code_to"));
		entity.setAmount(rs.getFloat("amount"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setResult(rs.getFloat("result"));
		return entity;
	}
	

	@Override
	public List<Transaction> find(TableStateDto tableStateDto) {
		List<Transaction> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from tranzaction");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Transactions using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Transaction entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Transaction entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from tranzaction");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get tranzactions count", e);
		}
	}
}