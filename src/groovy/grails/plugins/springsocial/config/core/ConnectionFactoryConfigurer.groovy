/* Copyright 2013 Domingo Suarez Torres.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugins.springsocial.config.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.social.connect.ConnectionFactory
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.support.ConnectionFactoryRegistry
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
class ConnectionFactoryConfigurer {
  @Autowired
  ConnectionFactoryLocator connectionFactoryLocator
  @Autowired
  ApplicationContext appCtx

  @PostConstruct
  void postProcessBeanFactory() {
    //TODO: Document the automatic ConnectionFactory registration
    def connectionFactories = appCtx.getBeansOfType(ConnectionFactory)
    connectionFactories.each { connectionFactoryKey, connectionFactory ->
      ((ConnectionFactoryRegistry) connectionFactoryLocator).addConnectionFactory(connectionFactory)
    }
  }
}