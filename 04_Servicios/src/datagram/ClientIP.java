package datagram;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientIP {
	
	public static void main(String[] args) {
		DatagramSocket server = null;
		
		//Envía IP y recibe mi IP
		try {
			//Establizando conexión
			server = new DatagramSocket();
			
			//Prepara y envía el mensaje
			byte[] bufferSND = "IP".getBytes();
			InetAddress serverAddr = InetAddress.getByName("localhost");
			DatagramPacket IPrequest = new DatagramPacket(bufferSND, 2, serverAddr, 12345);
			server.send(IPrequest);
			
			//Prepara un paquete para recibir el mensaje del servidor
			byte[] bufferRCV = new byte[50];
			DatagramPacket rcvPacket = new DatagramPacket(bufferRCV, 50);
			server.receive(rcvPacket);
			
			//Traduce el paquete (debería ser mi dirección IP)
			System.out.println("My IP dir: " + new String(bufferRCV));
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			server.close();
		}
		
	}

}
