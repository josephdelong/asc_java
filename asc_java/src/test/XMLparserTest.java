/**
 * @author joseph_delong
 *
 */
package test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import asc_dataTypes.BuildingType;
import util.XMLparser;

/**
 * 
 */
public class XMLparserTest {

	public static void main(String[] args) {
		BuildingType bt = new BuildingType(5);
		bt.toString();
	}
	
}
