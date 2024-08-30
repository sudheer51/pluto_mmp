package org.iit.mmp.lib;

import java.util.Random;

public class RandomEx {
	
	public static void main(String[] args) {
	 
		String result = generateRandomEmailID();
		System.out.println(result);
		char c ='A';
		String str =c+"";
		
	}

	public static String generateRandomEmailID()
	{
		
		Random rand = new Random();
		int n = rand.nextInt(1000);//0 to 999
		System.out.println(n);
		
		int upperCaseChar = 65+ rand.nextInt(26);
		System.out.println((char)upperCaseChar);
		
		int lowerCaseChar = 97+ rand.nextInt(26);
		System.out.println((char)lowerCaseChar);
		
		int specialChar = 33+ rand.nextInt(14);
		System.out.println((char)specialChar);
		
		String randEmailID =(char)upperCaseChar+(char)lowerCaseChar+n+(char)specialChar+"";
		System.out.println(randEmailID);
		
		return randEmailID;
	}
}
