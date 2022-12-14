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
import by.grsu.dlantukh.currency.db.dao.impl.ClientDaoImpl;
import by.grsu.dlantukh.currency.db.model.Client;
import by.grsu.dlantukh.currency.web.dto.ClientDto;
import by.grsu.dlantukh.currency.web.dto.TableStateDto;

public class ClientServlet extends AbstractListServlet {
	private static final IDao<Integer, Client> clientDao = ClientDaoImpl.INSTANCE;

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
		int totalClients = clientDao.count(); // get count of ALL items

		final TableStateDto tableStateDto = resolveTableStateDto(req, totalClients); // init TableStateDto for specific
																						// Servlet and saves it in
																						// current
																						// request using key
																						// "currentPageTableState" to be
																						// used by 'paging' component

		List<Client> clients = clientDao.find(tableStateDto); // get data using paging and sorting params

		List<ClientDto> dtos = clients.stream().map((entity) -> {
			ClientDto dto = new ClientDto();
			dto.setId(entity.getId());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setPatronymic(entity.getPatronymic());
			dto.setPassport(entity.getPassport());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("client.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ClientIdStr = req.getParameter("id");
		ClientDto dto = new ClientDto();
		if (!Strings.isNullOrEmpty(ClientIdStr)) {
			Integer clientId = Integer.parseInt(ClientIdStr);
			Client entity = clientDao.getById(clientId);
			dto.setId(entity.getId());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setPatronymic(entity.getPatronymic());
			dto.setPassport(entity.getPassport());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("edit client.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Client client = new Client();
		String clientIdStr = req.getParameter("id");
		client.setFirstName(req.getParameter("firstName"));
		client.setLastName(req.getParameter("lastName"));
		client.setPatronymic(req.getParameter("patronymic"));
		client.setPassport(req.getParameter("passport"));

		if (Strings.isNullOrEmpty(clientIdStr)) {
			clientDao.insert(client);
		} else {
			client.setId(Integer.parseInt(clientIdStr));
			clientDao.update(client);
		}

		res.sendRedirect("/client");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		clientDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
