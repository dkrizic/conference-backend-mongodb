package com.prodyna.conference.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.prodyna.conference.Room;
import com.prodyna.conference.RoomService;

public class RoomServiceBean implements RoomService {

	@Inject
	private Logger log;

	@Inject
	private DBCollection rooms;

	@Override
	public void add(Room r) {
		log.info("Adding " + r + " to " + rooms);
		BasicDBObject o = new BasicDBObject();
		o.put("type", "room");
		o.put("name", r.getName());
		rooms.insert(o);
	}

	@Override
	public void delete(String rid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Room> readAll() {
		DBCursor c = rooms.find();
		Set<Room> rs = new HashSet<Room>();
		while (c.hasNext()) {
			BasicDBObject o = (BasicDBObject) c.next();
			rs.add(new Room(o.getString("name")));
		}
		return rs;
	}
}
