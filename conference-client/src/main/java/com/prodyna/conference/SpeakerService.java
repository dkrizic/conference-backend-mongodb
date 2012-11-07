package com.prodyna.conference;

import java.util.Set;

public interface SpeakerService {
	public void add( Speaker speaker );
	public void delete( String sid );
	public Set<Speaker> readAll();
}
