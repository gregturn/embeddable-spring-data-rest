/*
 * Copyright 2013-2014 the original author or authors.
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
package com.greglturnquist.embeddablesdr;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.rest.webmvc.json.PersistentEntityJackson2Module;
import org.springframework.hateoas.EntityLinks;

public class SystemDependencySerializerTests {

	private static final Logger log = LoggerFactory.getLogger(SystemDependencySerializerTests.class);

	private AnnotationConfigApplicationContext ctx;
	private SystemRepository repository;
	private PersistentEntityJackson2Module module;
	private SystemDependencySerializer serializer;
	private ObjectMapper mapper;

	@Before
	public void setUp() {

		this.ctx = new AnnotationConfigApplicationContext();
		this.ctx.register(SpringDataRestConfig.class);
		this.ctx.refresh();

		this.repository = ctx.getBean(SystemRepository.class);
		this.module = ctx.getBean(PersistentEntityJackson2Module.class);
		this.serializer = ctx.getBean(SystemDependencySerializer.class);
		this.mapper = ctx.getBean("objectMapper", ObjectMapper.class);
	}

	@Test
	public void testSerializer() throws JsonProcessingException {

		System system = new System();
		system.setName("router101");
		system.setId(1L);
		system.setDependencies(new ArrayList<SystemDependency>());

		SystemDependency dependency = new SystemDependency();
		dependency.setDescription("router");
		dependency.setTarget(system);

		system.getDependencies().add(dependency);

		system = repository.save(system);

		module.addSerializer(serializer);
		mapper.registerModule(module);

		String doc = mapper.writeValueAsString(system);

		log.info(doc);

		assertThat((String) JsonPath.read(doc, "$.name"), equalTo("router101"));
		assertThat((String) JsonPath.read(doc, "$.dependencies[0]._links.target.href"), equalTo("/path/to/1"));
	}

	@Configuration
	@Import(RepositoryRestMvcConfiguration.class)
	@EnableAutoConfiguration
	protected static class SpringDataRestConfig {

		@Bean
		SystemDependencySerializer serializer(EntityLinks entityLinks) {
			return new SystemDependencySerializer(entityLinks);
		}

	}

}
