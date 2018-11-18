package com.mfl.models;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity {
	@Version
	protected int version;
	
	public BaseEntity() {
		
	}
	public BaseEntity(int version) {
		this.version=version;
	}
}
