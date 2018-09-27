package com.mfl.modules.match;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mfl.models.Match;
import com.mfl.models.Match.Matches;
import com.mfl.modules.DBServices;
@Repository
public class MatchDBServices implements DBServices {
	@Autowired
	private Matches matches;
	private String action;
	@Autowired
	private SessionFactory sessionFactory;
	
	public Matches getMatches() {
		return matches;
	}

	public void setMatches(Matches matches) {
		this.matches = matches;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public Boolean objectToDB() {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		System.out.println("Check point 1");
		for(Match match:matches.getMatches())
		{
			System.out.println("Action is:"+getAction());
			if(getAction().equals("create"))
			{
			s.save(match);
			}
			else if(getAction().equals("update"))
			{
			s.update(match);
			}
			else if(getAction().equals("delete"))
			{
			s.delete(match);
			}
		}
		s.getTransaction().commit();
		s.close();
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean dBToObject(int key) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		matches.setMatches((ArrayList<Match>) s.createQuery("from Match m where m.id="+key).list());
		s.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean dBToObject() {
		Session s = sessionFactory.openSession();
		s.beginTransaction();	
		matches.setMatches((ArrayList<Match>)s.createQuery("select m.id as id from Match m").setResultTransformer(Transformers.aliasToBean(Match.class)).list());;
		s.close();
		
		return null;
	}

}
