package bidireccionales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Mayusculas {
	
	public static void main(String[] args) {
		
		Process procesoHijo = null;
		BufferedReader br_teclado = null;
		BufferedReader br_hijo = null;
		BufferedWriter bw_hijo = null;
		String classpath = System.getProperty("java.class.path");
		try {
			procesoHijo = new ProcessBuilder("java", "-cp", classpath, "bidireccionales.Hijo").start();
			
			//Para introducir desde el teclado
			br_teclado = new BufferedReader(new InputStreamReader(System.in));
			//Para introducir al hijo
			bw_hijo = new BufferedWriter(new OutputStreamWriter(procesoHijo.getOutputStream()));
			
			//Para leer del hijo
			br_hijo = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()));
			
			String line = "";
			String readHijo = "";
			System.out.println("Introduzca cadenas, para salir introduzca \"salir\"");
			while (!(line = br_teclado.readLine()).equalsIgnoreCase("")) {
				//Se escribe en el hijo
				bw_hijo.write(line + "\n");
				bw_hijo.flush();
				
				//Se lee del hijo
				readHijo = br_hijo.readLine();
				System.out.println("Hijo dice: " + readHijo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				procesoHijo.destroy();
				br_teclado.close();
				br_hijo.close();
				bw_hijo.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}