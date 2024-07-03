package org.iit.mmp.tests;

import java.util.ArrayList;

public class CarMake {

	public static void main(String[] args) {
		
		ArrayList<String> cars = new ArrayList<String>();
		
		cars.add("toyota");
		cars.add("Jeep");
		cars.add("Kia");
		cars.add("Mazda");
		cars.add("Benz");
		
		System.out.println("Total Cars Present : "+cars.size());
		System.out.println("cars[4] "+cars.get(4));
		
		for(String carmake : cars)
		{
			System.out.println(carmake);
		}
		
		
		
	}

}
