package com.fullcreative.login;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@RequestMapping("/register")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// String userID = request.getParameter("userID");

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String mailID = request.getParameter("mailID");
		String dOB = request.getParameter("dob");
		String mobileNo = request.getParameter("mobileNo");

		PersistenceManager pm = PMF.get().getPersistenceManager();

		User addUser = new User(userName, password, mailID, dOB, mobileNo);

		try {

			pm.currentTransaction().begin();
			pm.makePersistent(addUser);

			pm.currentTransaction().commit();

		} finally {

			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}

			
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		String display = "Registered Successfully! You can Login now";

		request.setAttribute("message", display);

		rd.include(request, response);


		
	}

}
