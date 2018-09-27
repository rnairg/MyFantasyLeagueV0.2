package com.mfl.modules.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.mfl.models.Match.Matches;
import com.mfl.modules.Modules;
@Service
public class MatchModule implements Modules {
	@Autowired
	private MatchXMLServices mxs;
	@Autowired
	private MatchDBServices mds;

	@Override
	public Boolean create() {
		
		return null;
	}

	@Override
	public Boolean create(Document xmlResource) {
		Matches matches = mxs.xMLToObject(xmlResource);
		mds.setMatches(matches);
		mds.setAction("create");
		mds.objectToDB();
		return null;
	}

	@Override
	public Boolean update(Document xmlResource) {
		Matches matches = mxs.xMLToObject(xmlResource);
		mds.setMatches(matches);
		mds.setAction("update");
		mds.objectToDB();
		return null;
	}

	@Override
	public Matches read() {
		mds.dBToObject();
		//getPds().getPlayers().displayPlayers();
		mxs.objectToXML(mds.getMatches());
		return null;

	}

	@Override
	public Boolean delete(Document xmlResource) {
		Matches matches = mxs.xMLToObject(xmlResource);
		mds.setMatches(matches);
		mds.setAction("delete");
		mds.objectToDB();
		return null;
	}

	@Override
	public Matches read(int i) {
		mds.dBToObject(i);
		//getPds().getPlayers().displayPlayers();
		mxs.objectToXML(mds.getMatches());
		return null;

	}

	@Override
	public Boolean create(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
