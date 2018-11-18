package com.mfl.dao;

import java.util.ArrayList;
import com.mfl.models.BaseEntity;

public interface CommonDBServicesInt<T extends BaseEntity> {
	
	public ArrayList<Integer> objectToDB(ArrayList<T> obj);
	
	public void objectToDB(ArrayList<T> obj, String action);

	public ArrayList<T> dBToObject(int key,String queryName);

	public ArrayList<T> dBToObject(String queryName);

}
