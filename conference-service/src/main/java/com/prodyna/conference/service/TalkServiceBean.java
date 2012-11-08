package com.prodyna.conference.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.prodyna.conference.RoomService;
import com.prodyna.conference.Speaker;
import com.prodyna.conference.Talk;
import com.prodyna.conference.TalkService;
import com.prodyna.conference.monitoring.Monitored;
import com.prodyna.conference.service.producer.Talks;

@Monitored
public class TalkServiceBean implements TalkService {

	@Inject
	private Logger log;

	@Inject
	@Talks
	private DBCollection talks;

	@Inject
	private RoomService rs;

	@Override
	public void add(Talk t) {
		BasicDBObject o = new BasicDBObject();
		o.put("name", t.getName());
		o.put("room", t.getRoom().getId());
		o.put("type", "talk");
		o.put("_id", t.getId());

		BasicDBList l = new BasicDBList();
		for (Speaker s : t.getSpeakers()) {
			l.add(s.getId());
		}
		o.put("speakers", l);
		talks.insert(o);
	}

	@Override
	public Set<Talk> readAll() {
		BasicDBObject q = new BasicDBObject("type", "talk");
		DBCursor c = talks.find(q);
		Set<Talk> ts = new HashSet<Talk>();
		while (c.hasNext()) {
			DBObject o = c.next();
			Talk talk = new Talk();
			talk.setId("" + o.get("_id"));
			talk.setName("" + o.get("name"));
			talk.setRoom(rs.readById("" + o.get("room")));
			ts.add(talk);
		}
		return ts;
	}

	@Override
	public void delete(String id) {
		talks.remove(new BasicDBObject("_id", id));
	}

}
