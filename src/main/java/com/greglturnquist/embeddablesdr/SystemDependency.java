package com.greglturnquist.embeddablesdr;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class SystemDependency {

	@OneToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="TARGET_SYSTEM_ID", nullable=false)
	private System target;

	@Column
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public System getTarget() {
		return target;
	}

	public void setTarget(System target) {
		this.target = target;
	}

}
