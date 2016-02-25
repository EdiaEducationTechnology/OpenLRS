package org.apereo.openlrs.model.event;

import static org.junit.Assert.*;

import java.util.Date;

import org.apereo.openlrs.model.xapi.Statement;
import org.apereo.openlrs.model.xapi.XApiActor;
import org.junit.Before;
import org.junit.Test;

public class EventConversionServiceTest extends EventConversionService {


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(fromXAPI(new Statement()));
	}
	@Test
	public void test1() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp("faahaas");
		final Event fromXAPI = fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertEquals(defaultDate(), fromXAPI.getTimestamp());

	}
	@Test
	public void test3() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp(null);
		final Event fromXAPI = fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertNull(fromXAPI.getTimestamp());
		
	}
	@Test
	public void test4() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp("2016-02-25T15:47:30+01:00");
		final Event fromXAPI = fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertNotEquals(defaultDate(), fromXAPI.getTimestamp());
		
	}
	@Test
	public void test5() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp("2016-02-25T14:17:58-01:00");
		final Event fromXAPI = fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertNotEquals(defaultDate(), fromXAPI.getTimestamp());
		
	}
	@Test
	public void test6() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp("2010-01-01T13:00:00-01:00");
		final Event fromXAPI = fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertNotEquals(defaultDate(), fromXAPI.getTimestamp());
		
	}
	
	@Override
	protected Date defaultDate() {
		return new Date(0);
	}

}
