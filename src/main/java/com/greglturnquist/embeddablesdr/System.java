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

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity(name = "T_SYSTEM")
public class System {

	@Id
	@GeneratedValue
	@Column(name = "SYSTEM_ID")
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@ElementCollection
	@CollectionTable(
		name = "T_SYSTEM_DEPENDENCY",
		joinColumns = @JoinColumn(name = "SYSTEM_ID", nullable = false)
	)
	private List<SystemDependency> dependencies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SystemDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<SystemDependency> dependencies) {
		this.dependencies = dependencies;
	}
}
