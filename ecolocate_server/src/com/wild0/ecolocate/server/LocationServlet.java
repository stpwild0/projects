package com.wild0.ecolocate.server;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LocationServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		String postedXml = req.getParameter("content");
		LocationParser xmlParser = new LocationParser(postedXml);
		
		Location location = xmlParser.parse();
		
		resp.setContentType("text/plain");
		
		Writer writer;
		
		try {
			writer = resp.getWriter();
			writer.write(location.toString());
		} catch (IOException e) {
			e.printStackTrace();
			resp.setStatus(500);
			return;
		}
	}
}
