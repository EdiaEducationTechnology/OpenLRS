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
package org.apereo.openlrs.storage.kinesis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.kinesis.AmazonKinesisClient;

/**
 * @author ggilbert
 *
 */
@Configuration
public class KinesisConfig {
  
  private String stream;
  private String url;

  @Bean
  public AmazonKinesisClient kinesisClient() {
    /*
     * Note we expect that the api key and secret
     * are available as system parameters either:
     * Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY
     * Java System Properties - aws.accessKeyId and aws.secretKey
     */
    return new AmazonKinesisClient();
  }
  
  @Bean(name="AWS_KINESIS_STREAM")
  public String kinesisStreamName() {
    return stream;
  }
  
  @Bean(name="AWS_KINESIS_URL")
  public String kinesisStreamUrl() {
    return url;
  }
}
