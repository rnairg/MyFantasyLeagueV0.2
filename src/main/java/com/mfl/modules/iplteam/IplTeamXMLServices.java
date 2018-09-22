package com.mfl.modules.iplteam;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.mfl.models.IplTeam.IplTeams;
import com.mfl.modules.XMLServices;
@Service
public class IplTeamXMLServices implements XMLServices {
	
	Jaxb2Marshaller jaxb2MarshallerIT;
	
		public Jaxb2Marshaller getJaxb2MarshallerIT() {
		return jaxb2MarshallerIT;
	}

	public void setJaxb2MarshallerIT(Jaxb2Marshaller jaxb2MarshallerIT) {
		this.jaxb2MarshallerIT = jaxb2MarshallerIT;
	}

	@Override
	public IplTeams xMLToObject(Document xmlResource) {
		try {
				Unmarshaller um =getJaxb2MarshallerIT().getJaxbContext().createUnmarshaller();
				IplTeams iplteams = (IplTeams)um.unmarshal(xmlResource);
				return iplteams;
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
	public Document objectToXML(Object iplteams) {
		try {
				Marshaller m =getJaxb2MarshallerIT().getJaxbContext().createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
				m.marshal((IplTeams)iplteams,System.out);
				return null;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

}
