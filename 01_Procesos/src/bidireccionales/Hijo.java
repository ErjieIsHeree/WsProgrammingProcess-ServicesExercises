package bidireccionales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Hijo {
	
	public static void main(String[] args) {
		BufferedReader lector = null;
		BufferedWriter escritor = null;
		
		lector = new BufferedReader(new InputStreamReader(System.in));
		escritor = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			
			String linea = "";
			while ((linea = lector.readLine()) != null) {
				escritor.write(linea.toUpperCase() + "\n");
				escritor.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lector.close();
				escritor.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
