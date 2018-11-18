package com.mfl.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mfl.models.BaseEntity;

@Repository
public class CommonDBServicesImp <T extends BaseEntity> implements CommonDBServicesInt<T> {

	@Autowired
	private ArrayList<T> t;
	
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public ArrayList<Integer> objectToDB(ArrayList<T> obj) {
		Session s = sessionFactory.getCurrentSession();
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			s.beginTransaction();
			for(T t1:obj)
			{
					result.add((int)s.save(t1));
			}
			s.getTransaction().commit();
			return result;
		}catch(Exception e) {
			System.out.println(""+e);
			return result;
		}finally {
			//s.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> dBToObject(int key, String queryName) {
		Session s;
		s = sessionFactory.getCurrentSession();
		try {
			s.beginTransaction();	
			this.t = (ArrayList<T>)s.createNamedQuery(queryName).setParameter("id",key).getResultList();
			return t;
		}catch (Exception e)
		{
			System.out.println(""+e);
			return t;
		}finally {
	
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> dBToObject(String queryName) {
		Session s;
		s = sessionFactory.getCurrentSession();
		try {
			s.beginTransaction();	
			this.t = (ArrayList<T>)s.createNamedQuery(queryName).getResultList();
			return t;
		}catch (Exception e)
		{
			System.out.println(""+e);
			return t;
		}finally {
	
		}
	}

	@Override
	public void objectToDB(ArrayList<T> obj, String action) {
		Session s = sessionFactory.getCurrentSession();
		try {
			s.beginTransaction();
			for(T t1:obj)
			{
				if(action.equals("update"))
				{
					s.update(t1);
				}
				else if(action.equals("delete"))
				{
					s.delete(t1);
				}
			}
			s.getTransaction().commit();
		}catch(Exception e) {
			System.out.println(""+e);
		}finally {

		}
		
	}

	
}
