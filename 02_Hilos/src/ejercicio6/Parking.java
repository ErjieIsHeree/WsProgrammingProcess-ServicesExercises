package ejercicio6;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Parking {
	private int[] plazas;
	private int disponibles;
	private Semaphore semaforo;
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaforo = new Semaphore(5);
		Parking parking = new Parking(5, semaforo);
		
		for(int i = 1; i <= 10; i++) {
			Thread hiloCoche = new Thread(new Coche(i, parking));
			hiloCoche.start();
			Thread.sleep(1500);
		}
		
	

	}
	
	
	public Parking(int disponibles, Semaphore semaforo) {
		this.plazas = new int[disponibles];
		this.disponibles = disponibles;
		this.semaforo = semaforo;
	}


	public void entrar(int idCoche) throws InterruptedException {
		semaforo.acquire();
		synchronized(plazas) {
			for(int i = 0; i < plazas.length; i++) {
				if(plazas[i] == 0) {
					plazas[i] = idCoche;
					System.out.println("ENTRADA: Coche " + idCoche + " aparca en " + i);
					disponibles --;
					imprimirEstado();
					break;
				}
			}
		}
	}
	
	public void salir(int idCoche) {
		synchronized(plazas) {
			for(int i = 0; i < plazas.length; i++) {
				if(plazas[i] == idCoche) {
					plazas[i] = 0;
					System.out.println("SALIDA: Coche " + idCoche + " saliendo");
					disponibles++;
					imprimirEstado();
					semaforo.release();
					break;
				}
			}
		}
	}
	
	private void imprimirEstado() {
		System.out.println("Plazas libres: " + disponibles);
		System.out.println("Parking: " + Arrays.toString(plazas));
		System.out.println("");
	}

}


class Coche extends Thread{
	private int id;
	private Parking parking;
	public Coche(int id, Parking parking) {
		this.id = id;
		this.parking = parking;
	}
	@Override
	public void run() {
		Random rd = new Random();
		while (true) {
			try {
				parking.entrar(id);
				Thread.sleep(rd.nextInt(10000)+5000);
				
				parking.salir(id);
				Thread.sleep(rd.nextInt(10000)+5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}