package EmailValidation.Email;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @Test(dataProvider = "getData")
    
    public void testGmail(String fileName, String subject)
    
    {
    	 HashMap<String, String> hm = GMail.getGmailData("subject:"+subject,fileName);
	        System.out.println(hm.get("subject"));
	        System.out.println("=================");
	        System.out.println(hm.get("body"));
	        
	        GMail.DeleteCredsFile();

    }
    
    
    @DataProvider(name ="getData")
    public static Object[][] getData() throws IOException {
    	FileInputStream fileInputStream= new FileInputStream(System.getProperty("user.dir") + File.separator + "src" +
    	         File.separator + "main" +
    	         File.separator + "java" + 
    	         File.separator + "Data" +
    	         File.separator + "InputDataSheet.xlsx"); //Excel sheet file location get mentioned here
    	         XSSFWorkbook workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
    	         XSSFSheet  worksheet=workbook.getSheet("Sheet1");// get my sheet from workbook
    	         XSSFRow Row=worksheet.getRow(0);   //get my Row which start from 0   
    	   
    	    	int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
    	    	int ColNum= Row.getLastCellNum(); // get last ColNum 
    	    	
    	    	Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
    	    	
    	     for(int i=0; i<RowNum-1; i++) //Loop work for Rows
    	     {  
    	     XSSFRow row= worksheet.getRow(i+1);
    	     
    	     for (int j=0; j<ColNum; j++) //Loop work for colNum
    	     {
    	     if(row==null)
    	     Data[i][j]= "";
    	     else 
    	     {
    	     XSSFCell cell= row.getCell(j);
    	     if(cell==null)
    	     Data[i][j]= ""; //if it get Null value it pass no data 
    	     else
    	     {
    	     Data[i][j]=cell.getStringCellValue(); //This formatter get my all values as string i.e integer, float all type data value
    	     }
    	     }
    	     }
    	     }
    	 
    	    	return Data;
    	    }
    }

