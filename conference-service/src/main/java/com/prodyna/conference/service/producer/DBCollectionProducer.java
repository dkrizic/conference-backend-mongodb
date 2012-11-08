package com.prodyna.conference.service.producer;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class DBCollectionProducer {

	private static final String ROOMS = "rooms";

	@Inject
	private DB db;

	@Produces
	public DBCollection createRoomsCollection() {
		DBCollection rooms = db.getCollection(ROOMS);
		return rooms;
	}
}
