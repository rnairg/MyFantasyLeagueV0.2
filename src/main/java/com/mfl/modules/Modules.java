package com.mfl.modules;

import java.util.ArrayList;

public interface Modules {

	//public Boolean create();
	
	//public Boolean create(Document xmlresource);
	
	//public Boolean update(Document xmlResource);
	
	public Object read();
	
	//public Boolean delete(Document xmlResource);

	public Object read(int i);

	ArrayList<Integer> create(Object o);

	ArrayList<Integer> delete(Object o);

	ArrayList<Integer> update(Object o);
	
}
