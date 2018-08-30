package excelutil;

import util.Xls_Reader;

public class Getdata{
	public static void main(String[] arg){
		Xls_Reader x=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\SuiteA.xlsx");
		getData(x, "AddProductTest");
	}
	
	
	public static Object[][] getData(Xls_Reader xls,String testcaseName){
		if(!xls.isSheetExist(testcaseName)){
			return new Object[1][0];
		}
		int rowcount=xls.getRowCount(testcaseName);
		int colcount=xls.getColumnCount(testcaseName);
		Object[][] data=new Object[rowcount-1][colcount-3];
		for(int row=2;row<=rowcount;row++){
			for(int col=0;col<colcount-3;col++){
			data[row-2][col]=xls.getCellData(testcaseName, col, row);
			}
		}
		return data;
		}
}