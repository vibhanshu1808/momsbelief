package com.mb.generic;
import java.io.File;
/**
 * @author Vibhanshu
 * @created on 28/09/2018
 * @reviewBy Vibhanshu
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.nio.file.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelUtilities 
{
static final String filepath="./testdata/data.xlsx";
/**
 * @description read data from testdata.xlsx
 * @param sheetname
 * @param rownum
 * @param cellnum
 * @return
 */
public static String readdata(String sheetname, int rownum,int cellnum)
{
String value=null;
try
{
	Workbook wb = WorkbookFactory.create(new FileInputStream(new File(filepath)));
	value=wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
}
catch(EncryptedDocumentException e)
{
	e.printStackTrace();
}
catch(InvalidFormatException e)
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
