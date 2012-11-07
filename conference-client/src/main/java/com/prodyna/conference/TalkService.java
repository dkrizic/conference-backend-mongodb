package com.prodyna.conference;

import java.util.Set;

public interface TalkService {
	void add( Talk t );
	
	Set<Talk> readAll();

	void delete(String id);
}
