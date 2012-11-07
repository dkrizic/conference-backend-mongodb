package com.prodyna.conference.service.producer;

import java.net.UnknownHostException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

@ApplicationScoped
public class MongoProducer {

	private static final int PORT = 27017;
	private static final String HOST = "127.0.0.1";

	@Produces
	public Mongo produceMongo(InjectionPoint ip) throws UnknownHostException,
			MongoException {
		return new Mongo(HOST, PORT);
	}
}
