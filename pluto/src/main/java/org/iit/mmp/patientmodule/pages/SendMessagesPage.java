package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SendMessagesPage {
	
	protected WebDriver driver;
	private By contactreasonTxtBox  = By.id("subject");
	private By subjectTxtArea = By.id("message");
	private By sendButton =By.xpath("//input[@value='Send']");
 
	
	public SendMessagesPage(WebDriver driver){
	    this.driver = driver;
	    if (!driver.getTitle().equals("Send Messages")) 
	    {
	      throw new IllegalStateException("This is not Messages for Login User current page is: " + driver.getCurrentUrl());
	    }
	}
	public String sendMessages(String contactReason,String subjectTxt)
	{
		driver.findElement(contactreasonTxtBox).sendKeys(contactReason);
		driver.findElement(subjectTxtArea).sendKeys(subjectTxt);
		driver.findElement(sendButton).click();
		
		Alert alrt = driver.switchTo().alert();
		String msg = alrt.getText();
		alrt.accept();
		return msg;
		
	}
}
