package objectConnection;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {
	
	/**
	 * Método a remoto
	 * @param mensaje cadena de texto
	 * @throws RemoteException
	 */
	public void enviar(String mensaje) throws RemoteException;

}
