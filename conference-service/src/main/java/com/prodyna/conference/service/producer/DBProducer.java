package com.prodyna.conference.service.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import com.mongodb.DB;
import com.mongodb.Mongo;

@ApplicationScoped
public class DBProducer {
	
	private static final String DB = "conference";
	
	@Inject private Mongo mongo;
	
	@Produces
	public DB produceDB( InjectionPoint ip ) {
		return mongo.getDB(DB);
	}
}
