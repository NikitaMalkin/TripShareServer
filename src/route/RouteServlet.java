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

import tripShareObjects.Route;

@WebServlet("/RouteServlet")
public class RouteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {   
    	 // Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        try 
        {  	
        	// get the user id from the request
        	Long userID = Long.parseLong(request.getParameter("m_userID"));
        	// read the routes from the DB        
        	 List<Route> routeListToSend = em.createQuery(
                     "SELECT r FROM Route r WHERE r.m_userID = :userID", Route.class).setParameter("userID", userID).getResultList();
    		String routeListInJson = gson.toJson(routeListToSend);
    		PrintWriter out = response.getWriter();
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		out.print(routeListInJson);
    		out.flush();
        }
        catch (Exception e) 
    	{
			response.sendError(500, e.getMessage());
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
    		Route routeToAddToDB = gson.fromJson(reader, Route.class);
            
    		// insert the object into the DB
    		em.getTransaction().begin();
    		em.persist(routeToAddToDB);
    		em.getTransaction().commit();        
    		
    		// set the response with the route generated ID
    		PrintWriter writer = response.getWriter();
    		String routeIDToSendBack = String.valueOf(routeToAddToDB.getRouteID());
    		writer.print(routeIDToSendBack);		
    	}  
    	catch (Exception e) 
    	{
			response.sendError(500, e.toString());
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	 // Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
    	try
    	{
        	// get the route id from the request
        	long routeID = Long.parseLong(request.getParameter("m_routeID"));
        	
        	Route routeToDelete = em.find(Route.class, routeID);

        	  em.getTransaction().begin();
        	  em.remove(routeToDelete);
        	  em.getTransaction().commit();
    	}
    	catch(Exception e)
    	{
			response.sendError(500, e.toString());
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