/**
 * @author joseph_delong
 *
 */
package test;

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
			
			City city = new City(0, "Happy Town", "Happy Hall", "Happy Hogan", null);
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
