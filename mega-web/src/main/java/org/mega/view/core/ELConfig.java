package org.mega.view.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class ELConfig  implements ServletContextListener {

	public ELConfig() {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
