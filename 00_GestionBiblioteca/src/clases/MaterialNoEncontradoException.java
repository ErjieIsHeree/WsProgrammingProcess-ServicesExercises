package clases;

public class MaterialNoEncontradoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MaterialNoEncontradoException(String informacion) {
		super (informacion);
	}
	
}
