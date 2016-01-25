/**
 * @author joseph_delong
 *
 */
package test;

import java.util.ArrayList;

import dataTypes.Building;
import dataTypes.BuildingType;
import dataTypes.City;
import dataTypes.Player;
import dataTypes.Resource;
import dataTypes.Unit;
import dataTypes.UnitType;
import exceptions.ASCException;

public class XMLparserTest {

	public static void main(String[] args) {
		try {
			BuildingType bt = BuildingType.getInstance(5);
			System.out.println(bt.toString());
			
			UnitType ut = UnitType.getInstance(7);
			System.out.println(ut.toString());
			
			Resource r = Resource.getInstance(8);
			System.out.println(r.toString());
			
			Building b = new Building(2, 2);
			System.out.println(b.toString());
			
			Unit u = new Unit(33);
			u = new Unit(15);
			System.out.println(u.toString());
			
			Player player = new Player();
			player.setActive(true);
			player.setAllies(null);
			player.setCities(null);
			player.setCurrentCity(null);
			player.setId(1);
			player.setName("Happy Harry");
			player.setResourceSurplus(null);
			player.setScore(0d);
			System.out.println(player.toString());
			
			City city = new City();
			city.setOwner(player);
			city.setName("Happy Town");
			city.setOffense(7);
			city.setDefense(15);
			ArrayList<Integer> resources = new ArrayList<Integer>();
			resources.add(25);
			resources.add(27);
			resources.add(32);
			resources.add(6);
			resources.add(11);
			resources.add(2);
			resources.add(0);
			resources.add(462);
			city.setResources(resources);
			city.setPopulation(217);
			city.setValue(city.getResourceValue());
			
			ArrayList<Integer> work = new ArrayList<Integer>();
			work.add(0);
			work.add(0);
			work.add(0);
			work.add(0);
			work.add(0);
			work.add(100);
			city.setAssignedWork(work);
			
			ArrayList<Unit> units = new ArrayList<Unit>();
			units.add(u);
			
			b.setOccupants(units);
			b.setGarrisonedUnits(units);
			
			ArrayList<Building> buildings = new ArrayList<Building>();
			buildings.add(b);
			
			city.setBuildings(buildings);
			
			System.out.println(city.toString());
			
		} catch (ASCException e) {
			// Do I want to handle exceptions individually?
			//   By Exception Class?
			//   Or just do it generically, with a standard sysout() call?
			System.out.println("Exception caught: " + e.getClass() + 
					"\n\tMessage: " + e.getMessage() + 
					"\n\tCause: " + e.getCause() + 
					"\n\tStackTrace: ");
			e.printStackTrace();
		}
	}
	
}
