package com.greglturnquist.embeddablesdr;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;

public class SystemDependencyJacksonModule extends SimpleModule {

	@Autowired
	public SystemDependencyJacksonModule(EntityLinks entityLinks) {
		super(new Version(2, 0, 0, null, "com.greglturnquist.embeddablesdr", "jackson-module"));

		addSerializer(new SystemDependencySerializer(entityLinks));
	}

}
