package com.mfl.modules;

import java.util.ArrayList;

public interface Modules <T> {

	
	public T read();

	public T read(int i);

	public ArrayList<Integer> create(T o);

	public void delete(T o);

	public void update(T o);
	
}
