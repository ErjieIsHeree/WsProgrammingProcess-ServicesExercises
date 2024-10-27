package ejercicio2;

import java.util.Scanner;

public class Check {
	
	private static boolean monitor;
	
	public static void main(String[] args) {
		int[] buffer = new int[10000];
		Scanner sc = new Scanner(System.in);
		
		System.out.println("¿Deseas ejecutar con control de sincreonizacion o no?\r\n"
				+ "Introduzca si o no");
		if (sc.nextLine().equalsIgnoreCase("si")) {
			Check.monitor = true;
		} else {
			Check.monitor = false;
		}
		
		new Output(buffer).start();
		new Input(buffer).start();
		
		sc.close();
	}
	
	//Lector
	public static class Input extends Thread {
		private int[] buffer;
		
		public Input (int[] arrayEnteros) {
			this.buffer = arrayEnteros;
		}
		
		public void run() {
			if (Check.monitor) {
				synchronized (buffer) {
					leer();
				}
			} else {
				leer();
			}
		}
		
		public boolean leer() {		
			while (true) {
				for (int i = 0; i < buffer.length; i++) {
					if (buffer[0] != buffer[i]) {
						System.out.println("No concuerda" + i);
					} else {
						System.out.println("Concuerda");
					}
				}
			}
		}
		
	}

	//Escritor
	public static class Output extends Thread {
		private int[] buffer;
		
		public Output (int[] arrayEnteros) {
			this.buffer = arrayEnteros;
		}
		
		public void run() {
			int contador=0;
			while (true) {
				if (Check.monitor) {
					synchronized (buffer) {
						escribir(contador);
					}
				} else {
					escribir(contador);
				}
				contador++;
			}
		}
		
		public void escribir(int contador) {
			for (int i = 0; i < buffer.length; i++) {
				buffer[i] = contador;
			}
		}
		
	}
	
}
