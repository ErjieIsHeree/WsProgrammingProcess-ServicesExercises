package stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	//Ruta al fichero del cliente
	static String fileRoot = "C:\\Users\\erjie_dmjyixu\\DAM\\2.ProgramaciónSyP\\2.Ejercicios\\WsEjercicios\\04_Servicios\\Files\\clientF.txt";	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			//Establece la conexión con el servidor en la dirección "localhost" en el puerto 12345
			Socket server = new Socket("localhost", 12345);
			
			OutputStream sOS = server.getOutputStream();
			InputStreamReader cISR = new InputStreamReader(server.getInputStream());;
			BufferedWriter fOSW = new BufferedWriter(new FileWriter(fileRoot));
			
			//Recoge por consola un número y lo envía al cliente
			System.out.println("Introduce el número de un archivo (1-3) (Solo uno)");
			sOS.write(sc.nextInt());
			
			//Recibo mensaje del servidor y lo almaceno en message
			int buffer = 0;
			String message = "Server file content: ";
			while ((buffer = cISR.read()) != -1) {
				message += (char)buffer;
			}
			
			//Escribo el mensaje en el fichero con la ruta del atributo fileRoot
			fOSW.write(message);
			fOSW.flush();
			fOSW.close();
			
			server.close();
			System.out.println("Conexión finalizada");
		} catch(Exception e) {
			e.printStackTrace();
		}
		sc.close();
		System.out.println("Programa finalizado");
	}

}
