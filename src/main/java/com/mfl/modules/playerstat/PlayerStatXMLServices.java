package com.mfl.modules.playerstat;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.w3c.dom.Document;

import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.XMLServices;

public class PlayerStatXMLServices implements XMLServices {
	
	Jaxb2Marshaller jaxb2MarshallerPS;
	
	public Jaxb2Marshaller getJaxb2MarshallerPS() {
		return jaxb2MarshallerPS;
	}

	public void setJaxb2MarshallerPS(Jaxb2Marshaller jaxb2MarshallerPS) {
		this.jaxb2MarshallerPS = jaxb2MarshallerPS;
	}

	@Override
	public PlayerStats xMLToObject(Document xmlResource) {
		try {
			Unmarshaller um =getJaxb2MarshallerPS().getJaxbContext().createUnmarshaller();
			PlayerStats playerStats = (PlayerStats)um.unmarshal(xmlResource);
			return playerStats;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Document objectToXML(Object playerStats) {
		try {
			Marshaller m =getJaxb2MarshallerPS().getJaxbContext().createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
			m.marshal((PlayerStats)playerStats,System.out);
			return null;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void displayObject() {
		// TODO Auto-generated method stub

	}

}
