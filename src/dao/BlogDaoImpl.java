package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface
{	
	@Override
	public void insertBlog(Blog blog) throws Exception
	{
	int id = blog.getBlogId();
	String title = blog.getBlogTitle();
	String desc = blog.getBlogDescription();
	LocalDate date = blog.getPostedOn();
	
	System.out.println("Enter details to insert into the blog:");
	String sql = "INSERT INTO BLOG(ID, TITLE, DESCRIPTION, DATE1) VALUES (?,?,?,?)";
	System.out.println("TABLE BLOG EXISTS");
	PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
	st.setInt(1, id);
	st.setString(2, title);
	st.setString(3, desc);
	st.setDate(4, java.sql.Date.valueOf(date));
	st.executeQuery();
	ConnectionManager.getConnection().close();
}

	@Override
	public List<Blog> selectAllBlogs() throws SQLException, Exception 
	{	
	System.out.println("your Blogs are listed below:");
	Statement st = ConnectionManager.getConnection().createStatement();
	ResultSet rs = st.executeQuery("SELECT * from BLOG");
	List<Blog> list = new ArrayList<Blog>();
	
	while(rs.next())
	{
		
		int id = rs.getInt("ID");
		String title = rs.getString("TITLE");
		String desc = rs.getString("DESCRIPTION");
		Date date = rs.getDate("DATE1"); 
		
		Blog blog = new Blog();
		blog.setBlogId(id);
		blog.setBlogTitle(title);
		blog.setBlogDescription(desc);
		
		LocalDate date1 = ((java.sql.Date) date).toLocalDate();
		blog.setPostedOn(date1);
		list.add(blog);
	}
	System.out.println(list);
	return list;
	}

@Override
public Blog selectBlog(int blogid) throws SQLException, Exception {
	Blog blog = null;
		Statement stmt = ConnectionManager.getConnection().createStatement();
		String sql = "SELECT * FROM BLOG WHERE BLOGID = ?";
		ResultSet rs = stmt.executeQuery(sql);
		blog = new Blog();
		while(rs.next()) {
		blog.setBlogId(rs.getInt(1));
		blog.setBlogTitle(rs.getString(2));
		blog.setBlogDescription(rs.getString(3));
		blog.setPostedOn(LocalDate.now());
	}
	return blog;
}

@Override
public boolean deleteBlog(int id) throws Exception {
	int i = -1;
		Statement stmt = ConnectionManager.getConnection().createStatement();
		String sql = "DELETE FROM BLOG WHERE BLOGID = ?";
		i = stmt.executeUpdate(sql);
		if (i > 0)
			return true;
		else
		return false;
}

@Override
public boolean updateBlog(Blog blog) throws SQLException, Exception {
	System.out.println("UPDATINGS");
	int i = -1;
	if (ConnectionManager.getConnection() != null) {
		Statement stmt = ConnectionManager.getConnection().createStatement();
		String sql = "UPDATE BLOG SET BLOGTITLE = ? , blogDiscription = ?, date = ?";
		i = stmt.executeUpdate(sql);
	}
	if (i > 0)
		return true;
	else
		return false;
}
}