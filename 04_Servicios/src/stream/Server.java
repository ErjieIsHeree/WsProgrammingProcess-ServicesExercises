package stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	//El siguiente atributo contendrá la conexión del servidor
	ServerSocket server;
	
	//Ruta de la carpeta que contendrá los ficheros del servidor
	String filesRoot = "C:\\Users\\erjie_dmjyixu\\DAM\\2.ProgramaciónSyP\\2.Ejercicios\\WsEjercicios\\04_Servicios\\Files\\";
	
	//Nombre de los ficheros del servidor
	String f1 = "serverF1.txt";
	String f2 = "serverF2.txt";
	String f3 = "serverF3.txt";
	
	/**
	 * Crea el objeto servidor y establece, en su atributo server, la conexión con dirección
	 * "localhost" y puerto 12345
	 */
	public Server() {
		try {
			this.server = new ServerSocket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 12345);
			this.server.bind(addr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * El siguiente método acepta al primer cliente en establecer una conexión y lee
	 * el primer carácter enviado por el cliente. Si este carácter es un número (1-3),
	 * entonces ejecuta el método enviarMensaje con los atributos f1, f2, f3, como los
	 * parámtros de nombre de fichero (n1 = f1, n2 = f2, n3 = f3), con el cliente aceptado.
	 */
	public void operacion() {
		try {
			Socket client = server.accept();
			InputStream cis = client.getInputStream();
			
			int fileNumber = cis.read();
			if (fileNumber == 1) {
				enviarMensaje(f1, client);
			} else if (fileNumber == 2) {
				enviarMensaje(f2, client);
			} else if (fileNumber == 3) {
				enviarMensaje(f3, client);
			}
			System.out.println("Hilo terminado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * El siguiente método lee la primera línea del fichero con nombre introducido por parámetro
	 * situada dentro de la carpeta con la ruta que el atributo filesRoot indica. La línea leída
	 * lo envía, carácter a carácter al cliente pasado por parámetro.
	 * @param file nombre del fichero a leer
	 * @param client conexión con el cliente
	 */
	private void enviarMensaje(String file, Socket client) {
		try {
			OutputStreamWriter clientWriter = new OutputStreamWriter(client.getOutputStream());
			BufferedReader fileReader = new BufferedReader(new FileReader(filesRoot + file));
			
			String contenido = fileReader.readLine();
			fileReader.close();
			for (int i = 0; i < contenido.length(); i++) {
				clientWriter.write(contenido.charAt(i));
				clientWriter.flush();
			}
			clientWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Inicia la ejecución del servidor y llama al método operacion
	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.operacion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
