package clases;

import java.util.Scanner;

public class Libro extends Material {
	
	String autor;
	String iSBN;
	
	public Libro(String titulo, String anioPublicacion, String autor,
			String iSBN) {
		super(titulo, anioPublicacion);
		this.autor = autor;
		this.iSBN = iSBN;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getISBN() {
		return iSBN;
	}
	public void setISBN(String iSBN) {
		Scanner sc = new Scanner(System.in);
		while (iSBN.length() != 6) {
			System.out.println("El ISBN debe ser de 6 números");
			iSBN = sc.nextLine();
		}
		sc.close();
		this.iSBN = iSBN;
	}
	@Override
	public String mostrarInfo() {
		// TODO Auto-generated method stub
		return "Título: " + super.titulo + "\n" +
			"Año publicación: " + super.anioPublicacion + "\n" +
			"Autor: " + this.autor + "\n" +
			"ISBN: " + this.iSBN;
	}
}
