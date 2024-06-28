package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.mmp.lib.Helper;
import org.iit.mmp.lib.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {

	protected WebDriver driver;
	private By newAppointmentButton  = By.xpath("//input[@value='Create new appointment']");
	private By datePicketID = By.id("datepicker");
	private By datePickerYear =By.className("ui-datepicker-year");
	private By nextButton= By.xpath("//span[text()='Next']");
	private By datePickerMonth =By.className("ui-datepicker-month");
	
	public ScheduleAppointmentPage(WebDriver driver){
	    this.driver = driver;
	    if (!driver.getTitle().equals("Shedule Appointments")) 
	    {
	      throw new IllegalStateException("This is not ScheduleAppointment for Login User current page is: " + driver.getCurrentUrl());
	    }
	}

	public HashMap<String, String> bookAppointment(String doctorName,int noofDays)
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		driver.findElement(newAppointmentButton).click();
		
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();
		expectedHMap.put("doctor", doctorName);

		driver.switchTo().frame("myframe");
		String date = Utility.generateFutureDate(noofDays,"dd/MMMM/YYYY");
		String dateArr[] = date.split("/");
		expectedHMap.put("date", date);

		System.out.println(dateArr[0]);//11
		System.out.println(dateArr[1]);//August
		System.out.println(dateArr[2]);//2025
		driver.findElement(datePicketID).click();
		String expectedDay = dateArr[0];
		String expectedMonth = dateArr[1];
		String expectedYear = dateArr[2];
		String actualYear = driver.findElement(datePickerYear ).getText();//2024

		while(!(actualYear.equals(expectedYear)))
		{
			driver.findElement(nextButton).click();
			actualYear = driver.findElement(datePickerYear).getText();
		}
		String actualMonth = driver.findElement(datePickerMonth).getText();//June
		while(!(actualMonth.equals(expectedMonth)))
		{
			driver.findElement(nextButton).click();
			actualMonth = driver.findElement(datePickerMonth).getText();
		}
		driver.findElement(By.linkText(expectedDay)).click();
		String time="10Am";
		new Select(driver.findElement(By.id("time"))).selectByValue(time);
		expectedHMap.put("time", time);
		Helper helperObj = new Helper();
		helperObj.waitforElement(driver,40,By.id("status"));
		helperObj.waitforText(driver,driver.findElement(By.id("status")),"OK",30);

		driver.findElement(By.id("ChangeHeatName")).click();
		driver.switchTo().defaultContent();

		helperObj.waitforElement(driver,40,By.id("sym"));	

		String appointment="To meet doctor for Checkup";
		driver.findElement(By.id("sym")).sendKeys(appointment);
		expectedHMap.put("appointment", appointment);

		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		return expectedHMap;
	}

}
