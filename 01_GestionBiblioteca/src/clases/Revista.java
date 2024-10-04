package clases;

public class Revista extends Material {

	int numero;
	
	public Revista(String titulo, String anioPublicacion, int numero) {
		super(titulo, anioPublicacion);
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String mostrarInfo() {
		// TODO Auto-generated method stub
		return "Título: " + super.titulo + "\n" +
			"Año publicación: " + super.anioPublicacion + "\n" +
			"Numero revista: " + this.numero;
	}
}
