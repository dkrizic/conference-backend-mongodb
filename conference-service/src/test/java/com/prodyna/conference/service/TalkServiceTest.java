package com.prodyna.conference.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import com.prodyna.conference.Room;
import com.prodyna.conference.RoomService;
import com.prodyna.conference.Speaker;
import com.prodyna.conference.SpeakerService;
import com.prodyna.conference.Talk;
import com.prodyna.conference.TalkService;

@RunWith(Arquillian.class)
public class TalkServiceTest extends LoggingTest {

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
		jar.addPackages(true, "com.prodyna.conference");
		jar.addAsManifestResource("META-INF/beans.xml");
		return jar;
	}

	@Inject
	private Logger log;

	@Inject
	private TalkService ts;

	@Inject
	private RoomService rs;

	@Inject
	private SpeakerService ss;

	@Test
	@InSequence(0)
	public void deleteAll() {
		for (Talk t : ts.readAll()) {
			ts.delete(t.getId());
		}
	}

	@Test
	@InSequence(1)
	public void add() {
		Speaker darko = new Speaker("Darko");
		Speaker markus = new Speaker("Markus");
		Room montreal = new Room("Montreal");
		Talk t = new Talk();
		t.setName("Testen mit Arquillian");
		t.setRoom(montreal);
		Set<Speaker> sks = new HashSet<Speaker>();
		sks.add(darko);
		sks.add(markus);
		t.setSpeakers(sks);

		ss.add(darko);
		ss.add(markus);
		rs.add(montreal);
		ts.add(t);
	}

	@Test
	@InSequence(2)
	public void readAll() {
		Set<Talk> tss = ts.readAll();
		Assert.assertEquals(1, tss.size());
		Talk t = tss.iterator().next();
		Assert.assertEquals("Testen mit Arquillian", t.getName() );
		Assert.assertNotNull( t.getRoom() );
		Assert.assertEquals("Montreal", t.getRoom().getName() );
	}
}
