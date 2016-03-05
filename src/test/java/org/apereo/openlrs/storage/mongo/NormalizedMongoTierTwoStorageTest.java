/**
 * Copyright 2016 EDIA (r) Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package org.apereo.openlrs.storage.mongo;

import java.util.Arrays;
import java.util.UUID;

import org.apereo.openlrs.Application;
import org.apereo.openlrs.model.event.Event;
import org.apereo.openlrs.model.xapi.Statement;
import org.apereo.openlrs.model.xapi.XApiActor;
import org.apereo.openlrs.model.xapi.XApiContext;
import org.apereo.openlrs.model.xapi.XApiContextActivities;
import org.apereo.openlrs.model.xapi.XApiObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("fongo")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class NormalizedMongoTierTwoStorageTest extends NormalizedMongoTierTwoStorage {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSave() {
		Statement entity = new Statement();
		entity.setId(UUID.randomUUID().toString());
		XApiActor actor = new XApiActor();
		String mbox =  UUID.randomUUID().toString() + "@example.com";
		actor.setMbox(mbox);
		entity.setActor(actor);
		final XApiContext context = new XApiContext();
		final XApiContextActivities contextActivities = new XApiContextActivities();
		final XApiObject parent = new XApiObject();
		String contextId = UUID.randomUUID().toString();
		parent.setId(contextId);
		contextActivities.setParent(Arrays.asList(parent));
		context.setContextActivities(contextActivities);
		entity.setContext(context);
		Event event = (Event)save(entity);
		Assert.assertEquals(event, findById(entity.getId()));
		
		Assert.assertEquals(1, findByUser(mbox, new PageRequest(0, 100)).getTotalElements());
		Assert.assertEquals(event, findByUser(mbox, new PageRequest(0, 100)).getContent().get(0));

		Assert.assertEquals(1, findByContext(contextId, new PageRequest(0, 100)).getTotalElements());
		Assert.assertEquals(event, findByContext(contextId, new PageRequest(0, 100)).getContent().get(0));

		Assert.assertEquals(1, findByContextAndUser(contextId, mbox, new PageRequest(0, 100)).getTotalElements());
		Assert.assertEquals(event, findByContextAndUser(contextId, mbox, new PageRequest(0, 100)).getContent().get(0));

		Assert.assertEquals(event, findAll(new PageRequest(0, 1)).getContent().get(0));
	}

}
