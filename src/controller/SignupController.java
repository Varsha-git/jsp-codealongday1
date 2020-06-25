package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		LocalDate date = LocalDate.now();

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		UserDAO userdao = new UserDAO();
		user.setDate(date);
		int checkUser = 0;
		try {
			checkUser = userdao.signUp(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("checkUser" + checkUser);

		if (checkUser != 0) {
			System.out.println(user.getEmail());
			System.out.println(user.getPassword());
			System.out.println(user.getDate());
			request.setAttribute("message", "Registration Succesful");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("message", "Check your credentials");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
			rd.forward(request, response);
		}

	}

}
