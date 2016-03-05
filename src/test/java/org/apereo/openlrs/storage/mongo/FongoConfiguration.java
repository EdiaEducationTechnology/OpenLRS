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

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

/**
 * @author roland
 *
 */
@Configuration
@EnableAutoConfiguration
@Profile("fongo")
public class FongoConfiguration {
    @Bean
    public Mongo mongo() {
        // Configure a Fongo instance
        return new Fongo("mongo-test").getMongo();
    }
}
