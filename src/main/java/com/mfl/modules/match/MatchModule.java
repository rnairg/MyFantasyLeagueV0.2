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
	
	@Override
	public Matches read() {
		return new Matches(mds.dBToObject("get_all_matches"));
	}
	@Override
	public Matches read(int i) {
		return new Matches(mds.dBToObject(i,"get_match_byID"));
	}
	@Override
	public ArrayList<Integer> create(Matches matches) {
		return mds.objectToDB(matches.getMatches());
	}
	@Override
	public void delete(Matches matches) {
		mds.objectToDB(matches.getMatches(),"delete");
	}
	@Override
	public void update(Matches matches) {
		mds.objectToDB(matches.getMatches(),"update");
	}
}
