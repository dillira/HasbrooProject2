package excelutil;

import util.Xls_Reader;

public class GetRowNums {

	public static void main(String[] args) {
		Xls_Reader x=new Xls_Reader(System.getProperty("user.dir")+"\\src\\xls\\SuiteA.xlsx");
	System.out.println(getRowNum(x, "AddProductTest"));
		
	}

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
