package ejercicio4;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Park {
	private static int[] arrayParking = new int[6];
	private final static Object MONITOR = new Object();
	private static int turnoActual = 1;
	
	public static void main(String[] args) {
		Parking parking = new Parking(6);
		Coche c1 = new Coche(1, 1, parking);
		Coche c2 = new Coche(2, 2, parking);
		Coche c3 = new Coche(3, 3, parking);
		Coche c4 = new Coche(4, 4, parking);
		Coche c5 = new Coche(5, 5, parking);
		Coche c6 = new Coche(6, 6, parking);
		Coche c7 = new Coche(7, 7, parking);
		Coche c8 = new Coche(8, 8, parking);
		Coche c9 = new Coche(9, 9, parking);
		Coche c10 = new Coche(10, 10, parking);
		
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		c6.start();
		c7.start();
		c8.start();
		c9.start();
		c10.start();
		
	}
	
	public static class Parking{
		private Semaphore semaforo;
		
		public Parking(int espaciosLibres) {
			this.semaforo = new Semaphore(espaciosLibres);
		}
		
		public void aparcar(int coche) {
			Random rd = new Random();
			int posicionCoche = 0;
			try {
				semaforo.acquire();
				posicionCoche = entrar(coche, rd);
				System.out.println("ENTRADA: Coche " + coche + " aparca en " + posicionCoche);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(rd.nextInt(10000)+3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				salir(posicionCoche);
				semaforo.release();
				System.out.println("SALIDA: Coche " + coche + " saliendo.");
				int plazasLibres = 0;
				for (int vacio : arrayParking) {
					if (vacio == 0) {
						plazasLibres++;
					}
				}
				System.out.println("Plazas libres: " + plazasLibres);
			}
		}
		
		public int entrar(int coche, Random rd) {
			ArrayList<Integer> listaPlazaLibre = new ArrayList<Integer>();
			for (int i = 0; i < arrayParking.length; i++) {
				if (arrayParking[i] == 0) {
					listaPlazaLibre.add(i);
				}
			}
			int plaza = listaPlazaLibre.get(rd.nextInt(listaPlazaLibre.size() - 1));
			arrayParking[plaza] = coche;
			return plaza;
		}
		
		public void salir(int posicionCoche) {
			arrayParking[posicionCoche] = 0;
		}
		
		public String visualParking() {
			return ("[" + arrayParking[0] + "] " + "[" + arrayParking[1] + "] " + "[" + arrayParking[2] + "] " + "[" + arrayParking[3] + "] "
					+ "[" + arrayParking[4] + "] " + "[" + arrayParking[5] + "] " + "[" + arrayParking[6] + "]");
		}
	}
	
	public static class Coche extends Thread{
		private int coche;
		private int turno;
		private Parking parking;
		
		public int getCoche() {
			return coche;
		}
		
		public Coche(int coche, int turno, Parking parking) {
			this.coche = coche;
			this.turno = turno;
			this.parking = parking;
		}
		
		public void run() {
			synchronized (MONITOR) {
				while (turnoActual != turno) {
					try {
						MONITOR.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
					parking.aparcar(coche);
					MONITOR.notifyAll();
					turnoActual++;
				}
			}
		}
		
	}
	
}
