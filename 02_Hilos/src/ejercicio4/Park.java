package ejercicio4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Park {
	
	//Capacidad de garage
	private static int espacioParking = 20;
	//Cantidad de coches
	private static int cantidadCoches = 40;
	
	private static Random rd = new Random();
	private static final Object MONITOR = new Object();
	private static int[] arrayParking = new int[espacioParking];
	
	public static void main(String[] args) throws InterruptedException {
		Parking parking = new Parking();
		for (int i = 1; i <= cantidadCoches; i++) {
			Coche coche = new Coche(i, parking);
			coche.start();
			Thread.sleep(1500);
		}
		
	}
	
	private static class Parking{
		private Semaphore semaforo = new Semaphore(espacioParking);
		
		private void usaParking(int coche) {
			int posicionCoche = 0;
			try {
				semaforo.acquire();
				synchronized (MONITOR) {
					posicionCoche = entra(coche);
					System.out.println("ENTRADA: Coche " + coche + " aparca en " + posicionCoche + ".");
					System.out.println("Plazas libre: " + plazasLibres());
					visualParking();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(rd.nextInt(10000)+5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (MONITOR) {
					sale(posicionCoche);
					System.out.println("SALIDA: Coche " + coche + " saliendo.");
					System.out.println("Plazas libre: " + plazasLibres());
				}
			}
			
		}
		
		/**
		 * El siguiente metodo introduce un <i>Coche</i> en el arrayParking asociado a la clase principal
		 * @param coche numero del coche a introducir en el array
		 * @return posicionArray del coche en un valor entero
		 */
		private int entra(int coche) {
			int plazaLibre = 0;
			for (int i = arrayParking.length - 1; i > -1; i--) {
				if (arrayParking[i] == 0) {
					plazaLibre = i;
				}
			}
			arrayParking[plazaLibre] = coche;
			return plazaLibre;
		}
		
		/**
		 * El siguiente metodo saca el coche de la plaza del parking introducido y
		 * libera un permiso del semaforo.
		 * @param posicionCoche la posicion del array en la que se encuentra el coche
		 */
		private void sale(int posicionCoche) {
			arrayParking[posicionCoche] = 0;
		}
		
		/**
		 * Cuenta la cantidad 0 que contiene el array arrayParking.
		 * @return cantidadDeCeros de array arrayParking
		 */
		private int plazasLibres() {
			int plazasLibres = 0;
			for (int i = 0; i < arrayParking.length; i++) {
				if (arrayParking[i] == 0) {
					plazasLibres++;
				}
			}
			return plazasLibres;
		}
		
		/**
		 * El siguiente metodo imprime por consola el array de parking.
		 */
		private void visualParking() {
			String visualParking = "Parking: ";
			for (int i = 0; i < arrayParking.length; i++) {
				visualParking += i + 1 + "[" + arrayParking[i] + "] ";
			}
			System.out.println(visualParking);
		}
	}
	
	private static class Coche extends Thread{
		private int coche;
		private Parking parking;
		
		private Coche(int coche, Parking parking) {
			this.coche = coche;
			this.parking = parking;
		}
		
		public void run() {
			while (true) {
				parking.usaParking(coche);
				try {
					Thread.sleep(rd.nextInt(10000)+5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
