/**
 * @author Joseph DeLong
 * XML Parser example taken from:
 *     http://javarevisited.blogspot.com/2011/12/parse-read-xml-file-java-sax-parser.html
 *     Original Author: Javin Paul
 *
 */
package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import asc_dataTypes.Building;
import asc_dataTypes.BuildingType;
import asc_dataTypes.DataType;
import asc_dataTypes.Resource;
import asc_dataTypes.Unit;
import asc_dataTypes.UnitType;

public class XMLparser extends DefaultHandler {

	private String temp;
	
	private DataType dataMember;
	private ArrayList<DataType> dataMembers = new ArrayList<DataType>();
	private String dataType;
	private String field;
	
	private ArrayList<String> tags;
	private ArrayList<Integer> indexes;
	
	private int currentIndex = 1;

	/**
	 * The parse method sets up the SAXParserFactory, SAXParser, and creates an instance of
	 *   this XMLparser class which handles all XML parsing, passing values contained within
	 *   XML tags to the appropriate DataType.
	 * @param xmlFile String representing the relative file path of the XML file to be parsed.
	 * @param tags ArrayList<String> containing the names of all XML tags that are to be parsed.
	 * @param indexes ArrayLilst<Integer> containing the unique IDs of the individual DataType
	 *   to be parsed.
	 * @return ArrayList<DataType> containing the specific DataType instances and tags specified.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public ArrayList<DataType> parse(String xmlFile, ArrayList<String> tags, ArrayList<Integer> indexes)
			throws IOException, SAXException, ParserConfigurationException {

		// Create a "parser factory" for creating SAX parsers
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		// Now use the parser factory to create a SAXParser object
		SAXParser sp = saxParserFactory.newSAXParser();

		// Create an instance of this class; it defines all the handler methods
		XMLparser parser = new XMLparser();

		// Extract the Data Type from the XML file name, dropping the last 's'
		dataType = xmlFile.substring(xmlFile.lastIndexOf('/') + 1, xmlFile.lastIndexOf('.') - 1);
		// Grab the tags parameter
		if(tags.isEmpty()) {
			this.tags = null;
		} else {
			this.tags = tags;
		}
		//Grab the indexes parameter
		if(indexes.isEmpty()) {
			this.indexes = null;
		} else {
			this.indexes = indexes;
		}
		
		// Finally, tell the parser to parse the input and notify the handler
		sp.parse(xmlFile, parser);
		// Read out the contents of the dataMember ArrayList
		//parser.readList();
		return dataMembers;
	}

	/*
	 * When the parser encounters plain text (not XML elements), it calls this
	 * method, which accumulates them in a string buffer
	 */
	public void characters(char[] buffer, int start, int length) {
		temp = new String(buffer, start, length);
	}

	/*
	 * Every time the parser encounters the beginning of a new element, it calls
	 * this method, which resets the string buffer
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// reset string buffer
		temp = "";
		if(indexes.isEmpty() == false) { // if indexes is NOT empty
			if(tags.isEmpty() == false) { // if tags is NOT empty
				// get specific tags from specific indexes
				if(qName.equalsIgnoreCase(dataType)) { // if a new index of this Data Type is indicated
					dataMember = newDataMember(dataType);
				} else { // we're getting a FIELD
					if(indexes.contains(currentIndex)) { // if we want this index
						if(tags.contains(field)) { // if we want this field
							field = qName; // get the field for later parsing
						} else { // we DON'T want this field
							field = null; // do nothing
						}
					} else { // we DON'T want this index
						field = null; // do nothing
					}
				}
			} else { // tags IS empty
				// get all tags from specific indexes
				if(qName.equalsIgnoreCase(dataType)) { // if a new index of this Data Type is indicated
					dataMember = newDataMember(dataType);
				} else { // we're getting a FIELD
					if(indexes.contains(currentIndex)) { // if we want this index
						field = qName; // get the field for later parsing
					} else { // we DON'T want this index
						field = null; // do nothing
					}
				}
			}
		} else { // indexes IS empty: get all indexes
			if(tags.isEmpty() == false) { // if tags is NOT empty
				// get every occurence of tags
				if(qName.equalsIgnoreCase(dataType)) { // if a new index of this Data Type is indicated
					dataMember = newDataMember(dataType);
				} else { // we're getting a FIELD
					if(tags.contains(field)) { // if we want this field
						field = qName; // get the field for later parsing
					} else { // we DON'T want this field
						field = null; // do nothing
					}
				}
			} else { // tags IS empty
				//get EVERYTHING
				if(qName.equalsIgnoreCase(dataType)) { // if a new index of this Data Type is indicated
					dataMember = newDataMember(dataType);
				} else { // we're getting a FIELD
					field = qName; // get the field for later parsing
				}
			}
		}
	}

	/**
	 * 
	 * @param dataType String representation of the DataType subclass to be returned.
	 * @return
	 */
	private DataType newDataMember(String dataType) {
		DataType d = null;
		if(dataType.equalsIgnoreCase("battle")) {
			//d = new Battle();
		} else if(dataType.equalsIgnoreCase("battleLog")) {
			//d = new BattleLog();
		} else if(dataType.equalsIgnoreCase("building")) {
			d = new Building();
		} else if(dataType.equalsIgnoreCase("buildingType")) {
			d = new BuildingType();
		} else if(dataType.equalsIgnoreCase("checkInLog")) {
			//d = new CheckInLog();
		} else if(dataType.equalsIgnoreCase("city")) {
			//d = new City();
		} else if(dataType.equalsIgnoreCase("map")) {
			//d = new Map();
		} else if(dataType.equalsIgnoreCase("mapTile")) {
			//d = new MapTile();
		} else if(dataType.equalsIgnoreCase("marketLog")) {
			//d = new MarketLog
		} else if(dataType.equalsIgnoreCase("player")) {
			//d = new Player();
		} else if(dataType.equalsIgnoreCase("resource")) {
			d = new Resource();
		} else if(dataType.equalsIgnoreCase("ticket")) {
			//d = new Ticket();
		} else if(dataType.equalsIgnoreCase("trade")) {
			//d = new Trade();
		} else if(dataType.equalsIgnoreCase("unit")) {
			d = new Unit();
		} else if(dataType.equalsIgnoreCase("unitType")) {
			d = new UnitType();
		}
		return d;
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if(qName.equalsIgnoreCase(dataType)) {
			if(dataMember.getId() == 0) { // this means the data type hasn't been properly initialized
				// do nothing
			} else {
				// add it to the list
				dataMembers.add(dataMember);
			}
			// increment currentIndex
			currentIndex++;
		} else {
			// pass temp to dataMember so it can parse
			dataMember.parse(field, temp);
		}

	}

	private void readList() {
		System.out.println("Total number of parsed data members: " + dataMembers.size() + ".");
		Iterator<DataType> it = dataMembers.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

}