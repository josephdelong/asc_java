/**
 * 
 * @author Joseph DeLong
 *  
 */
package util;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 */
public class XMLwriter {
	
	public void write(String fileName, boolean doOverwrite) {
		
		XMLStreamWriter writer = new XMLStreamWriter() {
			
			@Override
			public void writeStartElement(String prefix, String localName, String namespaceURI)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeStartElement(String namespaceURI, String localName)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeStartElement(String localName) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeStartDocument(String encoding, String version)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeStartDocument(String version) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeStartDocument() throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeProcessingInstruction(String target, String data)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeProcessingInstruction(String target)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeNamespace(String prefix, String namespaceURI)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeEntityRef(String name) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeEndElement() throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeEndDocument() throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeEmptyElement(String prefix, String localName, String namespaceURI)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeEmptyElement(String namespaceURI, String localName)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeEmptyElement(String localName) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeDefaultNamespace(String namespaceURI) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeDTD(String dtd) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeComment(String data) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeCharacters(char[] text, int start, int len)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeCharacters(String text) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeCData(String data) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeAttribute(String prefix, String namespaceURI, String localName,
					String value) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeAttribute(String namespaceURI, String localName, String value)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void writeAttribute(String localName, String value)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setPrefix(String prefix, String uri) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setNamespaceContext(NamespaceContext context)
					throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setDefaultNamespace(String uri) throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object getProperty(String name) throws IllegalArgumentException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getPrefix(String uri) throws XMLStreamException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public NamespaceContext getNamespaceContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void flush() throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void close() throws XMLStreamException {
				// TODO Auto-generated method stub
				
			}
		};
		
	}
	
}
