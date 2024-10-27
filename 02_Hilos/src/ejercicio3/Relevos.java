package ejercicio3;

public class Relevos {
	private static final Object MONITOR = new Object();
	private static int turnoActual = 1;
	
	public static void main(String[] args) {
		Corredor hiloA = new Corredor(1);
		Corredor hiloB = new Corredor(2);
		Corredor hiloC = new Corredor(3);
		Corredor hiloD = new Corredor(4);
		System.out.println("Todos los hilos creados.\r\n"
				+ "Doy la salida!");
		synchronized (MONITOR) {
			hiloA.start();
			hiloB.start();
			hiloC.start();
			hiloD.start();
		}
		
		System.out.println("Todos los hilos terminaron.");
	}
	
	public static class Corredor extends Thread {
		private int numero;
		
		public Corredor(int numero) {
			this.numero = numero;
		}
		
		public void run() {
			synchronized (MONITOR) {
				while (numero != turnoActual) {
					try {
						MONITOR.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			corre();
			turnoActual++;
			MONITOR.notifyAll();
		}
		
		public void corre() {
			System.out.println("Soy el hilo " + numero + ", corriendo...");
			try {
				Corredor.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (numero < 4) {
				System.out.println("Termine. Paso el testigo al hijo " + (numero + 1));
			} else {
				System.out.println("¡Termine!");
			}
		}
		
	}

}
