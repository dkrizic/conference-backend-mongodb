package com.prodyna.conference.service;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.mongodb.DB;
import com.prodyna.conference.Room;
import com.prodyna.conference.RoomService;

@Stateless
public class RoomServiceBean implements RoomService {

	@Inject
	private Logger log;

	@Inject
	private DB db;

	@Override
	public void add(Room r) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(String rid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Room> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
