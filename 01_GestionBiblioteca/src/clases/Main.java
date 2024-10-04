package clases;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		LinkedList<Material> listaMateriales = new LinkedList<Material>();
		Biblioteca biblio = new Biblioteca(listaMateriales);
		int opcion = 0;
		
		do {
			opcion = menu(sc);
			sc.nextLine();
			switch (opcion) {
			case 1:
				biblio.mostrarMateriales();
				break;
			case 2:
				biblio.addMaterial();
				break;
			case 3:
				System.out.println("Dime el titulo del material que "
						+ "deseas");
				String titleMat = sc.nextLine();
				try {
					System.out.println(biblio.buscarMaterialTitle(titleMat)
							.mostrarInfo());
				} catch (Exception e) {
					System.out.println("El título no fue encontrado");
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Esta opción no hase nada, introduzca "
						+ "otro.");
				break;
			}
		} while (opcion != 4);
		
	}
	
	public static int menu(Scanner sc) {
		
		System.out.println();
		System.out.println("Selecciones la opción introduciendo del 1-3 "
				+ "según lo que desees");
		System.out.println("1.Mostrar material");
		System.out.println("2.Agregar material");
		System.out.println("3.Buscar material");
		System.out.println("4.Apagar programa");
		System.out.println();
		return sc.nextInt();
	}

}
