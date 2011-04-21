package com.github.diegopacheco.webrouter.dsl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * NOT THREAD SAFE YET!
 * 
 * 
 * @author Diego Pacheco
 * @since 20/04/2011
 * @version 1.0
 *
 */
public class Router {
	
	private Map<String,Resource> routes = new HashMap<String,Resource>();
	private String last;
	
	public static Router route(){
		return new Router();
	}
	
	public Router from(String from){
		Resource r = new Resource(from,ResourceType.STATIC); 
		addFrom(r);
		return this;
	}
	
	public Router fromJsp(String from){
		Resource r = new Resource(from,ResourceType.JSP); 
		addFrom(r);
		return this;
	}
	
	public Router fromServlet(String from){
		Resource r = new Resource(from,ResourceType.SERVLET); 
		addFrom(r);
		return this;
	}	
	
	public Router to(String to){
		Resource r = new Resource(to,ResourceType.STATIC);
		addTo(r);
		return this; 
	}
	
	public Router toServlet(String to){
		Resource r = new Resource(to,ResourceType.SERVLET);
		addTo(r);
		return this;
	}
	
	public Router toJsp(String to){
		Resource r = new Resource(to,ResourceType.SERVLET);
		addTo(r);
		return this;
	}
	
	private synchronized void addFrom(Resource r){ 
		last = r.getName();
		routes.put(r.getName(), r);
	}
	
	private synchronized void addTo(Resource r){
		Resource from = routes.get(last);
		r.setFrom(from);
		routes.put(last, r);
	}
	
	public Map<String,Resource> getRoutes(){
		Map<String,Resource> returnRoutes = new HashMap<String, Resource>();
		returnRoutes = Collections.unmodifiableMap(routes);
		return returnRoutes; 
	}
	
	@Override
	public String toString() {
		return "Routes \n" + fromMapStructureToString(routes);
	}

	public static String fromMapStructureToString(Map<String, Resource> routes2) {
		
		StringBuffer sb = new StringBuffer();
		for(String key: routes2.keySet()){
			sb.append("From: " + key + " To: " + routes2.get(key).getName() + " \n");
		}
		return sb.toString();		
	}
	
}
