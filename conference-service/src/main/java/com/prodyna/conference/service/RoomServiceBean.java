package com.prodyna.conference.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.prodyna.conference.Room;
import com.prodyna.conference.RoomService;
import com.prodyna.conference.monitoring.Monitored;
import com.prodyna.conference.service.producer.Rooms;

@Monitored
public class RoomServiceBean implements RoomService {

	@Inject
	private Logger log;

	@Inject
	@Rooms
	private DBCollection rooms;

	@Override
	public void add(Room r) {
		log.info("Adding " + r + " to " + rooms);
		BasicDBObject o = new BasicDBObject();
		o.put("_id", r.getId());
		o.put("name", r.getName());
		o.put("type", "room");
		WriteResult wr = rooms.insert(o);
		if (!wr.getLastError().ok()) {
			throw new RuntimeException(wr.getError());
		}
	}

	@Override
	public void delete(String rid) {
		rooms.remove(new BasicDBObject("_id", rid));
	}

	@Override
	public Room readById(String id) {
		return new Room(""
				+ rooms.find(new BasicDBObject("_id", id)).next().get("name"));
	}

	@Override
	public Set<Room> readAll() {
		BasicDBObject q = new BasicDBObject();
		q.put("type", "room");
		DBCursor c = rooms.find(q);
		Set<Room> rs = new HashSet<Room>();
		while (c.hasNext()) {
			BasicDBObject o = (BasicDBObject) c.next();
			rs.add(new Room(o.getString("name")));
		}
		return rs;
	}
}
