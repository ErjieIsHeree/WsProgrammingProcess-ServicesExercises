package ejercicio1;

public class Orden {
	public static void main(String[] args) {
		Hilo a = new Hilo(1);
		Hilo b = new Hilo(2);
		
		b.start();
		try {
			b.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.start();
		
	}
	
	public static class Hilo extends Thread{
		
		private int numero;
		
		public Hilo (int numero) {
			this.numero = numero;
		}
		
		public void run() {
			System.out.println("Hola, soy el hilo numero " + numero);
		}
	}
}
