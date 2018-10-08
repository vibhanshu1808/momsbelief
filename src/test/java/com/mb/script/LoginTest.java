package com.mb.script;

import org.testng.annotations.Test;

import com.mb.generic.ExcelUtilities;
import com.mb.generic.MomsDriver;
import com.mb.pom.LoginPage;

public class LoginTest{
LoginPage lp=new LoginPage();

@Test
public void validLogin()
{
	String username = ExcelUtilities.readdata("Sheet1",2,1);
	String password = ExcelUtilities.readdata("Sheet1",2,2);
	lp.login(username,password);
}
}
