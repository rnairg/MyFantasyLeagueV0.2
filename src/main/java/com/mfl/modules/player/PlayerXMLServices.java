package com.mfl.modules.player;


import com.mfl.models.Player.Players;
import com.mfl.modules.XMLServices;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
@Service
public class PlayerXMLServices implements XMLServices {
	
	Jaxb2Marshaller jaxb2MarshallerP;
	
	public Jaxb2Marshaller getJaxb2MarshallerP() {
		return jaxb2MarshallerP;
	}

	public void setJaxb2MarshallerP(Jaxb2Marshaller jaxb2MarshallerP) {
		this.jaxb2MarshallerP = jaxb2MarshallerP;
	}

	@Override
	public Players xMLToObject(Document xmlResource) {
		try {
				Unmarshaller um = getJaxb2MarshallerP().getJaxbContext().createUnmarshaller();
				return (Players)um.unmarshal(xmlResource);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	
	}
	
	@Override
	public void displayObject() {
		
	}

	@Override
	public Document objectToXML(Object players) {
		try {
				Marshaller m =getJaxb2MarshallerP().getJaxbContext().createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
				m.marshal((Players)players,System.out);
				return null;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	

}
