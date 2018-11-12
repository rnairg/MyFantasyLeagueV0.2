package com.mfl.modules.match;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.Match.Matches;
import com.mfl.modules.Modules;
@Service
public class MatchModule implements Modules {
	@Autowired
	private MatchDBServices mds;
	@Override
	public Matches read() {
		mds.dBToObject();
		//getPds().getPlayers().displayPlayers();
		//mxs.objectToXML(mds.getMatches());
		return mds.getMatches();
	}
	@Override
	public Matches read(int i) {
		mds.dBToObject(i);
		//getPds().getPlayers().displayPlayers();
		//mxs.objectToXML(mds.getMatches());
		return mds.getMatches();
	}
	@Override
	public ArrayList<Integer> create(Object o) {
		mds.setMatches((Matches)o);
		mds.setAction("create");
		return mds.objectToDB();
	}
	@Override
	public ArrayList<Integer> delete(Object o) {
		mds.setMatches((Matches)o);
		mds.setAction("delete");
		return mds.objectToDB();
	}
	@Override
	public ArrayList<Integer> update(Object o) {
		mds.setMatches((Matches)o);
		mds.setAction("update");
		return mds.objectToDB();
	}
}
