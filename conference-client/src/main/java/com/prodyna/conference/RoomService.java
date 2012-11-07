package com.prodyna.conference;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/room")
public interface RoomService {

	@POST
	public void add(Room r);

	@DELETE
	@Path("/{rid}")
	public void delete(@PathParam("rid") String rid);

	@GET
	public Set<Room> readAll();
}
