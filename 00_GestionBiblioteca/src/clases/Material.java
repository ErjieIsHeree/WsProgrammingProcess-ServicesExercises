package clases;

public abstract class Material {
	
	String titulo;
	String anioPublicacion;
	
	public Material(String titulo, String anioPublicacion) {
		super();
		this.titulo = titulo;
		this.anioPublicacion = anioPublicacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAnioPublicacion() {
		return anioPublicacion;
	}
	public void setAnioPublicacion(String anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}
	public abstract String mostrarInfo();
}
