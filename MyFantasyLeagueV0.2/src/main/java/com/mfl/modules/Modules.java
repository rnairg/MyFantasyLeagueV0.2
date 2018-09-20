package com.mfl.modules;

import org.w3c.dom.Document;

public interface Modules {

	public Boolean create();
	
	public Boolean create(Document xmlresource);
	
	public Boolean update(Document xmlResource);
	
	public Object read();
	
	public Boolean delete(Document xmlResource);

	public Object read(int i);

	Boolean create(Object o);

	Boolean delete(Object o);

	Boolean update(Object o);
	
}
