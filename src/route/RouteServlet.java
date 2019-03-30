package route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.persistence.*;
import com.google.gson.*;

@WebServlet("/RouteServlet")
public class RouteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {    	
    	// Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        try 
        {  	
        	// get the user id from the request
        	int userID = Integer.parseInt(req.getParameter("m_userID"));
        	// read the routes from the DB        
        	 List<Route> routeListToSend = em.createQuery(
                     "SELECT r FROM Route r WHERE r.m_userID = :userID", Route.class).setParameter("userID", userID).getResultList();
    		String routeListInJson = gson.toJson(routeListToSend);
    		PrintWriter out = resp.getWriter();
    		resp.setContentType("application/json");
    		resp.setCharacterEncoding("UTF-8");
    		out.print(routeListInJson);
    		out.flush();
        }
        finally 
        {
            // Close the database connection:
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }
    
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
    	try 
    	{
    		// read the object from the request
    		BufferedReader reader = request.getReader();
    		Route m_RouteToAddToDB = gson.fromJson(reader, Route.class);
            
    		// insert the object into the DB
    		em.getTransaction().begin();
    		em.persist(m_RouteToAddToDB);
    		em.getTransaction().commit();        
    		
    	}  
        finally 
        {
            // Close the database connection:
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }
}