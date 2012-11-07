package com.prodyna.conference.service;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggingTest {

	static {
		BasicConfigurator.configure();
		Logger.getLogger("org.jboss.weld").setLevel( Level.INFO );
	}
	
}
