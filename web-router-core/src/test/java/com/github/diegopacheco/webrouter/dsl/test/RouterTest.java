package com.github.diegopacheco.webrouter.dsl.test;

import org.junit.Test;

import com.github.diegopacheco.webrouter.dsl.Router;

/**
 * 
 * @author Diego Pacheco
 * @since 20/04/2011
 * @version 1.0
 *
 */
public class RouterTest {
		
	@Test
	public void testSimpleDSL(){
		
		System.out.println(Router.route()
			  
			  .from("/crud/people")
			  .toJsp("peoplecrud.jsp")
			  
			  .from("/crud/admin")
			  .toServlet("com.servlets.admin.AdminServlet")
		);
		
	}
	
}
