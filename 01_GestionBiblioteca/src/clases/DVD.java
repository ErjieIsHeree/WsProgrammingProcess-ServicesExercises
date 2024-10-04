package clases;

public class DVD extends Material{

	String director;
	
	public DVD(String titulo, String anioPublicacion, String director) {
		super(titulo, anioPublicacion);
		this.director = director;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	@Override
	public String mostrarInfo() {
		// TODO Auto-generated method stub
		return "Título: " + super.titulo + "\n" +
			"Año publicación: " + super.anioPublicacion + "\n" +
			"Director: " + this.director;
	}
	
}
