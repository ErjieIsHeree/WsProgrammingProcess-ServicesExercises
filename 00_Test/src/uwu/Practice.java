package uwu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Practice {
	
	public static void main(String[] args) {
		Process MyProcess = null;
		BufferedReader lectorConsola = null;
		BufferedReader lectorHijo = null;
		BufferedWriter escritorHijo = null;
		String classPath = System.getProperty("java.class.path");
		
		try {
			MyProcess = new ProcessBuilder("java", "-cp", classPath, "uwu.a").start();
			
			lectorConsola = new BufferedReader(new InputStreamReader(System.in));
			lectorHijo = new BufferedReader(new InputStreamReader(MyProcess.getInputStream()));
			escritorHijo = new BufferedWriter(new OutputStreamWriter(MyProcess.getOutputStream()));
			
			String line = null;
			String line2 = null;
			while (!(line = lectorConsola.readLine()).equals("")) {
				escritorHijo.write(line);
				escritorHijo.flush();
				
				line2 = lectorHijo.readLine();
				System.out.println("dice:" + line2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			MyProcess.destroy();
			try {
				lectorConsola.close();
				lectorHijo.close();
				escritorHijo.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
