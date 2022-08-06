//package com.UserMgtSystem.UserMgtSystem.utils;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.UserMgtSystem.UserMgtSystem.Entities.Country;
//
//public class FileUploadHelper {
//	
//		  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//		  static String[] HEADERs = { "id", "country_code", "country_name", "short_name" };
//		  static String SHEET = "ExcelCountryList";
//		  public static boolean hasExcelFormat(MultipartFile file) {
//			  System.out.println("We got here"+file);
//		    if (!TYPE.equals(file.getContentType())) {
//		        System.out.println("we are falss>> ");
//		      return false;
//		 
//		    }
//		    return true;
//		  }
//		  public static List<Country> excelToTutorials(InputStream is) {
//		    try {
//		    	 System.out.println(" The main method");
//		      Workbook workbook = new XSSFWorkbook(is);
//		      Sheet sheet = workbook.getSheet(SHEET);
//		      System.out.println("In Row iterator");
//		      Iterator<Row> rows = sheet.iterator();
//		      System.out.println("In Row iterator22");
//		      System.out.println("her..."+rows);
//		      List<Country> tutorials = new ArrayList<Country>();
//		      int rowNumber = 0;
//		      System.out.println("here.. row number"+rowNumber);
//		      while (rows.hasNext()) {
//		    	  System.out.println(rowNumber);
//		        Row currentRow = rows.next();
//		        // skip header
//		        if (rowNumber == 0) {
//		          rowNumber++;
//		          System.out.println("We are here");	
//		          continue;
//		        }
//		        Iterator<Cell> cellsInRow = currentRow.iterator();
//		        Country tutorial = new Country();
//		        int cellIdx = 0;
//		        while (cellsInRow.hasNext()) {
//		          Cell currentCell = cellsInRow.next();
//		          switch (cellIdx) {
//		          case 0:
//		            tutorial.setId((int) currentCell.getNumericCellValue());
//		            break;
//		          case 1:
//		            tutorial.setCountryCode(SHEET);
//		            break;
//		          case 2:
//		            tutorial.setCountryName(SHEET);
//		            break;
//		          case 3:
//		            tutorial.setShortCode(SHEET);
//		            break;
//		          default:
//		            break;
//		          }
//		          cellIdx++;
//		        }
//		        tutorials.add(tutorial);
//		      }
//		      workbook.close();
//		      return tutorials;
//		    } catch (IOException e) {
//		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//		    }
//		  }
//		}
//
//
