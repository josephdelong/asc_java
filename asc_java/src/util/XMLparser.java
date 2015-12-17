///**
// * 
// */
//package util;
//
///**
// * @author Joseph
// *
// */
//public class XMLparser {
//
//}
package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import asc_dataTypes.BuildingType;
import asc_dataTypes.UnitType;

public class XMLparser extends DefaultHandler {

	private BuildingType buildingType;
	private UnitType unitType;
	private String temp;
	private ArrayList<BuildingType> btList = new ArrayList<BuildingType>();
	private ArrayList<UnitType> utList = new ArrayList<UnitType>();
	private ArrayList<Integer> requiredBuildings = new ArrayList<Integer>();

	/** The main method sets things up for parsing */
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

		// Create a "parser factory" for creating SAX parsers
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		// Now use the parser factory to create a SAXParser object
		SAXParser sp = saxParserFactory.newSAXParser();

		// Create an instance of this class; it defines all the handler methods
		XMLparser parser = new XMLparser();
		
		// Finally, tell the parser to parse the input and notify the handler
		if (args.length == 0) {
//			sp.parse("src/asc_dataTypes/buildingTypes.xml", parser);
			sp.parse("src/asc_dataTypes/unitTypes.xml", parser);
		} else if (args[0].equalsIgnoreCase("BUILDING TYPE")) {
			sp.parse("src/asc_dataTypes/buildingTypes.xml", parser);
		} else if (args[0].equalsIgnoreCase("UNIT TYPE")) {
			sp.parse("src/asc_dataTypes/unitTypes.xml", parser);
		}
		parser.readList();
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
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		temp = "";
		if (qName.equalsIgnoreCase("unitType")) {
			unitType = new UnitType();
		} else if (qName.equalsIgnoreCase("requiredBuildings")) {
			requiredBuildings = new ArrayList<Integer>();
		}
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("unitType")) {
			// add it to the list
			utList.add(unitType);
		} else if (qName.equalsIgnoreCase("id")) {
			unitType.setId(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("name")) {
			unitType.setName(temp);
		} else if (qName.equalsIgnoreCase("type")) {
			unitType.setType(temp);
		} else if (qName.equalsIgnoreCase("offense")) {
			unitType.setOffense(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("defense")) {
			unitType.setDefense(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("foodCost")) {
			unitType.setFoodCost(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("woodCost")) {
			unitType.setWoodCost(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("stoneCost")) {
			unitType.setStoneCost(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("ironCost")) {
			unitType.setIronCost(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("cottonCost")) {
			unitType.setCottonCost(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("silkCost")) {
			unitType.setSilkCost(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("goldCost")) {
			unitType.setGoldCost(Integer.parseInt(temp));
		} else if (qName.equalsIgnoreCase("special")) {
			unitType.setSpecial(Boolean.parseBoolean(temp));
		} else if (qName.isEmpty() == false && qName.equalsIgnoreCase("image")) {
			unitType.setImage(new File(temp));
		} else if (qName.equalsIgnoreCase("description")) {
			unitType.setDescription(temp);
		} else if (qName.equalsIgnoreCase("requiredBuildings")) {
			unitType.setRequiredBuildings(requiredBuildings);
		} else if (qName.equalsIgnoreCase("requiredBuilding")) {
			requiredBuildings.add(Integer.parseInt(temp));
		}

	}

	private void readList() {
		System.out.println("Total number of Unit Types: " + utList.size() + ".");
		Iterator<UnitType> it = utList.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

}