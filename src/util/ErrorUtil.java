package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.Reporter;

//@SuppressWarnings("all")
public class ErrorUtil {
	private static Map<ITestResult,List<Throwable>> verificationFailuresMap = new HashMap<ITestResult,List<Throwable>>();
	//private static Map<ITestResult,List<Throwable>> skipMap = new HashMap<ITestResult,List<Throwable>>();

	     public static void addVerificationFailure(Throwable e) {
			//System.out.println("*************addVerificationFailure******************");
				List<Throwable> verificationFailures = getVerificationFailures();
				verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
				verificationFailures.add(e);
			}
		  
		  @SuppressWarnings({ "unchecked", "rawtypes" })
		public static List<Throwable> getVerificationFailures() {
				//System.out.println("*************getVerificationFailures******************");
				List<Throwable> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
				//return verificationFailures == null ? new ArrayList() : verificationFailures;
				return verificationFailures == null ? new ArrayList() : verificationFailures;

				//verificationFailures = null;
			}

}