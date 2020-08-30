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
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login extends HttpServlet {

	@RequestMapping("/login")
	protected ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userNamefromUser = request.getParameter("username");
		String passwordfromUser = request.getParameter("password");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		ModelAndView modelView = new ModelAndView();

		if (userNamefromUser.equals("admin") && passwordfromUser.equals("admin")) {

			// System.out.println(u1);
			pm.currentTransaction().begin();
			// System.out.println(pm.getObjectById("User"));
			// User u1 = (User) pm.getObjectById(User.class);
			// System.out.println(u1);
			// pm.makePersistent(u1);
			

			HttpSession sess = request.getSession();
			sess.setAttribute("userName", userNamefromUser);

			Query q = pm.newQuery(User.class);
			String user = q.execute().toString();

			if (user.equals("[]")) {
				System.out.println("Empty");
			}
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
				temp.add(mailID);
				temp.add(mobileNo);
				temp.add(dOB);

				finalUsers.put(userName, temp);

			}
			
			modelView.setViewName("adminResult.jsp");
			modelView.addObject("allUsers", finalUsers);
			return modelView;

		}

		try {

			Query q = pm.newQuery(User.class,
					"userName == '" + userNamefromUser + "' & password == '" + passwordfromUser + "'");
			String user = q.execute().toString();

			if (user.equals("[]")) {
				throw new IllegalArgumentException();
			}

			String[] splitEqualSym = user.split("=");
			String[] splitUserName = splitEqualSym[1].split(",");
			String[] splitPassword = splitEqualSym[2].split(",");
			String[] splitMailID = splitEqualSym[3].split(",");
			String[] splitdOB = splitEqualSym[4].split(",");
			String[] splitMobileNo = splitEqualSym[5].split("]");

			String userName = splitUserName[0];
			String password = splitPassword[0];
			String mailID = splitMailID[0];
			String dOB = splitdOB[0];
			String mobileNo = splitMobileNo[0];

			List<String> finalAuthUser = new ArrayList();

			finalAuthUser.add(userName);
			finalAuthUser.add(password);
			finalAuthUser.add(mailID);
			finalAuthUser.add(dOB);
			finalAuthUser.add(mobileNo);

			HttpSession sess = request.getSession();
			sess.setAttribute("userName", userName);

			modelView.setViewName("welcome.jsp");
			modelView.addObject("showUserDetail", finalAuthUser);
			return modelView;

		} catch (IllegalArgumentException e1) {
			modelView.setViewName("login.jsp");
			String errorMessage = "Incorrect Details";
			modelView.addObject("message", errorMessage);
		}

		return modelView;

	}
}
