package datagram;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ServerIP {
	
	public static void main(String[] args) {
		DatagramSocket client = null;
		InetSocketAddress serverIP = new InetSocketAddress("localhost", 12345);
		
		//Establece la conexion del servidor
		try {
			
			client = new DatagramSocket(serverIP);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Hacemos que el servidor esté contantemente escuchando peticiones
		boolean serverON = true;
		while (serverON) {
			//Recibe "IP" y envía la IP del cliente
			try {
				//La cantidad de información que se espera del cliente, como se pide un mensaje ("IP"), el buffer será de 2 bytes
				byte[] buffer = new byte[2];
				DatagramPacket rcvPacket = new DatagramPacket(buffer, 2);
				
				//Almacena los bytes recividos por el usuario en packet
				client.receive(rcvPacket);
				
				//Traducimos los bytes
				String mensaje = new String(rcvPacket.getData());
				System.out.println(mensaje);
				//Si el mensaje es IP, devolveremos la IP al cliente
				if (mensaje.equals("IP")) {
					//Recoge la dirección IP y el puerto del cliente
					InetAddress clientAddr = rcvPacket.getAddress();
					int port = rcvPacket.getPort();
					System.out.println("Datos a enviar: " + clientAddr.toString());
					
					//Prepara y envía el paquete con los datos
					byte[] IP = clientAddr.toString().getBytes();
					DatagramPacket sndPacket = new DatagramPacket(IP, IP.length, clientAddr, port);
					client.send(sndPacket);
					
					System.out.println("Paquete envíado");
				} else if (mensaje.equals("PI")) {
					serverON = false;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
