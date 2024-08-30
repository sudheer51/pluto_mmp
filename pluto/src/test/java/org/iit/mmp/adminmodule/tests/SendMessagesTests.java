package org.iit.mmp.adminmodule.tests;

import java.util.HashMap;

import org.iit.mmp.lib.BaseClass;
import org.iit.mmp.lib.MMPLibrary;
import org.iit.mmp.lib.Utility;
import org.iit.mmp.patientmodule.pages.EditProfilePage;
import org.iit.mmp.patientmodule.pages.SendMessagesPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/**
 * 1. Login to Patient Module.
2. navigate to messages module.
3. fill the contact reason and subject.
4. Click on send button.
5. Click on "Ok" button to accept the pop-up.
6. Validate the message displayed in the pop-up.
	expected HMap
       ==> Fetch the patient name from profile tab.
	   =>  contact reason and subject 
	   =>  future date -> dd-MM-YYYY
7. Logout of the Patient Module.
8. Login to the Admin Module.
9. navigate to messages module.
10. verify Patient Name,Contact REason,Subject & Date are displayed as 
    as expected.
actual HMap 
ria	Meet doctor James	    30-08-2024
     Monthly Visit

Compare both the HashMap..
 * @author sudhe
 *
 */

public class SendMessagesTests extends BaseClass{
	@Test
	public void validateSendMessages()
	{
		MMPLibrary mmpLib = new MMPLibrary(driver);
		mmpLib.launchApplication(prop.getProperty("patienturl"));
		mmpLib.login(prop.getProperty("patientusername"),prop.getProperty("patientpassword"));
		mmpLib.navigateToAModule("Profile");
		EditProfilePage editProfile = new EditProfilePage(driver);
		
		String fName = editProfile.fetchPatientFName();
		String contactReason = "Meet Doctor"+Utility.generateRandomString();
		String subject = "Monthly Visit"+Utility.generateRandomString();
		String todayDate = Utility.generateFutureDate(0, "dd-MM-YYYY");
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		expectedHMap.put("fName", fName);
		expectedHMap.put("contactReason", contactReason);
		expectedHMap.put("subject", subject);
		expectedHMap.put("todayDate", todayDate);
		
		System.out.println("Expected HashMap:::::::::" + expectedHMap);
		
		//Messages Successfully sent.
		mmpLib.navigateToAModule("Messages");
		SendMessagesPage msg = new SendMessagesPage(driver);
		String actual = msg.sendMessages(contactReason,subject);
		String expected ="Messages Successfully sent.";
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actual, expected);
		mmpLib.logout();
		
		
		mmpLib.launchApplication(prop.getProperty("adminurl"));
		mmpLib.login(prop.getProperty("adminusername"),prop.getProperty("adminpassword"));
		mmpLib.navigateToAModule("Messages");
		
//		HashMap<String,String> actualHMap = msgAdminPage.fetchMessageDetails();
//		
//		sa.assertEquals(actualHMap, expectedHMap);
		
		sa.assertAll();
		
	}

}
