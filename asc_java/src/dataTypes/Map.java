/**
 * @author Joseph DeLong
 *
 */
package dataTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exceptions.ASCException;
import exceptions.DataSourceParseException;
import util.XMLparser;

/**
 *
 */
public class Map extends DataType {
	
	/**
	 * Private data members
	 */
	private int id;
	private int mapType;
	private ArrayList<MapTile> mapTiles;
	private int regionId;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Map() {
		this.setId(0);
		this.setMapType(0);
		this.setMapTiles(null);
		this.setRegionId(0);
	}
	
	/**
	 * Constructor which sets up an instance of Map with the specified May type and Region.
	 * @param mapType
	 * @param regionId
	 */
	public Map(int mapType, int regionId) {
		
	}

	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 * @throws ASCException 
	 */
	@Override
	public void parse(String fieldName, String attribute, String value) throws ASCException {
		if(fieldName == null || fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("mapType")) {
			this.setMapType(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("mapTiles")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("mapTile")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.addMapTile(Integer.parseInt(s));
			}
		} else if(fieldName.equalsIgnoreCase("regionId")) {
			this.setRegionId(Integer.parseInt(value));
		}
	}

	/**
	 * Method which returns an instance of Map based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of Map you are looking for.
	 * @return Map associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static Map getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> maps = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			maps = parser.parse("src/datastore/maps.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
//			throw new DataSourceParseException("Get Map instance lookup: " + instanceId, e);
			return null;
		}
		
		Iterator<DataType> it = maps.iterator();
		if(it.hasNext()) {
			return (Map)it.next();
		} else {
			return null;
		}
	}

	/**
	 * Method which returns this Data Type's fields.
	 * @return ArrayList of Strings containing the names of this Data Type's fields. 
	 */
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("mapType");
		fields.add("mapTiles");
		fields.add("regionId");
		return fields;
	}

	/**
	 * @return the id
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * @return the mapType
	 */
	public int getMapType() {
		return mapType;
	}

	/**
	 * @return the mapTiles
	 */
	public ArrayList<MapTile> getMapTiles() {
		return mapTiles;
	}

	/**
	 * @return the regionId
	 */
	public int getRegionId() {
		return regionId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param mapType the mapType to set
	 */
	public void setMapType(int mapType) {
		this.mapType = mapType;
	}

	/**
	 * @param mapTiles the mapTiles to set
	 */
	public void setMapTiles(ArrayList<MapTile> mapTiles) {
		this.mapTiles = mapTiles;
	}

	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	/**
	 * Adds the specified MapTile to this Map
	 * @param mapTileId
	 * @throws DataSourceParseException 
	 */
	protected void addMapTile(int mapTileId) throws DataSourceParseException {
		ArrayList<MapTile> temp = this.getMapTiles();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			temp = new ArrayList<MapTile>();
		}
		temp.add(MapTile.getInstance(mapTileId));
		this.setMapTiles(temp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Map:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append(", mapType=");
		builder.append(mapType);
		builder.append("\n\t");
		builder.append(", mapTiles=");
		builder.append(mapTiles);
		builder.append("\n\t");
		builder.append(", regionId=");
		builder.append(regionId);
		return builder.toString();
	}

}
