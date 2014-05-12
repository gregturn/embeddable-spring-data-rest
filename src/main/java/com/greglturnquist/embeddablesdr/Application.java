package com.greglturnquist.embeddablesdr;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		SystemRepository repository = ctx.getBean(SystemRepository.class);

		final System system = new System();
		system.setName("router101");
		system.setDependencies(new ArrayList<SystemDependency>() {{
			add(new SystemDependency() {{
				setDescription("WLAN");
				setTarget(system);
			}});
			add(new SystemDependency() {{
				setDescription("UPS");
				setTarget(system);
			}});
		}});

		repository.save(system);
	}

}
