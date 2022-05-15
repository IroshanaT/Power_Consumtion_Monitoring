package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet implementation class MonitorAPI */
@WebServlet("/MonitorAPI")

public class MonitorAPI extends HttpServlet {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Monitor userObj =new Monitor();
       
    
    public MonitorAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = userObj.insertPowerPlant(request.getParameter("areaId"), 
				request.getParameter("pName"), 
				request.getParameter("pLocation"), 
				request.getParameter("type"),
				request.getParameter("status"),
				request.getParameter("capacity"));
				response.getWriter().write(output); 
				
				
	}

	

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request); 
		 String output = userObj.updatePowerPlant(paras.get("hidpowerpIdSave").toString(), 
		 paras.get("areaId").toString(), 
		 paras.get("pName").toString(), 
		paras.get("pLocation").toString(), 
		paras.get("type").toString(),
		paras.get("status").toString(),
		paras.get("capacity").toString());
		response.getWriter().write(output); 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = userObj.deletePowerPlant(paras.get("powerpId").toString()); 
		response.getWriter().write(output);
	}
	

	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
}
