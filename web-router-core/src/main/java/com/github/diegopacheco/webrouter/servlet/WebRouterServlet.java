package com.github.diegopacheco.webrouter.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.diegopacheco.webrouter.base.WebRouter;
import com.github.diegopacheco.webrouter.dsl.Resource;
import com.github.diegopacheco.webrouter.dsl.Router;

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
			super.init(config);
		} catch (Exception e){
		   e.printStackTrace();
		}	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resolveRoute(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resolveRoute(req, resp);
	}

	private void resolveRoute(HttpServletRequest req, HttpServletResponse resp)	throws IOException, ServletException {
		String uri =  "/" + extractUri(req);
		if(routes.containsKey(uri)){
			route(uri,req,resp);
		}else if ("/routes".equals(uri)){
			routesDefinition(resp);			
		}else{
			super.doGet(req, resp);
		}
	}

	private void route(String uri, HttpServletRequest req,HttpServletResponse resp) {
		Resource r = routes.get(uri);
		switch(r.getType()){			
			case SERVLET:
				routeToServlet(uri,r,req,resp);
			break;
			case JSP:
				routeToJsp(uri,r,req,resp);
			break;
			case STATIC:
			break;
		}
	}

	private void routeToServlet(String uri,Resource r, HttpServletRequest req,HttpServletResponse resp) {
		try {			
			 RequestDispatcher dispatcher = getServletConfig().getServletContext().getNamedDispatcher(r.getName());
			 dispatcher.forward( req, resp );			 
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void routeToJsp(String uri,Resource r, HttpServletRequest req,HttpServletResponse resp) {
		try {			
//			 RequestDispatcher dispatcher = req.getRequestDispatcher(r.getName());
//			 dispatcher.forward( req, resp );	
			 resp.sendRedirect(req.getContextPath() + r.getName());
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private void routesDefinition(HttpServletResponse resp) throws IOException {
		String content = Router.fromMapStructureToString(routes);
		content = content.replace("\n", "<BR>"); 
		resp.setContentType("text/html");
		resp.getOutputStream().write( "<h2>Avaliable Routes</h2><BR>".getBytes() );
		resp.getOutputStream().write( content.getBytes() );
	}
	
	private String extractUri(HttpServletRequest req){
		String base = req.getRequestURI();
		String uri  = base.replace(req.getContextPath() + "/", "");
		return uri;
	}
	
}
