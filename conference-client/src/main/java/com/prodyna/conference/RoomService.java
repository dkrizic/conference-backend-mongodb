package com.prodyna.conference;

import java.util.Set;

import javax.ws.rs.PathParam;

//@Path("/room")
public interface RoomService {

//	@POST
	public void add(Room r);

//	@DELETE
//	@Path("/{rid}")
	public void delete(@PathParam("rid") String rid);

//	@GET
	public Set<Room> readAll();

	Room readById(String id);
}
