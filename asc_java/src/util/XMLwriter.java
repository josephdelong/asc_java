/**
 * 
 * @author Joseph DeLong
 *  
 */
package util;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 */
public class XMLwriter extends DefaultHandler {
	
	private String displayText[] = new String[1000];
	private int numLines = 0;
	private String indent = "";

	/**
	 * 
	 * @param xmlFile
	 * @param doOverwrite
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void write(String xmlFile, boolean doOverwrite)
			throws ParserConfigurationException, SAXException, IOException {

		// Create a "parser factory" for creating SAX parsers
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		// Now use the parser factory to create a SAXParser object
		SAXParser sp = saxParserFactory.newSAXParser();

		// DO OTHER SET UP STUFF HERE IF NECESSARY
		
		// Finally, tell the parser to parse the input and notify the handler
		sp.parse(xmlFile, this);
		
		FileWriter fileWriter = new FileWriter(xmlFile + "_" + System.currentTimeMillis());
		
		for (int i = 0; i < numLines; i++) {
			fileWriter.write(displayText[i].toCharArray());
			fileWriter.write("\n");
		}
		
		fileWriter.close();

	}
	
	/**
	 * This method runs when we first start writing to the XML File (when NEW)
	 */
	public void startDocument() {
		displayText[numLines] = indent;
		displayText[numLines] += "<?xml version'\"1.0\" encoding=\"UTF-8\"?>";
		numLines++;
	}
	
	/**
	 * This method runs when we need to insert comments / instructional text into the XML document
	 * @param target
	 * @param data
	 */
	public void processingInstruction(String target, String data) {
		displayText[numLines] = indent;
		displayText[numLines] += "<?";
		
	}
	
	/**
	 * Method which runs when we need to insert an XML start tag
	 * @param uri
	 * @param localName
	 * @param qualifiedName
	 * @param attributes
	 */
	public void startElement(String uri, String localName, String qualifiedName, Attributes attributes) {
		displayText[numLines] = indent;
		indent+= "\t";
		displayText[numLines] += "<";
		displayText[numLines] += qualifiedName;
		if(attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				displayText[numLines] += ' ';
				displayText[numLines] += attributes.getQName(i);
				displayText[numLines] += "=\"";
				displayText[numLines] += attributes.getValue(i);
				displayText[numLines] += '"';
			}
		}
		displayText[numLines] += '>';
		numLines++;
	}
	
	/**
	 * 
	 * @param characters
	 * @param start
	 * @param length
	 */
	public void characters(char characters[], int start, int length) {
		String characterData = (new String(characters, start, length)).trim();
		if(characterData.indexOf("\n") < 0 && characterData.length() > 0) {
			displayText[numLines] = indent;
			displayText[numLines] += characterData;
			numLines++;
		}
	}
	
	/**
	 * 
	 * @param characters
	 * @param start
	 * @param length
	 */
	public void ignorableWhitespace(char characters[], int start, int length) {
		// characters(characters, start, length);
	}
	
	/**
	 * @param uri
	 * @param localName
	 * @param qualifiedName
	 */
	public void endElement(String uri, String localName, String qualifiedName) {
		indent = indent.substring(0,  indent.length() - 1);
		displayText[numLines] = indent;
		displayText[numLines] += "</";
		displayText[numLines] += qualifiedName;
		displayText[numLines] += '>';
		numLines++;
	}

}
