package com.mfl.modules;

import org.w3c.dom.Document;

//import com.mfl.modules.player.PlayerXMLServices.Players;

public interface XMLServices {
	
	public Object xMLToObject(Document xmlResource);
	
	public Document objectToXML(Object obj);

	void displayObject();

}
