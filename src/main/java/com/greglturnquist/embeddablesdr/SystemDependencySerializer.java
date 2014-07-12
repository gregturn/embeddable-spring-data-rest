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

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;

public class SystemDependencySerializer extends StdSerializer<SystemDependency> {

	private static final Logger log = LoggerFactory.getLogger(SystemDependencySerializer.class);

	private final EntityLinks entityLinks;

	@Autowired
	public SystemDependencySerializer(EntityLinks entityLinks) {
		super(SystemDependency.class);
		this.entityLinks = entityLinks;
	}

	@Override
	public void serialize(final SystemDependency systemDependency, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonGenerationException {

		Link link = entityLinks.linkToSingleResource(System.class, systemDependency.getTarget().getId());

		jgen.writeStartObject();
		jgen.writeStringField("description", systemDependency.getDescription());
		jgen.writeObjectFieldStart("_links");
		jgen.writeObjectFieldStart("target");
		jgen.writeStringField("href", link.getHref());
		jgen.writeEndObject();
		jgen.writeEndObject();
		jgen.writeEndObject();
	}

}
