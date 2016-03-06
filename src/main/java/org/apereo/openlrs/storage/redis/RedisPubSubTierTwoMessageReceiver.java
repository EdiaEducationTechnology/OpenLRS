/**
 * Copyright 2015 Unicon (R) Licensed under the
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
package org.apereo.openlrs.storage.redis;

import org.apereo.openlrs.exceptions.InvalidEventFormatException;
import org.apereo.openlrs.model.OpenLRSEntity;
import org.apereo.openlrs.model.caliper.CaliperEvent;
import org.apereo.openlrs.model.xapi.Statement;
import org.apereo.openlrs.storage.StorageFactory;
import org.apereo.openlrs.storage.TierTwoStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ggilbert
 *
 */
@Component
@Profile("redis")
public class RedisPubSubTierTwoMessageReceiver {
	
	private static final Logger log = LoggerFactory.getLogger(RedisPubSubTierTwoMessageReceiver.class);
	@Autowired private ObjectMapper objectMapper;
	@Autowired private StorageFactory storageFactory;

	
	public void onMessage(String json) {
		// guess at format
		OpenLRSEntity entity = null;
		
		try {
			entity = objectMapper.readValue(json.getBytes("UTF-8"), Statement.class);
		}
		catch (Exception e) {
			log.warn("unable to parse {} as xapi",json);
		}
		
		if (entity == null) {
			// try caliper
			try {
				entity = objectMapper.readValue(json.getBytes("UTF-8"), CaliperEvent.class);
			}
			catch (Exception e) {
				throw new InvalidEventFormatException(String.format("unable to parse %s",json),e);
			}
		}
		
		try {
			TierTwoStorage<OpenLRSEntity> tierTwoStorage = storageFactory.getTierTwoStorage();
			tierTwoStorage.save(entity);
		}
		catch (Exception e) {
			log.error(e.getMessage(),e);
		}
				
	}

}
