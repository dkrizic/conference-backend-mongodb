package com.prodyna.conference.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.prodyna.conference.Speaker;
import com.prodyna.conference.SpeakerService;
import com.prodyna.conference.monitoring.Monitored;

@Monitored
public class SpeakerServiceBean implements SpeakerService {

	@Inject
	private Logger log;

	@Inject
	private DBCollection speakers;

	@Override
	public void add(Speaker speaker) {
		BasicDBObject o = new BasicDBObject();
		o.put("_id", speaker.getId());
		o.put("name", speaker.getName());
		o.put("type", "speaker");
		o.put("created", new Date() );
		speakers.insert(o);
	}

	@Override
	public void delete(String sid) {
		speakers.remove(new BasicDBObject("_id", sid));
	}

	@Override
	public Set<Speaker> readAll() {
		BasicDBObject q = new BasicDBObject();
		q.put("type",  "speaker");
		Set<Speaker> ss = new HashSet<Speaker>();
		for (DBObject o : speakers.find( q )) {
			Speaker s = new Speaker((String) o.get("name"));
			ss.add(s);
		}
		return ss;
	}

}
