package excelutil;

import util.Xls_Reader;

public class GetdataSetRunmode {

	public static void main(String[] args) {
		Xls_Reader x=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\SuiteA.xlsx");
		getDataSetRunmodeTest(x, "AddProductTest");
		
	}
	

	//get testdata runmode
	public static String[] getDataSetRunmodeTest(Xls_Reader xls, String sheetName){
		String runmode[] = null;
		if(!xls.isSheetExist(sheetName)){
			xls=null;
			sheetName=null;
			runmode=new String[1];
			runmode[0]="Y";
			xls=null;
			sheetName=null;
			return runmode;
		}
		runmode=new String[xls.getRowCount(sheetName)-1];
		for(int i=2;i<=runmode.length+1;i++){
			runmode[i-2]=xls.getCellData(sheetName, "Runmode", i);
			//System.out.println(runmode[i-2]);
		}
		xls=null;
		sheetName=null;
		return runmode;
		
	}
}
