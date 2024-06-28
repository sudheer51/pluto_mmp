package org.iit.mmp.tests;

public class CarMake {

	public static void main(String[] args) {
		
		String[] cars = new String[5];
		
		cars [0]= "toyota";
		cars[1] = "Jeep";
		cars [2]= "Kia";
		cars[3] = "Mazda";
		cars[4] = "Benz";
		
		System.out.println("Total Cars Present : "+cars.length);
		System.out.println("cars[4] "+cars[4]);
		
		for(String carmake : cars)
		{
			System.out.println(carmake);
		}
		
		
		
	}

}
