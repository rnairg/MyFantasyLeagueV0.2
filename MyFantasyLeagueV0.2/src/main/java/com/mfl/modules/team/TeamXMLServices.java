package com.mfl.modules.team;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import com.mfl.models.Team.Teams;
import com.mfl.modules.XMLServices;
@Service
public class TeamXMLServices implements XMLServices {
	
	Jaxb2Marshaller jaxb2MarshallerT;
	
	public Jaxb2Marshaller getJaxb2MarshallerT() {
		return jaxb2MarshallerT;
	}

	public void setJaxb2MarshallerT(Jaxb2Marshaller jaxb2MarshallerT) {
		this.jaxb2MarshallerT = jaxb2MarshallerT;
	}

	@Override
	public Teams xMLToObject(Document xmlResource) {
		try {
				Unmarshaller um =getJaxb2MarshallerT().getJaxbContext().createUnmarshaller();
				Teams teams = (Teams)um.unmarshal(xmlResource);
				return teams;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		// TODO Auto-generated method stub
	}

	@Override
	public void displayObject() {
		// TODO Auto-generated method stub

	}


	@Override
	public Document objectToXML(Object teams) {
		try {
				Marshaller m =getJaxb2MarshallerT().getJaxbContext().createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
				m.marshal((Teams)teams,System.out);
				return null;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

}
