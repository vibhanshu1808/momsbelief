package com.mb.pom;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mb.generic.MomsDriver;
import com.mb.generic.ReadConfig;

public class LoginPage extends MomsDriver
{

@FindBy(name="email")
private WebElement unTxBx;
@FindBy(name="password")
private WebElement pwdTxBx;
@FindBy(name="ladda-label")
private WebElement loginBtn;
MomsDriver md = new MomsDriver();
public LoginPage() {
	try {
		invokeBrowser("firefox");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	PageFactory.initElements(driver,this);
	
}

public void login(String username, String password)
{
	unTxBx.sendKeys(username);
	pwdTxBx.sendKeys(password);
	loginBtn.click();
}
public WebElement getUnTxBx() {
	return unTxBx;
}
public WebElement getPwdTxBx() {
	return pwdTxBx;
}
public WebElement getLoginBtn() {
	return loginBtn;

}
}