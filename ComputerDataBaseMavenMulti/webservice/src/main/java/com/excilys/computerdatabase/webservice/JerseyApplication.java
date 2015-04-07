package com.excilys.computerdatabase.webservice;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
	/**
	* Register JAX-RS application components.
	*/
	public JerseyApplication() {
	register(CompanyBLWeb.class);
	}

}
