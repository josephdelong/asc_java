/**
 * @author joseph_delong
 *
 */
package test;

import dataTypes.BuildingType;
import dataTypes.City;
import dataTypes.Resource;
import dataTypes.UnitType;
import exceptions.ASCException;
import exceptions.BuildingAtMaxGarrisonException;
import exceptions.DataSourceParseException;
import exceptions.InvalidBuildingProductionTypeException;
import exceptions.UnitNotFoundException;
import exceptions.UnitNotFoundInBuildingException;

/**
 * 
 */
public class XMLparserTest {

	public static void main(String[] args) {
		try {
//			BuildingType bt = BuildingType.getInstance(5);
//			System.out.println(bt.toString());
//			UnitType ut = UnitType.getInstance(7);
//			System.out.println(ut.toString());
//			Resource r = Resource.getInstance(8);
//			System.out.println(r.toString());
			City city = new City(0, "Happy Town", "Happy Hall", "Happy Hogan", null);
			System.out.println(city.toString());
		} catch (ASCException e) {
			// Do I want to handle exceptions individually?
			//   By Exception Class?
			//   Or just do it generically, with a standart sysout() call?
			if(e.getClass() == new BuildingAtMaxGarrisonException(null, null).getClass()) {
				// TODO: handle BuildingAtMaxGarrisonException
			}
			if(e.getClass() == new DataSourceParseException(null, null).getClass()) {
				// TODO: handle DataSourceParseException
			}
			if(e.getClass() == new InvalidBuildingProductionTypeException(null).getClass()) {
				// TODO: handle InvalidBuildingProductionTypeException
			}
			if(e.getClass() == new UnitNotFoundException(null, null).getClass()) {
				// TODO: handle UnitNotFoundException
			}
			if(e.getClass() == new UnitNotFoundInBuildingException(null, null).getClass()) {
				// TODO: handle UnitNotFoundInBuildingException
			}
		}
	}
	
}
