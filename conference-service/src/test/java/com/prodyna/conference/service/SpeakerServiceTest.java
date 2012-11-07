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

import com.prodyna.conference.Speaker;
import com.prodyna.conference.SpeakerService;

@RunWith(Arquillian.class)
public class SpeakerServiceTest extends LoggingTest {

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
	private SpeakerService ss;

	@Test
	@InSequence(0)
	public void deleteAll() {
		for (Speaker s : ss.readAll()) {
			ss.delete(s.getId());
		}
	}

	@Test
	@InSequence(1)
	public void add() {
		Assert.assertNotNull(ss);
		Assert.assertNotNull(log);
		log.info("Service is " + ss);
		Speaker s = new Speaker("Darko");
		ss.add(s);
	}

	@Test
	@InSequence(2)
	public void readAll() {
		Set<Speaker> sss = ss.readAll();
		Assert.assertEquals(1, sss.size());
	}

}
