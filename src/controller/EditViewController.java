package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BlogDaoImpl;
import model.Blog;




@WebServlet(urlPatterns = {"/edit"})
public class EditViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
  		try {
			showEditForm(request,response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		BlogDaoImpl blogDAO = new BlogDaoImpl();
		int id = Integer.parseInt(request.getParameter("id"));
		Blog existingBlog = null;
		try {
			existingBlog = blogDAO.selectBlog(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/blogListView.jsp");
		request.setAttribute("bloglist", existingBlog);
		dispatcher.forward(request, response);

	}
	
}
