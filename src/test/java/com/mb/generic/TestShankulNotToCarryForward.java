package com.mb.generic;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;


public class TestShankulNotToCarryForward
{
    static final String filepath = ".\\testData\\Login_Data.xlsx";

    public static void main(String []args)
    {
        try {
            Workbook workbook = WorkbookFactory.create(new File(filepath));
            DataFormatter formatter = new DataFormatter();
            String value=formatter.formatCellValue(workbook.getSheet("Sheet1").getRow(2).getCell(1));
            //String value=workbook.getSheet("Sheet1").getRow(2).getCell(1).getStringCellValue().toString();
            System.out.println("hogya"+ value);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
