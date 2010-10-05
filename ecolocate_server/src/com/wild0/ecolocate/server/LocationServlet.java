package com.wild0.ecolocate.server;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.servlet.http.*;

import sun.security.util.Debug;

@SuppressWarnings("serial")
public class LocationServlet extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		/*
		 * UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
		 */
		String postedXml = req.getParameter("content");
		LocationParser xmlParser = new LocationParser(postedXml);
		String username = "woo".toLowerCase();
		
		Writer writer = null;
		try {
			writer = resp.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Location newLocation = xmlParser.parse();
		Location oldLocation = null;
		
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
        	if (users.size() == 0)
        	{
        		user = new User(username);
        		pm.makePersistent(user);
        	}
        	if (users.size() > 0)
        	{
        		user = users.get(0);
        		oldLocation = user.getLocation();
        	}
        	
        	Transaction tx = pm.currentTransaction();
        	
        	tx.begin();
        	user.setLocation(new Location(50.0, 50.0, new Date()));
        	tx.commit();
        	try {
				writer.write("update successful");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        } finally {
        	if (pm.currentTransaction().isActive())
        		pm.currentTransaction().rollback();
        	
            pm.close();
        }
        
        
        String writeString = "welcome new user!";
        if (oldLocation != null)
        	writeString = oldLocation.toString();
		
		try {
			
			writer.write(writeString);
		} catch (IOException e) {
			e.printStackTrace();
			resp.setStatus(500);
			return;
		}
	}
}
