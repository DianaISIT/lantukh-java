package by.grsu.dlantukh.currency.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.dlantukh.currency.db.dao.IDao;
import by.grsu.dlantukh.currency.db.dao.impl.ClientDaoImpl;
import by.grsu.dlantukh.currency.db.dao.impl.CurrencyDaoImpl;
import by.grsu.dlantukh.currency.db.dao.impl.TransactionDaoImpl;
import by.grsu.dlantukh.currency.db.model.Client;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.db.model.Transaction;
import by.grsu.dlantukh.currency.web.dto.ClientDto;
import by.grsu.dlantukh.currency.web.dto.CurrencyDto;
import by.grsu.dlantukh.currency.web.dto.TableStateDto;
import by.grsu.dlantukh.currency.web.dto.TransactionDto;

public class TransactionServlet extends AbstractListServlet {
	private static final IDao<Integer, Transaction> transactionDao = TransactionDaoImpl.INSTANCE;
	private static final IDao<Integer, Client> clientDao = ClientDaoImpl.INSTANCE;
	private static final IDao<String, Currency> currencyDao = CurrencyDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int totalTransactions = transactionDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalTransactions); // init TableStateDto for
																							// specific
		// Servlet and saves it in current
		// request using key
		// "currentPageTableState" to be
		// used by 'paging' component

		List<Transaction> transactions = transactionDao.find(tableStateDto); // get data using paging and sorting params

		List<TransactionDto> dtos = transactions.stream().map((entity) -> {
			TransactionDto dto = new TransactionDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setCreated(entity.getCreated());
			dto.setResult(entity.getResult());
			dto.setAmount(entity.getAmount());
			dto.setCurrencyCodeFrom(entity.getCurrencyCodeFrom());
			dto.setCurrencyCodeTo(entity.getCurrencyCodeTo());

			// build data for complex fields
			Client client = clientDao.getById(entity.getClientId());
			dto.setClientFirstName(client.getFirstName());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("transaction.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String TransactionIdStr = req.getParameter("id");
		TransactionDto dto = new TransactionDto();
		if (!Strings.isNullOrEmpty(TransactionIdStr)) {
			// object edit
			Integer TransactionId = Integer.parseInt(TransactionIdStr);
			Transaction entity = transactionDao.getById(TransactionId);
			dto.setId(entity.getId());
			dto.setClientId(entity.getClientId());
			dto.setCreated(entity.getCreated());
			dto.setResult(entity.getResult());
			dto.setAmount(entity.getAmount());
			dto.setCurrencyCodeFrom(entity.getCurrencyCodeFrom());
			dto.setCurrencyCodeTo(entity.getCurrencyCodeTo());

		}
		req.setAttribute("dto", dto);
		req.setAttribute("allClients", getAllClientsDtos());
		req.setAttribute("allCurrencyes", getAllCurrencyesDtos());
		req.getRequestDispatcher("edit transaction.jsp").forward(req, res);
	}

	private List<ClientDto> getAllClientsDtos() {
		return clientDao.getAll().stream().map((entity) -> {
			ClientDto dto = new ClientDto();
			dto.setId(entity.getId());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setPatronymic(entity.getPatronymic());
			dto.setPassport(entity.getPassport());
			return dto;
		}).collect(Collectors.toList());
	}

	private List<CurrencyDto> getAllCurrencyesDtos() {
		return currencyDao.getAll().stream().map((entity) -> {
			CurrencyDto dto = new CurrencyDto();
			dto.setCode(entity.getCode());
			dto.setName(entity.getName());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Transaction transaction = new Transaction();
		String transactionIdStr = req.getParameter("id");
		String clientIdStr = req.getParameter("clientId");
		String currencyCodeFromStr = req.getParameter("currencyCodeFrom");
		String currencyCodeToStr = req.getParameter("currencyCodeTo");
		String resultStr = req.getParameter("result");
		String amountStr = req.getParameter("amount");

		transaction.setClientId(clientIdStr == null ? null : Integer.parseInt(clientIdStr));
		transaction.setCurrencyCodeFrom(currencyCodeFromStr);
		transaction.setCurrencyCodeTo(currencyCodeToStr);
		transaction.setAmount(Strings.isNullOrEmpty(amountStr) ? null : Float.parseFloat(amountStr));
		transaction.setCreated(new Timestamp(new Date().getTime()));
		transaction.setResult(Strings.isNullOrEmpty(resultStr) ? null : Float.parseFloat(resultStr));
		if (Strings.isNullOrEmpty(transactionIdStr)) {
			transaction.setCreated(new Timestamp(new Date().getTime()));
			transactionDao.insert(transaction);
		} else {
			transaction.setId(Integer.parseInt(transactionIdStr));
			transactionDao.update(transaction);
		}
		res.sendRedirect("/transaction"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		transactionDao.delete(Integer.parseInt(req.getParameter("id")));
	}

}
