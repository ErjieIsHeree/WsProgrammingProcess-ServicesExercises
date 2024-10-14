package _01_procesos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CommsProcesos {
	
	public static void main(String[] args) {
		Process proceso1 = null;
		Process proceso2 = null;
		BufferedReader bufRea = null;
		BufferedWriter bufWri = null;
		try {
			proceso1 = new ProcessBuilder("cmd", "/c", "dir").start();
			proceso2 = new ProcessBuilder("cmd", "/c", "findstr /i d").start();
			bufRea = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
			bufWri = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()));
			//MOSTRAR PROCESO 1
			String linea;
			while((linea = bufRea.readLine()) != null) {
				System.out.println(linea);
				bufWri.write(linea + "\n");
			}
			bufWri.flush();
			bufWri.close();
			proceso1.waitFor();
			System.out.println("");
			System.out.println("");
			//MOSTRAR PROCESO 2
			bufRea = new BufferedReader(new InputStreamReader(proceso2.getInputStream()));
			while ((linea = bufRea.readLine()) != null) {
				System.out.println(linea);
			}
			proceso2.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufRea.close();
				bufWri.close();
				proceso1.destroy();
				proceso2.destroy();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
