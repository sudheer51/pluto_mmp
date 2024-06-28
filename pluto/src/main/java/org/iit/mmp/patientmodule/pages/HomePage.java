package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	protected WebDriver driver;

	@FindBy(xpath="//table[@class='table']/tbody/tr[1]/td[1]") 
	private WebElement dateXpath;

	@FindBy(xpath="//table[@class='table']/tbody/tr[1]/td[2]") 
	private WebElement timeXpath;

	@FindBy(xpath="//table[@class='table']/tbody/tr[1]/td[3]") 
	private WebElement appointmentXpath;

	@FindBy(xpath="//abc") 
	private WebElement doctorXpath;

	//private By dateXpath  = By.xpath("//table[@class='table']/tbody/tr[1]/td[1]");
	//private By timeXpath = By.xpath("//table[@class='table']/tbody/tr[1]/td[2]");
	//private By appointmentXpath =By.xpath("//table[@class='table']/tbody/tr[1]/td[3]");
	//private By doctorXpath= By.xpath("//table[@class='table']/tbody/tr[1]/td[4]");

	public HomePage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("home")) 
		{
			throw new IllegalStateException("This is not HomePage for Login User current page is: " + driver.getCurrentUrl());
		}
		else
		{
			PageFactory.initElements(driver, HomePage.class);
		}
	}
	public HashMap<String, String> fetchPatientTableData()
	{
		String date = dateXpath.getText();
		String time= timeXpath.getText();
		String appointment= appointmentXpath.getText();
		String doctor = doctorXpath.getText();
		System.out.println(date+"---"+time+"---"+ appointment+"--"+ doctor);
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("date", date);
		actualHMap.put("time", time);
		actualHMap.put("appointment", appointment);
		actualHMap.put("doctor", doctor);
		return actualHMap;

	}

}
