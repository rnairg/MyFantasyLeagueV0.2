package com.mfl.modules.playerstat;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mfl.models.PlayerStat;
import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.DBServices;
@Repository
public class PlayerStatDBServices implements DBServices {
	@Autowired
	private PlayerStats playerStats;
	private String action;
	@Autowired
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

	@Override
	public ArrayList<Integer> objectToDB() {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<playerStats.getPlayerStats().size();i++)
		{
			System.out.println("Action is:"+getAction());
			if(getAction().equals("create"))
			{
				result.add((int)s.save(playerStats.getPlayerStats().get(i)));
			}
			else if(getAction().equals("update"))
			{
				s.update(playerStats.getPlayerStats().get(i));
				result.add(playerStats.getPlayerStats().get(i).getId());
			}
			else if(getAction().equals("delete"))
			{
				s.delete(playerStats.getPlayerStats().get(i));
				result.add(playerStats.getPlayerStats().get(i).getId());
			}
		}
		s.getTransaction().commit();
		s.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean dBToObject(int key) {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		playerStats.setPlayerStats((ArrayList<PlayerStat>) s.createQuery("from PlayerStat ps where  ps.id="+key).list());
		s.close();
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public Boolean dBToObject() {
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		playerStats.setPlayerStats((ArrayList<PlayerStat>) s.createQuery("select  ps.id as id from PlayerStat ps").setResultTransformer(Transformers.aliasToBean(PlayerStat.class)).list());
		s.close();
		return null;
	}
}
