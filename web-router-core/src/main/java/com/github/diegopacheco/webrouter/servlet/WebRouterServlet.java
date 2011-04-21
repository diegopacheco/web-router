package com.github.diegopacheco.webrouter.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.diegopacheco.webrouter.base.WebRouter;
import com.github.diegopacheco.webrouter.dsl.Resource;

/**
 * 
 * @author Diego Pacheco
 * @since 20/04/2011
 * @version 1.0
 *
 */
public class WebRouterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String,Resource> routes;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			String routerClass  =  config.getInitParameter("router");
			WebRouter webRouter =  (WebRouter)Class.forName(routerClass).newInstance();
			routes = webRouter.define().getRoutes();			
		} catch (Exception e){
		   e.printStackTrace();
		}	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		System.out.println(routes);
		System.out.println(req);
		System.out.println(resp);
		super.doGet(req, resp);
	}
	
}
