package ExcelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class getObjectValue {
	public static ExcelOperations excel=new ExcelOperations();

	public String ObjectValue(String ObjectName){
		Properties prop=new Properties();
		String localObjectValue="";
		FileInputStream input=null;

		try{
			input=new FileInputStream(GlobalVariables.objectspath);
			prop.load(input);
			localObjectValue=prop.getProperty(ObjectName);

		} catch (IOException ex){
			ex.printStackTrace();
		}finally{
			if (input != null) {
				try{
					input.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}

		return localObjectValue;
	}


}
