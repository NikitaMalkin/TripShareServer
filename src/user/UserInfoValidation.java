package user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

@WebServlet("/UserInfoValidation")
public class UserInfoValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
		String userName;
		byte[] password; 
		User user = null;
		boolean isValid = false;
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		userName = new String(request.getParameter("m_userName"));
		password = new String(request.getParameter("m_password")).getBytes();
		
		try
		{
			user = em.createQuery("SELECT u from User WHERE u.m_userName = :userName", User.class).setParameter("userID", userName).getSingleResult();
			if(user.getPassword().getBytes().equals(password))
				isValid = true;
		}
		catch (NoResultException e) 
		{
			isValid = false;
		}
		
		out.print(isValid);
		out.flush();
	}
}

