package com.mb.generic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelUtilities
{
	static final String filepath = "./testData/Login Data.xlsx";
	public static String readdata(String sheetname, int rownum,int cellnum)
	{
		String value = null;
		try
		{
			WorkbookFactory.create(new FileInputStream(new File(filepath)));
		}
		catch (EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		catch (InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return value;
	}
}