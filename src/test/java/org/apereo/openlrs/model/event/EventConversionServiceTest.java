package org.apereo.openlrs.model.event;

import static org.junit.Assert.*;

import org.apereo.openlrs.model.xapi.Statement;
import org.apereo.openlrs.model.xapi.XApiActor;
import org.junit.Before;
import org.junit.Test;

public class EventConversionServiceTest extends EventConversionService {

	private EventConversionService conversionService;

	@Before
	public void setUp() throws Exception {
		conversionService = new EventConversionService();
	}

	@Test
	public void test() {
		assertNotNull(conversionService.fromXAPI(new Statement()));
	}
	@Test
	public void test1() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp("faahaas");
		final Event fromXAPI = conversionService.fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertNotNull(fromXAPI.getTimestamp());

	}
	@Test
	public void test3() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp(null);
		final Event fromXAPI = conversionService.fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertNull(fromXAPI.getTimestamp());
		
	}
	@Test
	public void test4() {
		Statement entity = new Statement();
		XApiActor actor = new XApiActor();
		actor.setMbox("test@example.com");
		entity.setActor(actor);
		entity.setTimestamp("2016-02-25T15:47:30+0100");
		final Event fromXAPI = conversionService.fromXAPI(entity);
		assertNotNull(fromXAPI);
		assertNotNull(fromXAPI.getTimestamp());
		
	}

}
