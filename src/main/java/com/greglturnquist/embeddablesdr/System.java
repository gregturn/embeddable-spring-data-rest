package com.greglturnquist.embeddablesdr;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name="T_SYSTEM")
public class System {

	@Id @GeneratedValue
	@Column(name="SYSTEM_ID")
	private Long id;

	@Column(unique=true,nullable=false)
	private String name;

	@ElementCollection
	@CollectionTable(

			name="T_SYSTEM_DEPENDENCY",

			joinColumns=@JoinColumn(name="SYSTEM_ID",nullable=false))

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
