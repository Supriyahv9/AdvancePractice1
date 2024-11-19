package com.comcast.crm.generic.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
public String getDataFromExcelFile(String sheetname,int rownum,int colnum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./ConfigAppData/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getStringCellValue();
	return data;
	
	
	}
	
	public void setDataIntoCell(String sheetname,int rownum,int colnum) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./ConfigAppData/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		 wb.getSheet(sheetname).getRow(rownum).createCell(colnum);
		 
		 FileOutputStream fos = new FileOutputStream("./ConfigAppData/testscriptdata.xlsx");
			wb.write(fos);
			wb.close();
	}

}
