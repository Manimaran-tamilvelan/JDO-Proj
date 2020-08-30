package com.fullcreative.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Servlet implementation class Delete
 */
@Controller
public class Delete extends HttpServlet {

	@RequestMapping("/delete")
	protected ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userNameFromUser = request.getParameter("userID");
		String passwordFromUser = request.getParameter("password");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query deleteQuery = pm.newQuery(User.class, "userName == '" + userNameFromUser + "' & password == '" + passwordFromUser + "'");

		deleteQuery.deletePersistentAll();

		Query q = pm.newQuery(User.class);

		String user = q.execute().toString();

		String[] splitEqualSym = user.split("=");

		int size = 0;
		for (String a : splitEqualSym) {
			// System.out.println(a);
			size++;
		}

		Map<String, List<String>> finalUsers = new LinkedHashMap();

		for (int i = 1; i < size; i += 5) {
			String[] splitUserName = splitEqualSym[i + 0].split(",");
			String[] splitPassword = splitEqualSym[i + 1].split(",");
			String[] splitMailID = splitEqualSym[i + 2].split(",");
			String[] splitdOB = splitEqualSym[i + 3].split(",");
			String[] splitMobileNo = splitEqualSym[i + 4].split("]");

			String userName = splitUserName[0];
			String password = splitPassword[0];
			String mailID = splitMailID[0];
			String dOB = splitdOB[0];
			String mobileNo = splitMobileNo[0];

			List<String> temp = new ArrayList();

			temp.add(password);
			temp.add(password);
			temp.add(mailID);
			temp.add(mobileNo);

			finalUsers.put(userName, temp);

		}

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("adminResult.jsp");
		modelView.addObject("allUsers", finalUsers);
		return modelView;

	}

}
