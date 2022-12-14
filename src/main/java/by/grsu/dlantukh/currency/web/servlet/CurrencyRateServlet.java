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
import by.grsu.dlantukh.currency.db.dao.impl.CurrencyDaoImpl;
import by.grsu.dlantukh.currency.db.model.Currency;
import by.grsu.dlantukh.currency.web.dto.CurrencyDto;
import by.grsu.dlantukh.currency.db.dao.impl.CurrencyRateDaoImpl;
import by.grsu.dlantukh.currency.db.model.CurrencyRate;
import by.grsu.dlantukh.currency.db.model.Transaction;
import by.grsu.dlantukh.currency.web.dto.CurrencyRateDto;
import by.grsu.dlantukh.currency.web.dto.TableStateDto;

public class CurrencyRateServlet extends AbstractListServlet {
	private static final IDao<String, Currency> currencyDao = CurrencyDaoImpl.INSTANCE;
	private static final CurrencyRateDaoImpl currencyRateDao = CurrencyRateDaoImpl.INSTANCE;

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
		int totalCurrencyRates = currencyRateDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalCurrencyRates); // init TableStateDto for
																							// specific
		// Servlet and saves it in current
		// request using key
		// "currentPageTableState" to be
		// used by 'paging' component

		List<CurrencyRate> currencyRates = currencyRateDao.find(tableStateDto); // get data using paging and sorting
																				// params

		List<CurrencyRateDto> dtos = currencyRates.stream().map((entity) -> {
			CurrencyRateDto dto = new CurrencyRateDto();
			// copy necessary fields as-is
			dto.setCurrencyFromCode(entity.getCurrencyFromCode());
			dto.setCurrencyToCode(entity.getCurrencyToCode());
			dto.setValuePurchase(entity.getValuePurchase());
			dto.setValuePokypka(entity.getValuePokypka());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("currency Rate.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String idFrom = req.getParameter("idFrom");
		String idTo = req.getParameter("idTo");// /currency?idFrom=usd&idTo=byn
		CurrencyRateDto dto = new CurrencyRateDto();
		if (!Strings.isNullOrEmpty(idFrom) && !Strings.isNullOrEmpty(idTo)) {
			// object edit
			CurrencyRate entity = currencyRateDao.getById(idFrom, idTo);
			dto.setCurrencyFromCode(entity.getCurrencyFromCode());
			dto.setCurrencyToCode(entity.getCurrencyToCode());
			dto.setValuePurchase(entity.getValuePurchase());
			dto.setValuePokypka(entity.getValuePokypka());

		}
		req.setAttribute("dto", dto);
		req.setAttribute("allCurrencyes", getAllCurrencyesDtos());
		req.getRequestDispatcher("edit currency Rate.jsp").forward(req, res);
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
		CurrencyRate currencyRate = new CurrencyRate();
		String idFrom = req.getParameter("currencyFromCode");
		String idTo = req.getParameter("currencyToCode");
		String valuePurchaseStr = req.getParameter("valuePurchase");
		String valuePokypkaStr = req.getParameter("valuePokypka");

		currencyRate.setCurrencyFromCode(idFrom);
		currencyRate.setCurrencyToCode(idTo);
		currencyRate.setValuePurchase(Strings.isNullOrEmpty(valuePurchaseStr) ? null : Float.parseFloat(valuePurchaseStr));
		currencyRate.setValuePokypka(Strings.isNullOrEmpty(valuePokypkaStr) ? null : Float.parseFloat(valuePokypkaStr));
		if (currencyRateDao.getById(idFrom, idTo)==null) {
			currencyRateDao.insert(currencyRate);
		} else {
			currencyRateDao.update(currencyRate);
		}
		res.sendRedirect("/currencyRate"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		currencyRateDao.delete(req.getParameter("idFrom"), req.getParameter("idTo"));
	}

}
