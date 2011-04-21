package com.github.diegopacheco.webrouter.router;

import com.github.diegopacheco.webrouter.base.WebRouter;
import com.github.diegopacheco.webrouter.dsl.Router;

/**
 * 
 * @author Diego Pacheco
 * @since 20/04/2011
 * @version 1.0
 *
 */
public class MyRouter extends WebRouter {

	public Router define(){
		return Router.route()
				  
				  .from("/crud/people")
				  .toJsp("peoplecrud.jsp")
				  
				  .from("/crud/admin")
				  .toServlet("com.servlets.admin.AdminServlet")
				  
			   ;
	}
}
