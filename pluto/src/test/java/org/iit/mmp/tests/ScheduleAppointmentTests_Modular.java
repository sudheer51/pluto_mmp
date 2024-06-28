package org.iit.mmp.tests;

import java.util.HashMap;

import org.iit.mmp.lib.BaseClass;
import org.iit.mmp.lib.MMPLibrary;
import org.iit.mmp.patientmodule.pages.HomePage;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests_Modular extends BaseClass {
	 
	@Parameters({"doctor","duration"})
	@Test
	public void validatebookAppointment(String doctor,String duration)
	{
		MMPLibrary mmpLib = new MMPLibrary(driver);

		mmpLib.launchApplication(prop.getProperty("patienturl"));
		 
		mmpLib.login(prop.getProperty("patientusername"),prop.getProperty("patientpassword"));

		mmpLib.navigateToAModule("Schedule Appointment");
		
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		 
		HashMap<String,String> expectedHMap = sPage.bookAppointment(doctor,Integer.parseInt(duration));
		
		HomePage hPage = new HomePage(driver);
		
		HashMap<String,String> actualHMap = hPage.fetchPatientTableData();

		System.out.println(expectedHMap);
		
		System.out.println(actualHMap);
		
		Assert.assertEquals(actualHMap,expectedHMap);

	}
	 
}


