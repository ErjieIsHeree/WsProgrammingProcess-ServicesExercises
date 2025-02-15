package objectConnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		IChat chat = null;
		
		//Guardo el stub del Chat en chat
		try {
			//Guardo en el registro el RMI
			Registry reg = LocateRegistry.getRegistry("localhost", 12344);
			
			//Almaceno el stub del registro en chat
			chat = (IChat) reg.lookup("Chat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Scanner sc = new Scanner(System.in);
			String mensaje = "";
			System.out.println("Introduce el mensaje a enviar al servidor\r\n"
					+ "Introduce STOP si deseas parar.");
			
			//Pide por consola un texto y lo almacena en mensaje
			while (!mensaje.equals("STOP")) {
				mensaje = sc.nextLine();
				
				//Si el mensaje no es "STOP" usa el stub para usar el RMI del
				//objeto chat
				if (!mensaje.equals("STOP")) {
					chat.enviar(mensaje);
				}
			}
			System.out.println("Chat cerrado");
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
