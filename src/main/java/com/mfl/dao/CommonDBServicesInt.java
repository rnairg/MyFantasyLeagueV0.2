package com.mfl.dao;

import java.util.ArrayList;
import java.util.List;

import com.mfl.models.BaseEntity;
public interface CommonDBServicesInt<T extends BaseEntity> {
	
	public ArrayList<Integer> objectToDB(List<T> obj);
	
	public void objectToDB(List<T> obj, String action);

	public ArrayList<T> dBToObject(int key,String queryName);

	public ArrayList<T> dBToObject(String queryName);

}
