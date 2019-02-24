package com.mfl.utils;

import com.mfl.models.Player;
import com.mfl.models.PlayerStat;

public class Points {
	
	enum Factors{
		Batsman(1,0,0),Bowler(0,25,0), WicketKeeper(1,0,10), AllRounder(1,25,0);
		
		int runsFactor;
		int wicketsFactor;
		int wkDismissalFactor;
		
		Factors(int r, int w, int wk){
			runsFactor = r;
			wicketsFactor = w;
			wkDismissalFactor = wk;
		}
		
		int getRunsFactor() {
			return runsFactor;
		}
		int getWicketsFactor() {
			return wicketsFactor;
		}
		int getWkDismissalFactor() {
			return wkDismissalFactor;
		}
	}
	
	public static int calculatePoints(PlayerStat ps, Player p) {
		int points = 0;
							if(p.getCategory().equals("Batsman")) {
								System.out.println(p.getCategory());
								points = (ps.getRuns()*Factors.Batsman.getRunsFactor())+
								(ps.getWickets()*Factors.Batsman.getWicketsFactor())+
								(ps.getCatches()*Factors.Batsman.getWkDismissalFactor());
							}else if(p.getCategory().equals("Bowler")) {
								points = (ps.getRuns()*Factors.Bowler.getRunsFactor())+
								(ps.getWickets()*Factors.Bowler.getWicketsFactor())+
								(ps.getCatches()*Factors.Bowler.getWkDismissalFactor());
							}else if(p.getCategory().equals("Wicket-Keeper")) {
								points = (ps.getRuns()*Factors.WicketKeeper.getRunsFactor())+
								(ps.getWickets()*Factors.WicketKeeper.getWicketsFactor())+
								(ps.getCatches()*Factors.WicketKeeper.getWkDismissalFactor());
							}else if(p.getCategory().equals("All-Rounder")) {
								points = (ps.getRuns()*Factors.AllRounder.getRunsFactor())+
								(ps.getWickets()*Factors.AllRounder.getWicketsFactor())+
								(ps.getCatches()*Factors.AllRounder.getWkDismissalFactor());
							}
		return points;
	}
}
