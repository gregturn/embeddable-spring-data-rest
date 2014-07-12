package com.greglturnquist.embeddablesdr;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.EntityLinks;

@Configuration
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class Application {

	@Bean
	SystemDependencyJacksonModule systemDependencyJacksonModule(EntityLinks entityLinks) {
		return new SystemDependencyJacksonModule(entityLinks);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		SystemRepository repository = ctx.getBean(SystemRepository.class);

		System system1 = new System();
		system1.setName("router101");
		system1 = repository.save(system1);

		System system2 = new System();
		system2.setName("switch405");
		system2 = repository.save(system2);

		SystemDependency dep1 = new SystemDependency();
        dep1.setDescription("WLAN");
        dep1.setTarget(system1);

        SystemDependency dep2 = new SystemDependency();
        dep2.setDescription("UPS");
        dep2.setTarget(system1);

		ArrayList<SystemDependency> dependencies1 = new ArrayList<>();
        dependencies1.add(dep1);
        dependencies1.add(dep2);
        system1.setDependencies(dependencies1);

		system1 = repository.save(system1);

		SystemDependency dep3 = new SystemDependency();
		dep3.setDescription("MUX");
		dep3.setTarget(system1);

		SystemDependency dep4 = new SystemDependency();
		dep4.setDescription("Backup Battery");
		dep4.setTarget(system2);

		ArrayList<SystemDependency> dependencies2 = new ArrayList<>();
		dependencies2.add(dep3);
		dependencies2.add(dep4);
		system2.setDependencies(dependencies2);

		system2 = repository.save(system2);

	}

}
