package util;

public class Utility {

	/*
	 * The below method checks the runmode of suite
	 */
	public static boolean isSuiteRunnable(Xls_Reader xls, String suitename) {
		boolean isExecutable = false;
		for (int i = 2; i <= xls.getRowCount("Suitename"); i++) {
			String suit_name = xls.getCellData("Suitename", 0, i);
			String runMode = xls.getCellData("Suitename", 2, i);
			System.out.println("suit_name: "+ suit_name);
			System.out.println("runMode: "+ runMode);
			System.out.println("suitename: "+ suitename);
			if (suit_name.equalsIgnoreCase(suitename)) {
				if (runMode.equalsIgnoreCase("y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}
		}

		return isExecutable;

	}

	/*
	 * The below method checks the runmode of testcase
	 */
	public static boolean isCaseRunnable(Xls_Reader xls, String testCase) {
		boolean isExeutable = false;
		for (int i = 2; i <= xls.getRowCount("Testcases"); i++) {
			String tc_Name = xls.getCellData("Testcases", "TCName", i);
			String tcRunmode = xls.getCellData("Testcases", "Runmode", i);
			if (tc_Name.equalsIgnoreCase(testCase)) {
				if (tcRunmode.equalsIgnoreCase("y")) {
					isExeutable = true;
				} else {
					isExeutable = false;
				}
			}
		}
		return isExeutable;

	}

	/*
	 * The below method gets the test data for the testcases
	 */
	public static Object[][] getData(Xls_Reader xls, String testcaseName) {
		if (!xls.isSheetExist(testcaseName)) {
			return new Object[1][0];
		}
		int rowcount = xls.getRowCount(testcaseName);
		int colcount = xls.getColumnCount(testcaseName);
		Object[][] data = new Object[rowcount - 1][colcount - 3];
		for (int row = 2; row <= rowcount; row++) {
			for (int col = 0; col < colcount - 3; col++) {
				data[row - 2][col] = xls.getCellData(testcaseName, col, row);
			}
		}
		return data;
	}

	/*
	 * The below method updates the results in result column of excel sheet
	 */
	public static void dataSetResult(Xls_Reader xls, String testCaseName,
			int row, String result) {
		xls.setCellData(testCaseName, "Results", row, result);
	}
	
	
	public static void automatedDataSet(Xls_Reader xls, String testCaseName,
			int row, String result) {
		xls.setCellData(testCaseName, "DataUsedForAutomation", row, result);
	}

	//get testdata runmode
		public static String[] getDataSetRunmodeTest(Xls_Reader xls, String sheetName){
			String runmode[] = null;
			System.out.println(sheetName);
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
				System.out.println(runmode[i-2]);
			}
			xls=null;
			sheetName=null;
			return runmode;
		}
		
		//get row number
				public static int getRowNum(Xls_Reader xls,String testcaseName){
					for(int i=2;i<=xls.getRowCount("Testcases");i++){
						String TCname=xls.getCellData("Testcases", "TCName", i);
						
						if(TCname.equalsIgnoreCase(testcaseName)){
							xls=null;
							return i;
						}
					}
					return -1;
				}
}
