package com.mfl.modules.match;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.Match.Matches;
import com.mfl.dao.CommonDBServicesImp;
import com.mfl.models.Match;
import com.mfl.modules.Modules;

@Service
public class MatchModule implements Modules<Matches> {
	@Autowired
	private CommonDBServicesImp<Match> mds;
	
	@Autowired
	private Matches matches;
	
	@Override
	public Matches read() {
		matches.setMatches(mds.dBToObject("get_all_matches"));
		return matches;
	}
	@Override
	public Matches read(int i) {
		matches.setMatches(mds.dBToObject(i,"get_match_byID"));;
		return matches;
	}
	@Override
	public ArrayList<Integer> create(Matches matches) {
		this.matches=matches;
		return mds.objectToDB(this.matches.getMatches());
	}
	@Override
	public void delete(Matches matches) {
		this.matches=matches;
		mds.objectToDB(this.matches.getMatches(),"delete");
	}
	@Override
	public void update(Matches matches) {
		this.matches=matches;
		mds.objectToDB(this.matches.getMatches(),"update");
	}
}
