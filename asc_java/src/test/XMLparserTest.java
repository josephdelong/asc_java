/**
 * @author joseph_delong
 *
 */
package test;

import java.util.ArrayList;

import dataTypes.BattleLog;
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
			BattleLog bl = new BattleLog();
			bl.setBattleId(134);
			bl.setDamageGiven(15);
			bl.setId(356);
			bl.setLocationStart(5);
			bl.setLocationEnd(7);
			bl.setTarget(1);
			bl.setDamageTaken(15);
			bl.setTargetLocation(0);
			bl.setTurnAction(2);
			bl.setUnitId(1);
			
			System.out.println(bl.printTurn());
			
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
