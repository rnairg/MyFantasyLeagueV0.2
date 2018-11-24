package com.mfl.models;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity {
	@Version
	private int version;
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public BaseEntity() {
		
	}
	public BaseEntity(int version) {
		this.version=version;
	}
}
