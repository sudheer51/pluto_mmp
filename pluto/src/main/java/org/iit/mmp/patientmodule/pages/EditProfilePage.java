package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditProfilePage {
	
	protected WebDriver driver;
	private By fnameTxtBox  = By.id("fname");
	 
	
	public EditProfilePage(WebDriver driver){
	    this.driver = driver;
	    if (!driver.getTitle().equals("profile")) 
	    {
	      throw new IllegalStateException("This is not Messages for Login User current page is: " + driver.getCurrentUrl());
	    }
	}
	public String fetchPatientFName()
	{
	 
		return 	driver.findElement(fnameTxtBox).getAttribute("value");
		
	}

}
