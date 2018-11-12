package com.mfl.modules;

import java.util.ArrayList;

public interface DBServices {

	public ArrayList<Integer> objectToDB();

	public Boolean dBToObject(int key);

	public Boolean dBToObject();
	
}
