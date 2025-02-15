package objectConnection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Chat implements IChat{
	
	/**
	 * El siguiente método sirve para recibir mensajes e imprimirlos
	 * por consola, puede usarse en clientes que tengan acceso al objeto
	 * remoto Chat
	 */
	@Override
	public void enviar(String mensaje) throws RemoteException {
		System.out.println(mensaje);
	}
	
	public static void main(String[] args) {
		
		//Creación de registro de objetos remotos
		Registry reg = null;
		try {
			//Localiza y crea un registro en el puerto 12344
			reg = LocateRegistry.createRegistry(12344);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Creación del objeto Chat
		Chat chatObject = new Chat();
		try {
			//Creación del stub en la que contiene el objeto con el métodos
			IChat stub = (IChat) UnicastRemoteObject.exportObject(chatObject, 12345);
			
			//Inscribiendo el stub en el registro con el nombre Chat
			reg.rebind("Chat", stub);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
