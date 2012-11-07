package com.prodyna.conference.service;

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

@RunWith(Arquillian.class)
public class RoomServiceTest extends LoggingTest {

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
	private RoomService rs;

	@Test
	@InSequence(0)
	public void deleteAll() {
		for( Room r : rs.readAll() ) {
			rs.delete( r.getId() );
		}
	}
	
	@Test
	@InSequence(1)
	public void add() {
		Assert.assertNotNull(rs);
		Assert.assertNotNull(log);
		log.info("Service is " + rs);
		Room r = new Room("Ballroom A");
		rs.add(r);
	}

	@Test
	@InSequence(2)
	public void add2() {
		rs.add( new Room("Syndey" ) );
	}

	@Test
	@InSequence(3)
	public void readAll() {
		Set<Room> rms = rs.readAll();
		Assert.assertEquals( 2, rms.size() );
	}

}
