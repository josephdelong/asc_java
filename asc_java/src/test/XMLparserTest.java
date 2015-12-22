/**
 * @author joseph_delong
 *
 */
package test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import util.XMLparser;

/**
 * 
 */
public class XMLparserTest {

	public static void main(String[] args) {
		XMLparser parser = new XMLparser();
		try {
			parser.parse("src/asc_dataTypes/buildings.xml", null, null);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
