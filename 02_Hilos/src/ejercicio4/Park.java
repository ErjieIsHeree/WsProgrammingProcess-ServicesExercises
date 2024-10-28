package ejercicio4;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Park {
	
	//Capacidad de garage
	private static int espacioParking = 10;
	//Cantidad de coches
	private static int cantidadCoches = 20;
	
	private static int[] arrayParking = new int[espacioParking];
	
	public static void main(String[] args) {
		Parking parking = new Parking(espacioParking);
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		for (int i = 1; i <= cantidadCoches; i++) {
			listaCoches.add(new Coche(i, parking));
		}
		for (int i = 0; i < cantidadCoches; i++) {
			listaCoches.get(i).start();
		}
	}
	
	public static class Parking{
		private Semaphore semaforo;
		
		public Parking(int espaciosGarage) {
			this.semaforo = new Semaphore(espaciosGarage);
		}
		
		public void entradaSalidaDe(int coche) {
			Random rd = new Random();
			int posicionCocheEnArray = 0;
			
			try {
				posicionCocheEnArray = entrar(coche);
				System.out.println("---------------------------------------------------------\r\n"
						+ "ENTRADA: Coche " + coche + " aparca en " + (1 + posicionCocheEnArray) + "\r\n"
						+ "Plazas libre: " + semaforo.availablePermits() + "\r\n"
						+ visualParking());
				visualParking();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(rd.nextInt(300000)+150000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				salir(posicionCocheEnArray);
				System.out.println("---------------------------------------------------------\r\n"
						+ "SALIDA: Coche " + coche + " saliendo.\r\n"
						+ "Plazas libres: " + semaforo.availablePermits());
			}
		}
		
		/**
		 * El siguiente metodo pide un permiso al semaforo, cuando reciba el permiso
		 * @param coche numero del coche a introducir en el array
		 * @return posicionArray del coche en un valor entero
		 */
		public int entrar(int coche) {
			try {
				semaforo.acquire();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int plazaLibre = 0;
			for (int i = arrayParking.length - 1; i >= 0; i--) {
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
		public void salir(int posicionCoche) {
			arrayParking[posicionCoche] = 0;
			semaforo.release();
		}
		
		public String visualParking() {
			String plazas = "";
			for (int i = 0; i < arrayParking.length; i++) {
				plazas += i + 1 + "[" + arrayParking[i] + "] ";
			}
			return plazas;
		}
	}
	
	public static class Coche extends Thread{
		private int coche;
		private Parking parking;
		
		public Coche(int coche, Parking parking) {
			this.coche = coche;
			this.parking = parking;
		}
		
		public void run() {
			while (true) {
				parking.entradaSalidaDe(coche);
			}
		}
		
	}
	
}
