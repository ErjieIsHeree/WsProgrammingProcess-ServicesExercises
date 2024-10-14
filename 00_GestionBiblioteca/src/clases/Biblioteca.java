package clases;

import java.util.LinkedList;
import java.util.Scanner;

public class Biblioteca {

	LinkedList<Material> listaMateriales;
	
	public Biblioteca() {
		super();
	}
	public Biblioteca(LinkedList<Material> listMateriales) {
		super();
		this.listaMateriales = listMateriales;
	}
	public LinkedList<Material> getListMateriales() {
		return listaMateriales;
	}
	public void setListMateriales(LinkedList<Material> listMateriales) {
		this.listaMateriales = listMateriales;
	}

	public void addMaterial() {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		System.out.println();
		System.out.println("Introduzca un número del 1-4 el material que desee"
				+ " introducir");
		System.out.println("1.Libro");
		System.out.println("2.Revista");
		System.out.println("3.DVDs");
		System.out.println("4.Cancelar");
		opcion = sc.nextInt();
		sc.nextLine();
		switch (opcion) {
		case 1:
			listaMateriales.add(crearLibro(sc));
			break;
		case 2:
			listaMateriales.add(crearRevista(sc));
			break;
		case 3:
			listaMateriales.add(crearDVD(sc));
			break;
		case 4:
			break;
		default:
			System.out.println("Esta opción no existe, elija otra.");
		}
	}
	
	public Libro crearLibro(Scanner sc) {
		System.out.println("Introduzca el titulo del material");
		String titulo = sc.nextLine();
		System.out.println("Introduzca la fecha de publicación");
		String anio = sc.nextLine();
		System.out.println("Dime el autor del libro:");
		String autor = sc.nextLine();
		System.out.println("Dime el ISBN:");
		String iSBN = sc.nextLine();
		return new Libro(titulo, anio, autor, iSBN);
	}
	
	public Revista crearRevista(Scanner sc) {
		System.out.println("Introduzca el titulo del material");
		String titulo = sc.nextLine();
		System.out.println("Introduzca la fecha de publicación");
		String anio = sc.nextLine();
		System.out.println("Dime el número de la revista");
		int numero = sc.nextInt();
		return new Revista(titulo, anio, numero);
	}
	
	public DVD crearDVD(Scanner sc) {
		System.out.println("Introduzca el titulo del material");
		String titulo = sc.nextLine();
		System.out.println("Introduzca la fecha de publicación");
		String anio = sc.nextLine();
		System.out.println("Dime en nombre del director del DVD:");
		String director = sc.nextLine();
		return new DVD(titulo, anio, director);
	}
	
	public void mostrarMateriales() {
		System.out.println();
		for (Material c : listaMateriales) {
			System.out.println((listaMateriales.indexOf(c) + 1) + "."
					+ c.mostrarInfo());
		}
	}
	
	public Material buscarMaterialTitle(String titleMat)
			throws MaterialNoEncontradoException {
		for (Material m : listaMateriales) {
			if (m.titulo.equalsIgnoreCase(titleMat)) return m;
		}
		throw new
			MaterialNoEncontradoException("ExceptionMaterialNoEncontrado");
	}
	
}
