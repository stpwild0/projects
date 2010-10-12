package com.wild0.ecolocate.server;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LocationServlet extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			/*
			 * UserService userService = UserServiceFactory.getUserService();
	        User user = userService.getCurrentUser();
			 */
			String postedXml = req.getParameter("content");
			LocationParser xmlParser = new LocationParser(postedXml);
			Location newLocation = xmlParser.parse();
			
			String username = "woo".toLowerCase();
			
			Writer writer = resp.getWriter();
			
			Location oldLocation = null;
			
			String oldLoc = "";
			
			resp.setContentType("text/plain");
			
			User user = null;
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
	        try {
	        	Query query = pm.newQuery("select from " + User.class.getName() +
	        			" where username == usernameParam");
	        	query.declareParameters("String usernameParam");
	        	
	        	List<User> results = (List<User>) query.execute(username);
	        	ArrayList<User> users = new ArrayList(results);
	        	
	        	if (users.size() < 0)
	        	{
	        		
	        	}
	        	else if (users.size() == 0)
	        	{
	        		user = new User(username);
	        		pm.makePersistent(user);
	        	}
	        	else if (users.size() > 0)
	        	{
	        		user = users.get(0);
	        		oldLocation = user.getLocation();
	        		oldLoc = oldLocation.toString();
	        	}
	        	
	        	Transaction tx = pm.currentTransaction();
	        	
	        	tx.begin();
	        	oldLocation.updateLocation(newLocation);
	        	tx.commit();
	        	
	        }
	        finally {
	        	if (pm.currentTransaction().isActive())
	        		pm.currentTransaction().rollback();
	        	
	            pm.close();
	        }
	        
	        
	        String writeString = "welcome new user!";
	        if (oldLocation != null)
	        	writeString = oldLoc;
	        
			writer.write(writeString);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			resp.setStatus(500);
			return;
		}
	}
}
