package Shutdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Com {

	public static void main(String[] args) {
		Process process = null;
		BufferedReader bufferRead = null;
		try{
			//If you don't want to see a window open
			//Runtime.getRuntime().exec("cmd /c start /MIN D:\\Project\\Program\\ControlLab\\src\\Shutdown\\test.bat");
			//process = Runtime.getRuntime().exec("cmd /c D:\\Project\\Programs\\ControlLab\\src\\shutdown\\bat\\shutdown.bat");
                        process = Runtime.getRuntime().exec("cmd /c D:\\Project\\Programs\\ControlLab\\src\\shutdown\\bat\\cancel.bat");
			bufferRead = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line = null;
			while ((line = bufferRead.readLine()) != null) {
				System.out.println(line);
			}
			
			if(process.exitValue() == 0){
				System.out.println("Command start sucess...");
			}else{
				System.out.println("Command start fail...");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try {
				process.destroy();
				bufferRead.close();
			} catch (IOException e) {}
		}
	}
}