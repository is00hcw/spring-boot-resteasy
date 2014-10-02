/*
 * Copyright 2012-2014 the original author or authors.
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

package org.springframework.boot.resteasy.autoconfigure;

import org.jboss.resteasy.springmvc.ResteasyHandlerMapping;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ConditionalOnClass(ResteasyHandlerMapping.class)
@ImportResource("classpath:springmvc-resteasy.xml")
@Configuration
public class ResteasyAutoConfiguration {

	@Bean
	public BeanPostProcessor postProcessor() {
		return new BeanPostProcessor() {

			@Override
			public Object postProcessBeforeInitialization(Object bean,
					String beanName) throws BeansException {
				if (bean instanceof ResteasyHandlerMapping) {
					((ResteasyHandlerMapping)bean).setOrder(Integer.MAX_VALUE - 2);
				}
				return bean;
			}

			@Override
			public Object postProcessAfterInitialization(Object bean,
					String beanName) throws BeansException {
				return bean;
			}

		};
	}

}
