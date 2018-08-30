package excelutil;

import util.Xls_Reader;

public class TestSuiteRunmode {

	public static void main(String[] args) {
		Xls_Reader x=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\Suite.xlsx");
		//System.out.println(x.getCellData("SuiteName", 0, 2));
		System.out.println(isSuiteRunnable(x, "SuiteA"));
		System.out.println(isSuiteRunnable(x, "SuiteB"));
		System.out.println(isSuiteRunnable(x, "SuiteC"));

	}
	
	public static boolean isSuiteRunnable(Xls_Reader xls,String suitename){
		boolean isExecutable=false;
		for(int i=2;i<=xls.getRowCount("Suitename");i++){
			String suit_name=xls.getCellData("Suitename", 0, i);
			String runMode=xls.getCellData("Suitename", 2, i);
			if(suit_name.equalsIgnoreCase(suitename)){
				if(runMode.equalsIgnoreCase("y")){
					isExecutable=true;
				}
				else{
					isExecutable=false;
				}
			}
		}
		
		return isExecutable;
		
	}

}
