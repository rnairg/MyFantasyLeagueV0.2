package com.mfl.modules.playerstat;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import com.mfl.models.PlayerStat;
import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.DBServices;

public class PlayerStatDBServices implements DBServices {
	
	private PlayerStats playerStats;
	private String action;
	private SessionFactory sessionFactory;
	
	public PlayerStats getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(PlayerStats playerStats) {
		this.playerStats = playerStats;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Boolean objectToDB() {
		Session s = getSessionFactory().openSession();
		s.beginTransaction();
		for(int i=0;i<playerStats.getPlayerStats().size();i++)
		{
			System.out.println("Action is:"+getAction());
			if(getAction().equals("create"))
			{
			s.save(playerStats.getPlayerStats().get(i));
			}
			else if(getAction().equals("update"))
			{
				System.out.println("Here");
			s.update(playerStats.getPlayerStats().get(i));
			}
			else if(getAction().equals("delete"))
			{
			s.delete(playerStats.getPlayerStats().get(i));
			}
			
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean dBToObject(int key) {
		Session s = getSessionFactory().openSession();
		s.beginTransaction();
		playerStats.setPlayerStats((ArrayList<PlayerStat>) s.createQuery("from PlayerStat ps where  ps.id="+key).list());
		s.close();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean dBToObject() {
		Session s = getSessionFactory().openSession();
		s.beginTransaction();
		playerStats.setPlayerStats((ArrayList<PlayerStat>) s.createQuery("select  ps.id as id from PlayerStat ps").setResultTransformer(Transformers.aliasToBean(PlayerStat.class)).list());
		s.close();
		return null;
	}

}
