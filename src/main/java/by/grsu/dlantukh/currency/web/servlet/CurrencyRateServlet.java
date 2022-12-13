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
import by.grsu.dlantukh.currency.web.dto.CurrencyRateDto;

public class CurrencyRateServlet extends HttpServlet {
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
		List<CurrencyRate> currencyRates = currencyRateDao.getAll(); // get data

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
		req.getRequestDispatcher("currency.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String idFrom = req.getParameter("idFrom");
		String idTo = req.getParameter("idTo");// /currency?idFrom=usd&idTo=byn
		CurrencyRateDto dto = new CurrencyRateDto();
		if (!Strings.isNullOrEmpty(idFrom) &&!Strings.isNullOrEmpty(idTo)) {
			// object edit
			CurrencyRate entity = currencyRateDao.getById(idFrom, idTo);
			dto.setCurrencyFromCode(entity.getCurrencyFromCode());
			dto.setCurrencyToCode(entity.getCurrencyToCode());
			dto.setValuePurchase(entity.getValuePurchase());
			dto.setValuePokypka(entity.getValuePokypka());

		}
		req.setAttribute("dto", dto);
		req.setAttribute("allCurrencyRates", getAllCurrencyesDtos());
		req.getRequestDispatcher("Change_Course.jsp").forward(req, res);
	}

	private List<CurrencyRateDto> getAllCurrencyRatesDtos() {
		return currencyRateDao.getAll().stream().map((entity) -> {
			CurrencyRateDto dto = new CurrencyRateDto();
			dto.setCurrencyFromCode(entity.getCurrencyFromCode());
			dto.setCurrencyToCode(entity.getCurrencyToCode());
			dto.setValuePurchase(entity.getValuePurchase());
			dto.setValuePokypka(entity.getValuePokypka());
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
		CurrencyRate currencyRate = new CurrencyRate();
		String idFrom = req.getParameter("idFrom");
		String idTo = req.getParameter("idTo");
		String valuePurchaseStr = req.getParameter("valuePurchase");
		String valuePokypkaStr = req.getParameter("valuePokypka");

		currencyRate.setCurrencyFromCode(idFrom == null ? null : idFrom);
		currencyRate.setCurrencyToCode(idFrom == null ? null : idTo);
		currencyRate.setValuePurchase(Strings.isNullOrEmpty(valuePurchaseStr) ? null : Float.parseFloat(valuePurchaseStr));
		currencyRate.setValuePokypka(Strings.isNullOrEmpty(valuePokypkaStr) ? null : Float.parseFloat(valuePokypkaStr));
		if (Strings.isNullOrEmpty(idFrom) &&!Strings.isNullOrEmpty(idTo)) {
			currencyRateDao.insert(currencyRate);
		} else {
			currencyRateDao.update(currencyRate);
		}
		res.sendRedirect("/currency"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		currencyRateDao.delete(Integer.parseInt(req.getParameter("id")));
	}

}

