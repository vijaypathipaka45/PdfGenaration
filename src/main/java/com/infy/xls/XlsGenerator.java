package com.infy.xls;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsGenerator {
	
	public static void main(String[] args) throws IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Contacts");

	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setFontHeightInPoints((short) 14);
	    headerFont.setColor(IndexedColors.RED.getIndex());

	    CellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFont(headerFont);

	    // Create a Row
	    Row headerRow = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(0);
	    
	    String []arr= {"A","B","c","d","e"};

	    for (int i = 0; i < arr.length; i++) {
	      Cell cell = headerRow.createCell(i);
	      cell.setCellValue(arr[i]);
	      cell.setCellStyle(headerCellStyle);
	    }

	    // Create Other rows and cells with contacts data
	    int rowNum = 1;
	    Row row ;
	    for (Details contact : new XlsGenerator().getDetails()) {   	
	       
	    	row =  sheet.createRow(rowNum);
	       int third=0;
	      
	      Cell cell = row.createCell(0);
	      cell.setCellValue(contact.getAmsNo());
	      int start=0;
	      for(String str:contact.getAddress()) {	    	  
	    	  if(sheet.getRow(rowNum+start)!=null) {
	    		  cell = sheet.getRow(rowNum+start).createCell(1);
	    		  cell.setCellValue(str);
	    		  start++;  
	    	  }	    	  
	    	  else {
	    		   row =  sheet.createRow(rowNum+start);
	    		   cell = sheet.getRow(rowNum+start).createCell(1);
	    	       cell.setCellValue(str);
	    	       start++;
	    	    }
	        }
	      if(start>=third) {
	    	  third=start;
	      }
 
	      if(sheet.getRow(rowNum)!=null) {
	       cell = sheet.getRow(rowNum).createCell(2);
	       cell.setCellValue(contact.getCity());
	       }
	      start=0;
	      for(String str:contact.getCountry()) {	    	  
	    	  if(sheet.getRow(rowNum+start)!=null) {
	    		  System.out.println("the row num is "+(rowNum+start+" "+str));
	    		  cell = sheet.getRow(rowNum+start).createCell(3);
	    		  cell.setCellValue(str);
	    		  start++;  
	    		 // break;
	    	  }	    	  
	    	  else {
	    		   row =  sheet.createRow(rowNum+start);
	    		   cell = sheet.getRow(rowNum+start).createCell(3);
	    	       cell.setCellValue(str);
	    	       start++;
	    	    }
	        }
	      if(start>=third) {
	    	  third=start;
	      }
	      rowNum=rowNum+third;
	      }
	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("src/main/resources/contacts.xlsx");
	    workbook.write(fileOut);
	    fileOut.close();
	  }	
		
	
	
	private  List<Details> getDetails(){
	
	List<Details> list=new ArrayList<Details>();
	
	for(int i=0;i<=2;i++) {
		Details data=new Details();
		
		data.setAmsNo("123");
		data.setCity("hyd");
		List<String> list1=new ArrayList<String>();
		list1.add("IND");
		list1.add("china");
		list1.add("US");
		list1.add("AUS");
		
		List<String> list2=new ArrayList<>();
		list2.add("telugu");
		list2.add("hindi");
		list2.add("english");
		
		List<String> list3=new ArrayList<>();
		list3.add("TS");
		list3.add("AP");
		list3.add("KS");
		
		data.setAddress(list1);;
		data.setCountry(list2);
		data.setName(list3);
		
		list.add(data);
	}
	
	return list;
	
}

}
