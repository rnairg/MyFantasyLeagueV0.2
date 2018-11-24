package com.mfl.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.mfl.models.BaseEntity;

@Repository
public class CommonDBServicesImp <T extends BaseEntity> implements CommonDBServicesInt<T> {

	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public ArrayList<Integer> objectToDB(ArrayList<T> obj) {
		Session s = sessionFactory.getCurrentSession();
		Transaction txn = s.getTransaction();
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			if(!txn.isActive()) {
				txn.begin();
			}
			for(T t1:obj)
			{
					result.add((int)s.save(t1));
			}
			txn.commit();
			return result;
		}catch(Exception e) {
			txn.rollback();
			throw e;
		}finally {
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> dBToObject(int key, String queryName) {
		Session s;
		s = sessionFactory.getCurrentSession();
		Transaction txn = s.getTransaction();
		try {
			if(!txn.isActive()) {
				txn.begin();
			}
			return (ArrayList<T>)s.createNamedQuery(queryName).setParameter("id",key).getResultList();
		}catch (Exception e)
		{
			//e.printStackTrace();
			//return null;
			throw e;
		}finally {
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> dBToObject(String queryName) {
		Session s;
		s = sessionFactory.getCurrentSession();
		Transaction txn = s.getTransaction();
		try {
			if(!txn.isActive()) {
				txn.begin();
			}
			return (ArrayList<T>)s.createNamedQuery(queryName).getResultList();
			
		}catch (Exception e)
		{
			throw e;
		}finally {
		}
	}

	@Override
	public void objectToDB(ArrayList<T> obj, String action) {
		Session s = sessionFactory.getCurrentSession();
		Transaction txn = s.getTransaction();
		try {
			if(!txn.isActive()) {
				txn.begin();
			}
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
			txn.commit();
		}catch(Exception e) {
			txn.rollback();
			throw e;
		}finally {
		}
		
	}

	
}
