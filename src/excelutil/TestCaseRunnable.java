package excelutil;

import util.Xls_Reader;

public class TestCaseRunnable {

	public static void main(String[] args) {
	Xls_Reader x=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\SuiteA.xlsx");
	Xls_Reader xa=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\SuiteB.xlsx");
	

	System.out.println(isCaseRunnable(x, "AddProductTest"));
	System.out.println(isCaseRunnable(x, "CheckProductDisplay"));
	System.out.println(isCaseRunnable(xa, "CheckProductDisplay"));
	System.out.println(isCaseRunnable(xa, "AddProductTest"));
	}

	public static boolean isCaseRunnable(Xls_Reader xls,String testCase){
		boolean isExeutable=false;
		
		for(int i=2;i<=xls.getRowCount("Testcases");i++){
			String tc_Name=xls.getCellData("Testcases", "TCName", i);
			String tcRunmode=xls.getCellData("Testcases", "Runmode", i);
		if(tc_Name.equalsIgnoreCase(testCase)){
			if(tcRunmode.equalsIgnoreCase("y")){
			isExeutable=true;
		}
		else{
			isExeutable=false;
		}}
		}
		return isExeutable;
		
	}
}
