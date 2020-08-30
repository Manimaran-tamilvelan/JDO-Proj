package com.fullcreative.login;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class update extends HttpServlet {

	@RequestMapping("/update")
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String mailID = request.getParameter("mailID");
		String dOB = request.getParameter("dob");
		String mobileNo = request.getParameter("mobileNo");

		HttpSession session = request.getSession();

		try {
			if (session.getAttribute("userNameModify") != null) {
				
				
				//update
				//"update User set mailID='mailID'. dOB='dOB' ,mobileNo='mobileNo' where userName=='userName' & password=='password'; 
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				
				Query deleteQuery = pm.newQuery(User.class, "userName == '" + userName + "' & password == '" + password + "'");

				deleteQuery.deletePersistentAll();
				
				User update = new User(userName, password, mailID, dOB, mobileNo);
				
				pm.makePersistent(update);

				session.removeAttribute("userNameModify");

				session.invalidate();

			}

		}finally {
			
		}

		
		
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		String display = "Updated Successfully! You can Login now";

		request.setAttribute("message", display);

		rd.include(request, response);

		
	}

}
