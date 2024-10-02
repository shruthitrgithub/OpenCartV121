package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name ="LoginData")
	public String[][] getData() throws IOException{
		String path =".\\TestData\\LoginData.xlsx"; //taking XL from testData
		
	ExcelUtility xlUtil = new ExcelUtility(path);//creating an object for XLUtility
	
	 int totalRows=xlUtil.getRowCount("Sheet1");
	 int totalcols =xlUtil.getCellCount("Sheet1",1);
	 
	 
	 String loginData[][]=new String [totalRows][totalcols];//created for two dimension Array
		for(int i = 1;i<=totalRows;i++)
		{
			for (int j=0;j<totalcols;j++) 
			{
				
				loginData[i-1][j]=xlUtil.getCellData("Sheet1",i ,j);//1,0
			}
		}
		return loginData; //returning two dimension Array
				
				
				
			}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
  

