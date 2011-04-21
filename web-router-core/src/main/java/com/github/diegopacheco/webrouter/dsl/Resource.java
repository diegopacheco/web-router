package com.github.diegopacheco.webrouter.dsl;

/**
 * 
 * @author Diego Pacheco
 * @since 20/04/2011
 * @version 1.0
 *
 */
public class Resource {
	
	private Resource     from = null;
	private String       name;
	private ResourceType type;
	
	public Resource(String name, ResourceType type) {
		super();
		this.name   = name;
		this.type   = type;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
	public void setFrom(Resource from){
		this.from = from;
	}

	public String getName() {
		return name;
	}
	public ResourceType getType() {
		return type;
	}	
	public Resource getFrom() {
		return from;
	}

}
