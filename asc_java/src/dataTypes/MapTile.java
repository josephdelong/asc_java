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
public class MapTile extends DataType {

	/**
	 * Private data members
	 */
	private int id;
	private int mapId;
	private int xPos;
	private int yPos;
	private int terrainType;
	private boolean blocking;
	private ArrayList<Integer> terrainObjects;
	private Integer building;
	private ArrayList<Integer> units;
	private ArrayList<Integer> miscObjects;
	
	/**
	 * /**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public MapTile() {
		this.setId(0);
		this.setMapId(0);
		this.setXPos(0);
		this.setYPos(0);
		this.setTerrainType(0);
		this.setBlocking(false);
		this.setTerrainObjects(null);
		this.setBuilding(null);
		this.setUnits(null);
		this.setMiscObjects(null);
	}
	
	/**
	 * 
	 * @param mapId
	 */
	public MapTile(int mapId) {
		
	}
	
	/**
	 * Constructor which sets up this MapTile with the data members specified. 
	 * All parameters but mapId, xPos, & yPos may be null.
	 * @param mapId
	 * @param xPos
	 * @param yPos
	 * @param terrainType
	 * @param blocking
	 * @param terrainObjects
	 * @param building
	 * @param units
	 * @param miscObjects
	 */
	public MapTile(int mapId, int xPos, int yPos, int terrainType, boolean blocking, 
			ArrayList<Integer> terrainObjects, Building building, ArrayList<Unit> units, 
			ArrayList<Integer> miscObjects) {
		
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
		} else if(fieldName.equalsIgnoreCase("mapId")) {
			this.setMapId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("xPos")) {
			this.setXPos(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("yPos")) {
			this.setYPos(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("terrainType")) {
			this.setTerrainType(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("blocking")) {
			this.setBlocking(Boolean.parseBoolean(value));
		} else if(fieldName.equalsIgnoreCase("terrainObjects")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("terrainObject")) {
			this.addTerrainObject(Integer.parseInt(attribute), Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("building")) {
			this.setBuilding(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("units")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("unit")) {
			this.addUnit(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("miscObjects")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("miscObject")) {
			this.addMiscObject(Integer.parseInt(attribute), Integer.parseInt(value));
		}
	}

	/**
	 * Method which returns an instance of MapTile based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of MapTile you are looking for.
	 * @return MapTile associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static MapTile getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> mapTiles = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			mapTiles = parser.parse("src/datastore/mapTiles.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get MapTile instance lookup: " + instanceId, e);
		}
		
		Iterator<DataType> it = mapTiles.iterator();
		if(it.hasNext()) {
			return (MapTile)it.next();
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
		fields.add("mapId");
		fields.add("xPos");
		fields.add("yPos");
		fields.add("terrainType");
		fields.add("blocking");
		fields.add("terrainObjects");
		fields.add("building");
		fields.add("units");
		fields.add("miscObjects");
		return fields;
	}

	/**
	 * @return the id
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/**
	 * @return the mapId
	 */
	public int getMapId() {
		return mapId;
	}

	/**
	 * @return the xPos
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getYPos() {
		return yPos;
	}

	/**
	 * @return the terrainType
	 */
	public int getTerrainType() {
		return terrainType;
	}

	/**
	 * @return the blocking
	 */
	public boolean getBlocking() {
		return blocking;
	}

	/**
	 * @return the terrainObjects
	 */
	public ArrayList<Integer> getTerrainObjects() {
		return terrainObjects;
	}

	/**
	 * @return the building
	 */
	public Integer getBuilding() {
		return building;
	}

	/**
	 * @return the units
	 */
	public ArrayList<Integer> getUnits() {
		return units;
	}

	/**
	 * @return the miscObjects
	 */
	public ArrayList<Integer> getMiscObjects() {
		return miscObjects;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param mapId the mapId to set
	 */
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * @param terrainType the terrainType to set
	 */
	public void setTerrainType(int terrainType) {
		this.terrainType = terrainType;
	}

	/**
	 * @param blocking the blocking to set
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	/**
	 * @param terrainObjects the terrainObjects to set
	 */
	public void setTerrainObjects(ArrayList<Integer> terrainObjects) {
		this.terrainObjects = terrainObjects;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(Integer building) {
		this.building = building;
	}

	/**
	 * @param units the units to set
	 */
	public void setUnits(ArrayList<Integer> units) {
		this.units = units;
	}

	/**
	 * @param miscObjects the miscObjects to set
	 */
	public void setMiscObjects(ArrayList<Integer> miscObjects) {
		this.miscObjects = miscObjects;
	}
	
	/**
	 * Adds the specified Terrain object to this MapTile with the specified Type and Subtype
	 * @param objectSubtype
	 * @param objectType
	 */
	private void addTerrainObject(int objectSubtype, int objectType) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adds the specified Unit (if valid) to this MapTile's units ArrayList
	 * @param unitId
	 */
	private void addUnit(int unitId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adds a miscellaneous (scenery) MapObject to this MapTile, with the specified Type and Subtype
	 * @param objectSubtype
	 * @param objectType
	 */
	private void addMiscObject(int objectSubtype, int objectType) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapTile:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("mapId=");
		builder.append(mapId);
		builder.append("\n\t");
		builder.append("xPos=");
		builder.append(xPos);
		builder.append("\n\t");
		builder.append("yPos=");
		builder.append(yPos);
		builder.append("\n\t");
		builder.append("terrainType=");
		builder.append(terrainType);
		builder.append("\n\t");
		builder.append("blocking=");
		builder.append(blocking);
		builder.append("\n\t");
		builder.append("terrainObjects=");
		builder.append(terrainObjects);
		builder.append("\n\t");
		builder.append("building=");
		builder.append(building);
		builder.append("\n\t");
		builder.append("units=");
		builder.append(units);
		builder.append("\n\t");
		builder.append("miscObjects=");
		builder.append(miscObjects);
		return builder.toString();
	}

}
