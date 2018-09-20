package com.mfl.modules.match;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.w3c.dom.Document;

import com.mfl.models.Match.Matches;
import com.mfl.modules.XMLServices;

public class MatchXMLServices implements XMLServices {
	
	Jaxb2Marshaller jaxb2MarshallerM;
	
	public Jaxb2Marshaller getJaxb2MarshallerM() {
		return jaxb2MarshallerM;
	}

	public void setJaxb2MarshallerM(Jaxb2Marshaller jaxb2MarshallerM) {
		this.jaxb2MarshallerM = jaxb2MarshallerM;
	}

	@Override
	public Matches xMLToObject(Document xmlResource) {
		try {
				Unmarshaller um = getJaxb2MarshallerM().getJaxbContext().createUnmarshaller();
				return (Matches)um.unmarshal(xmlResource);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public Document objectToXML(Object matches) {
		try {
			Marshaller m =getJaxb2MarshallerM().getJaxbContext().createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
			m.marshal((Matches)matches,System.out);
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
