package by.grsu.dlantukh.currency.web.servlet;

import java.io.IOException;
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
import by.grsu.dlantukh.currency.web.dto.TableStateDto;

public class CurrencyServlet extends AbstractListServlet {
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

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int totalCurrencyes = currencyDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalCurrencyes); // init TableStateDto for specific
																					// Servlet and saves it in current
																					// request using key
																					// "currentPageTableState" to be
																					// used by 'paging' component

		List<Currency> currency = currencyDao.find(tableStateDto); // get data using paging and sorting params


		List<CurrencyDto> dtos = currency.stream().map((entity) -> {
			CurrencyDto dto = new CurrencyDto();
			dto.setCode(entity.getCode());
			dto.setName(entity.getName());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("currency.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String CurrencyCodeStr = req.getParameter("code");
		CurrencyDto dto = new CurrencyDto();
		if (!Strings.isNullOrEmpty(CurrencyCodeStr)) {
			String currencyCode = CurrencyCodeStr.toString();
			Currency entity = currencyDao.getById(currencyCode);
			dto.setCode(entity.getCode());
			dto.setName(entity.getName());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("edit currency.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Currency currency = new Currency();
		String currencyCodeStr = req.getParameter("code");
		currency.setCode(req.getParameter("code"));
		currency.setName(req.getParameter("name"));
		

		if (Strings.isNullOrEmpty(currencyCodeStr)) {
			currencyDao.insert(currency);
		} else {
			currency.setCode(currencyCodeStr.toString());
			currencyDao.update(currency);
		}

		res.sendRedirect("/currency");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		currencyDao.delete(req.getParameter("code").toString());
	}
}
