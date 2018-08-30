package excelutil;

import util.Xls_Reader;

public class DataResult {

	public static void main(String[] args) {
		Xls_Reader x=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\SuiteA.xlsx");
		dataSetResult(x, "AddProductTest", 2, "Pass");
		dataSetResult(x, "AddProductTest", 3, "Fail");
	}
	
	public static void dataSetResult(Xls_Reader xls,String testCaseName,int row,String result){
		xls.setCellData(testCaseName, "Results", row, result);
	}

}
