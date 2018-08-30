package excelutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

	public static void main(String[] args) throws IOException {
		Properties prop=new Properties();
		FileInputStream f=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
		prop.load(f);
		System.out.println(prop.get("browser"));
		System.out.println(prop.get("username"));
		System.out.println(prop.get("env"));
		System.out.println(prop.get("xpath1"));
		
		FileInputStream f2=new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\OR.properties");
		prop.load(f2);
		System.out.println(prop.get("xpath1"));
		System.out.println(prop.get("env"));
	}

}
