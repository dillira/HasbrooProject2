package com.hasbro.cascading;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import util.Utility;

import base.SeleniumDriver;

public class TestsuiteBase extends SeleniumDriver{

	@BeforeSuite
	public void checkSuiteSkipTest() throws Exception{
		initialize();
	//	log.debug("Initiallized");
		//System.out.println(suiteXls);
		if(!Utility.isSuiteRunnable(suiteXls, "cascading")){
			log.debug("Skipping cascading option as runmode is set to no");
			throw new SkipException("Skipping cascading option as runmode is set to no");
		}
		System.out.println("Inside cascading");
	}
}
