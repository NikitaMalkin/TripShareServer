package post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import tripShareObjects.Post;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        try 
        {  	
        	// get the user id from the request
        	int userID = Integer.parseInt(request.getParameter("m_userID"));
        	// read the routes from the DB        
        	 List<Post> postListToSend = em.createQuery(
                     "SELECT p FROM Post p WHERE p.m_userID = :userID", Post.class).setParameter("userID", userID).getResultList();
    		String postListInJson = gson.toJson(postListToSend);
    		PrintWriter out = response.getWriter();
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		out.print(postListInJson);
    		out.flush();
        }
        catch (Exception e) 
    	{
			response.sendError(404);
		}
        finally 
        {
            // Close the database connection:
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
        // Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
    	try 
    	{
    		// read the object from the request
    		BufferedReader reader = request.getReader();
    		Post m_PostToAddToDB = new Gson().fromJson(reader, Post.class);
            
    		// insert the object into the DB
    		em.getTransaction().begin();
    		em.persist(m_PostToAddToDB);
    		em.getTransaction().commit();        
    		
    	}  
    	catch (Exception e) 
    	{
			response.sendError(404);
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
	        	long postID = Long.parseLong(request.getParameter("m_postID"));
	        	
	        	Post postToDelete = em.find(Post.class, postID);

	        	  em.getTransaction().begin();
	        	  em.remove(postToDelete);
	        	  em.getTransaction().commit();
	    	}
	    	catch(Exception e)
	    	{
				response.sendError(404);	
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